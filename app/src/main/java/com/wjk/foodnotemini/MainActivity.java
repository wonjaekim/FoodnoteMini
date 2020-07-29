package com.wjk.foodnotemini;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wjk.foodnotemini.api.APIClient;
import com.wjk.foodnotemini.api.APIInterface;
import com.wjk.foodnotemini.bean.Bean;
import com.wjk.foodnotemini.define.CommonAplication;
import com.wjk.foodnotemini.define.Define;
import com.wjk.foodnotemini.gps.GpsInfo;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    GpsInfo gpsInfo;

    double latitude = -1;
    double longitude = -1;

    private final int PERMISSIONS_ACCESS_FINE_LOCATION = 1000;
    private final int PERMISSIONS_ACCESS_COARSE_LOCATION = 1001;
    private boolean isAccessFineLocation = false;
    private boolean isAccessCoarseLocation = false;
    private boolean isPermission = false;

    TextView text;
    TextView startBtn;
    ImageView filterBtn;

    APIInterface apiInterface;

    RelativeLayout btnArea;

//    String priceCategory = "";
    String placeCategory = "";
    String infoCategory = "";

    CommonAplication commonAplication;
    SharedPreferences preferences;
    boolean isPermissionDialogShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gpsInfo = new GpsInfo(this);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        commonAplication = (CommonAplication) getApplicationContext();
        preferences = getSharedPreferences(Define.SHARED_INFO, MODE_PRIVATE);
        isPermissionDialogShow = preferences.getBoolean(Define.PERMISSION_DIALOG_SHOW, false);



        btnArea = (RelativeLayout)findViewById(R.id.btn_area);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//
//                btnArea.setVisibility(View.VISIBLE);
//
//            }
//
//        }, 1000);


        ViewCompat.animate(btnArea)
                .setDuration(100)
                .setStartDelay(2000)
                .alpha(1)
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {

                    }

                    @Override
                    public void onAnimationEnd(View view) {


                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                            int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
                            int permissionCheck2 = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);

                            if(permissionCheck == PackageManager.PERMISSION_DENIED
                                    || permissionCheck2 == PackageManager.PERMISSION_DENIED){

                                // 권한 없음
                                locationPermissionDialog();
                            } else{
                                // 권한 있음
                                checkPermission();
                            }
                        }else{

                            if(isPermissionDialogShow){
                                checkPermission();
                            }else{
                                locationPermissionDialog();
                            }
                        }
                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                })
                .start();


        text = (TextView)findViewById(R.id.text);
        startBtn = (TextView)findViewById(R.id.start);
        filterBtn = (ImageView) findViewById(R.id.filter_btn);


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getData();
            }
        });

        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, FilterActivity.class);

//                intent.putExtra("price", priceCategory);
                intent.putExtra("service", infoCategory);
                intent.putExtra("situation", placeCategory);

                startActivityForResult(intent, 8);

                overridePendingTransition(R.anim.anim_slide_in_bottom, R.anim.anim_slide_out_top);
            }
        });

        // 내위치 초기화
        (findViewById(R.id.location_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gpsInfo.getLocation();
                startLocation();
                Snackbar.make(filterBtn, "현재위치를 업데이트 하였습니다.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("onResume ::: ");


        gpsInfo.getLocation();

        if(!gpsInfo.isGPSEnabled()){
            gpsInfo.showSettingsAlert();
            return;
        }


//        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
//        int permissionCheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
//
//        if(permissionCheck == PackageManager.PERMISSION_DENIED
//                || permissionCheck2 == PackageManager.PERMISSION_DENIED){
//
//            // 권한 없음
//
//            locationPermissionDialog();
//        }
//        else{
//            // 권한 있음
//            checkPermission();
//        }

        startLocation();
    }

    public void locationPermissionDialog() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("서비스 사용을 위한 접근권한 안내")
                .setMessage("위치 : 현재위치 주변의 맛집검색")
                .setCancelable(false)
//                                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        // do nothing
//                                    }
//                                })
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete

                        isPermissionDialogShow = true;
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean(Define.PERMISSION_DIALOG_SHOW, isPermissionDialogShow);
                        editor.apply();

                        checkPermission();
                    }
                })
//                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    // 권한 확인
    private void checkPermission() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_ACCESS_FINE_LOCATION);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSIONS_ACCESS_COARSE_LOCATION);
        } else {
            isPermission = true;
            startLocation();
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_ACCESS_FINE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            isAccessFineLocation = true;

        } else if (requestCode == PERMISSIONS_ACCESS_COARSE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            isAccessCoarseLocation = true;
        }

        if (isAccessFineLocation && isAccessCoarseLocation) {
            isPermission = true;
            startLocation();
        }
    }



    public void startLocation(){

        latitude = gpsInfo.getLatitude();
        longitude = gpsInfo.getLongitude();

        text.setText(getAddress(this, latitude, longitude)
                +" 주변의 맛집을 추천해드립니다."
        );

        System.out.println("latitude ::: " + latitude);
        System.out.println("longitude ::: " + longitude);
    }



    /**
     * 위도,경도로 주소구하기
     *
     * @param lat
     * @param lng
     * @return 주소
     */
    public String getAddress(Context mContext, double lat, double lng) {

        String nowAddress = "현재위치";
        Geocoder geocoder = new Geocoder(mContext, Locale.KOREA);
        List<Address> address;
        try {
            if (geocoder != null) {
                //세번째 파라미터는 좌표에 대해 주소를 리턴 받는 갯수로
                //한좌표에 대해 두개이상의 이름이 존재할수있기에 주소배열을 리턴받기 위해 최대갯수 설정
                address = geocoder.getFromLocation(lat, lng, 1);

                if (address != null && address.size() > 0) {
                    // 주소 받아오기
                    String currentLocationAddress = address.get(0).getAddressLine(0).toString();
                    String currentLocality = address.get(0).getLocality(); // 서울시
                    String currentSubLocality = address.get(0).getSubLocality();         // 강남구
                    String currentDong = address.get(0).getThoroughfare(); // 역삼동


                    nowAddress = getSidoGugun(currentLocationAddress);

//                    System.out.println("주소 ::: " +currentLocality + " "+currentSubLocality +  " " + currentDong);
//                    System.out.println("nowAddress ::: " +nowAddress);
                }
            }

        } catch (IOException e) {
//            Toast.makeText(this, "주소를 가져 올 수 없습니다.", Toast.LENGTH_LONG).show();
            text.setText("주소를 가져 올 수 없습니다.");

            e.printStackTrace();
        }
        return nowAddress;
    }


    public String getSidoGugun(String sido){
        String result = "";

//        System.out.println("getSidoGugun ::: " +sido);

        String sidoGugun[] = sido.split(" ");

        String ssi = "";

        if(sidoGugun[1].contains("충청") || sidoGugun[1].contains("전라") || sidoGugun[1].contains("경상")){

            if(sidoGugun[1].equals("충청북도")){
                ssi = "충북";
            }else if(sidoGugun[1].equals("충청남도")){
                ssi = "충남";
            }else if(sidoGugun[1].equals("전라북도")){
                ssi = "전북";
            }else if(sidoGugun[1].equals("전라남도")){
                ssi = "전남";
            }else if(sidoGugun[1].equals("경상북도")){
                ssi = "경북";
            }else if(sidoGugun[1].equals("경상남도")){
                ssi = "경남";
            }

            result = ssi + " " + sidoGugun[2];

        }else{

            String subStr = sidoGugun[1].substring(0, 2);
            result = subStr + " " + sidoGugun[2];
        }

        return result;
    }

    public Integer getTime(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        int getTime = Integer.parseInt(sdf.format(date));
        return getTime;
    }

    public String placeParamCheck(String pc) {

        String result = "";
        int time = getTime();
        if (pc.length() < 3) {
            pc = "";
        }

        JSONArray array = null;
        try {
            if (pc.length() > 0) {
                array = new JSONArray(pc);
            } else {
                array = new JSONArray();
            }

            if (!pc.contains("STATE01")) {
                if (time > 9 && time <= 15) {
                    array.put("STATE01");
                }
            }
            if (!pc.contains("STATE02")) {
                if (time > 15 && time < 24) {
                    array.put("STATE02");
                }
            }
            result = array.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        System.out.println("result ::: " + result);

        return result;
    }

    public void getData(){

        Map map = new HashMap();
        map.put("lat",latitude);
        map.put("lng",longitude);

        // 점심, 저녁시간 체크
        map.put("state_category",placeParamCheck(placeCategory));
        map.put("service_category",infoCategory);

        System.out.println("params ::: " + map.toString());

        Call<Bean> call = apiInterface.getData(map);
        call.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {

                System.out.println("response ::: " + response.toString());


                if(response.body().stat != null ){

                    // 데이터 있음
                    if(response.body().stat.equals("1")){

                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra("title", response.body().getResponse().title);
                        intent.putExtra("place", response.body().getResponse().place);
                        intent.putExtra("phone", response.body().getResponse().phone);
                        intent.putExtra("contents", response.body().getResponse().contents);

                        intent.putExtra("contents_srl", response.body().getResponse().contents_srl);
                        intent.putExtra("images", (Serializable) response.body().getResponse().getImages());
                        startActivity(intent);

                    }
                    // 데이터 없음
                    else if(response.body().stat.equals("2")){

                        new AlertDialog.Builder(MainActivity.this)
//                                .setTitle("3G/LTE 데이터 이용 확인")
                                .setMessage("주변에 맛집이 없습니다.")
//                                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        // do nothing
//                                    }
//                                })
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // continue with delete
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();

                    }
                    // 에러
                    else{

                        new AlertDialog.Builder(MainActivity.this)
//                                .setTitle("3G/LTE 데이터 이용 확인")
                                .setMessage("네트워크 오류가 발생하였습니다.")
//                                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        // do nothing
//                                    }
//                                })
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // continue with delete
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();

                    }

                }else{

                    new AlertDialog.Builder(MainActivity.this)
//                                .setTitle("3G/LTE 데이터 이용 확인")
                            .setMessage("치명적인 오류가 발생하였습니다.")
//                                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        // do nothing
//                                    }
//                                })
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }

            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {

                new AlertDialog.Builder(MainActivity.this)
//                                .setTitle("3G/LTE 데이터 이용 확인")
                        .setMessage("무지막지한 오류가 발생하였습니다.")
//                                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        // do nothing
//                                    }
//                                })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 8 ){
            if(resultCode == RESULT_OK){

//                priceCategory = data.getStringExtra("price");
                infoCategory = data.getStringExtra("info");
                placeCategory = data.getStringExtra("place");

                if(infoCategory.length() < 3){
                    infoCategory = "";
                }
                if(placeCategory.length() < 3){
                    placeCategory = "";
                }

                if(infoCategory.length() > 0 || placeCategory.length() > 0){
                    filterBtn.setImageResource(R.drawable.btn_filter_on);
                }else{
                    filterBtn.setImageResource(R.drawable.btn_filter_off);
                }
//                System.out.println("infoCategory ::: " +infoCategory);
//                System.out.println("placeCategory ::: " +placeCategory);
            }
        }
    }

    // 뭐먹지가 궁금해요 다이얼로그
    public void showGuideDialog(String title, String contents, String image, String content_srl, String phone, String place) {

        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        if (Build.VERSION.SDK_INT >= 21) {
            dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            dialog.getWindow().setStatusBarColor(Color.parseColor("#472731"));
        }
        dialog.setContentView(R.layout.dialog_guide_popup);


        ImageView imageV = (ImageView)dialog.findViewById(R.id.image);
        TextView close1 = (TextView)dialog.findViewById(R.id.close1);
        TextView close2 = (TextView)dialog.findViewById(R.id.close2);
        TextView titleTv = (TextView)dialog.findViewById(R.id.title);
        TextView contentsTv = (TextView)dialog.findViewById(R.id.content);
        TextView phoneTv = (TextView)dialog.findViewById(R.id.phone);
        TextView placeTv = (TextView)dialog.findViewById(R.id.place);

        Glide.with(this)
                .load(image)
                .centerCrop()
                .into(imageV);

        titleTv.setText(title);
        contentsTv.setText(contents);
        phoneTv.setText(phone);
        placeTv.setText(place);


        close1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                dialog.dismiss();
            }
        });

        close2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

//                System.out.println(" @@@@ ::: "+Utils.getDateString("yyyyMMddHHmm", (int)(System.currentTimeMillis()/1000L)));
//                System.out.println(" #### ::: "+Utils.getDateString("yyyyMMddHHmm", (int)((System.currentTimeMillis()/1000L)+(24*60*60))));

                dialog.dismiss();
            }
        });
        dialog.show();
    }


    @Override
    protected void onDestroy() {

        gpsInfo.stopUsingGPS();
        super.onDestroy();
    }
}
