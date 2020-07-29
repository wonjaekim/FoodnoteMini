package com.wjk.foodnotemini;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wjk.foodnotemini.api.APIClient;
import com.wjk.foodnotemini.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class DetailActivity extends AppCompatActivity {

    APIInterface apiInterface;

    TextView titleTv;
    TextView addrTv;
    TextView phoneTv;
    TextView contentTv;
    TextView btnDetail;
    ViewPager viewPager;
    CircleIndicator indicator;

    String contents_srl;

    double latitude = -1;
    double longitude = -1;

    List<String> imageList = new ArrayList<>();
    FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        indicator = (CircleIndicator) findViewById(R.id.indicator);
        titleTv = (TextView)findViewById(R.id.title);
        addrTv = (TextView)findViewById(R.id.addr);
        phoneTv = (TextView)findViewById(R.id.phone);
        contentTv = (TextView)findViewById(R.id.content);
        btnDetail = (TextView)findViewById(R.id.btn_detail);
        viewPager = (ViewPager)findViewById(R.id.viewpager);



        if(getIntent() != null){

            titleTv.setText(getIntent().getStringExtra("title"));
            addrTv.setText(getIntent().getStringExtra("place"));
            phoneTv.setText(getIntent().getStringExtra("phone"));
            contentTv.setText(getIntent().getStringExtra("contents"));
            imageList = getIntent().getStringArrayListExtra("images");
            contents_srl = getIntent().getStringExtra("contents_srl");

//            System.out.println("imageList ::: "  + imageList.size());
//            System.out.println("contents_srl ::: "  + contents_srl);
        }

        adapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        adapter.registerDataSetObserver(indicator.getDataSetObserver());

        viewPager.setPageTransformer(true, new DepthPageTransformer());

        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isPackageInstalled("com.hansigan.foodnote", DetailActivity.this)){
                    Intent sendIntent =   getPackageManager().getLaunchIntentForPackage("com.hansigan.foodnote");
                    sendIntent.putExtra("push_code", "PUSH_TIMELINE");
                    sendIntent.putExtra("contents_srl", contents_srl);
                    sendIntent.putExtra("scrap_yn", "N");
                    startActivity(sendIntent);
                }else{

                    foodnoteInstallDialog();
                }
            }
        });

        // back 버튼
        (findViewById(R.id.back_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // copy
        (findViewById(R.id.btn_copy)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setClipBoardLink(DetailActivity.this, addrTv.getText().toString());
            }
        });
    }

    public class DepthPageTransformer implements ViewPager.PageTransformer {

        public void transformPage(View view, float position) {
            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page

            } else if (position <= 1) { // (0,1]
                // Fade the page out.

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.

            }
        }
    }

    private boolean isPackageInstalled(String packagename, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private class FragmentAdapter extends FragmentStatePagerAdapter {
        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            Fragment f = new ImageFragment();
            Bundle bundle = new Bundle();
//            bundle.putInt("position", position);
            bundle.putString("image", imageList.get(position));
            f.setArguments(bundle);
            return f;
        }
        @Override
        public int getCount() { return imageList.size(); }
    }


    /**
     * 클립보드에 주소 복사 기능
     * @param context
     * @param link
     */
    public void setClipBoardLink(Context context, String link){

        ClipboardManager clipboardManager = (ClipboardManager)context.getSystemService(context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("label", link);
        clipboardManager.setPrimaryClip(clipData);
        Snackbar.make(addrTv, "주소가 복사되었습니다.", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }


    public void foodnoteInstallDialog() {
        new AlertDialog.Builder(DetailActivity.this)
//                .setTitle("서비스 사용을 위한 접근권한 안내")
                .setMessage("자세히 보기는 뭐먹지!? 앱에서 이용가능합니다. \n다양한 맛집 정보를 보시려면 뭐먹지!? 앱을 설치 후 이용해주세요.")
                .setCancelable(false)
//                                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        // do nothing
//                                    }
//                                })
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete


                        // 구글플레이로 연결
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.hansigan.foodnote")));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.hansigan.foodnote")));
                        }
                    }
                })
//                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
