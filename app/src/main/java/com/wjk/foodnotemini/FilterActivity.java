package com.wjk.foodnotemini;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView backBtn;
    TextView btnConSearch;

//    RelativeLayout[] areaPrices = new RelativeLayout[3];
//    TextView[] textPrice = new TextView[3];

    RelativeLayout[] areaServices = new RelativeLayout[9];
    TextView[] textServices = new TextView[9];

    RelativeLayout[] areaSituations = new RelativeLayout[15];
    TextView[] textSituations = new TextView[15];


//    boolean isPrice[] = new boolean[3];
    boolean isService[] = new boolean[9];
    boolean isSituation[] = new boolean[15];

//    String priceStr = "";

    String serviceCategory;
    String situationCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);


        btnConSearch = (TextView) findViewById(R.id.btn_con_search);
        btnConSearch.setOnClickListener(this);
        backBtn = (ImageView)findViewById(R.id.back_btn);
        backBtn.setOnClickListener(this);

//        areaPrices[0] = (RelativeLayout) findViewById(R.id.area_price1);
//        areaPrices[1] = (RelativeLayout) findViewById(R.id.area_price2);
//        areaPrices[2] = (RelativeLayout) findViewById(R.id.area_price3);
//
//        textPrice[0] = (TextView) findViewById(R.id.price1);
//        textPrice[1] = (TextView) findViewById(R.id.price2);
//        textPrice[2] = (TextView) findViewById(R.id.price3);
//
//        areaPrices[0].setOnClickListener(this);
//        areaPrices[1].setOnClickListener(this);
//        areaPrices[2].setOnClickListener(this);

        areaServices[0] = (RelativeLayout) findViewById(R.id.area_service1);
        areaServices[1] = (RelativeLayout) findViewById(R.id.area_service2);
        areaServices[2] = (RelativeLayout) findViewById(R.id.area_service3);
        areaServices[3] = (RelativeLayout) findViewById(R.id.area_service4);
        areaServices[4] = (RelativeLayout) findViewById(R.id.area_service5);
        areaServices[5] = (RelativeLayout) findViewById(R.id.area_service6);
        areaServices[6] = (RelativeLayout) findViewById(R.id.area_service7);
        areaServices[7] = (RelativeLayout) findViewById(R.id.area_service8);
        areaServices[8] = (RelativeLayout) findViewById(R.id.area_service9);

        textServices[0] = (TextView) findViewById(R.id.service1);
        textServices[1] = (TextView) findViewById(R.id.service2);
        textServices[2] = (TextView) findViewById(R.id.service3);
        textServices[3] = (TextView) findViewById(R.id.service4);
        textServices[4] = (TextView) findViewById(R.id.service5);
        textServices[5] = (TextView) findViewById(R.id.service6);
        textServices[6] = (TextView) findViewById(R.id.service7);
        textServices[7] = (TextView) findViewById(R.id.service8);
        textServices[8] = (TextView) findViewById(R.id.service9);

        areaServices[0].setOnClickListener(this);
        areaServices[1].setOnClickListener(this);
        areaServices[2].setOnClickListener(this);
        areaServices[3].setOnClickListener(this);
        areaServices[4].setOnClickListener(this);
        areaServices[5].setOnClickListener(this);
        areaServices[6].setOnClickListener(this);
        areaServices[7].setOnClickListener(this);
        areaServices[8].setOnClickListener(this);


        areaSituations[0] = (RelativeLayout) findViewById(R.id.area_situation1);
        areaSituations[1] = (RelativeLayout) findViewById(R.id.area_situation2);
        areaSituations[2] = (RelativeLayout) findViewById(R.id.area_situation3);
        areaSituations[3] = (RelativeLayout) findViewById(R.id.area_situation4);
        areaSituations[4] = (RelativeLayout) findViewById(R.id.area_situation5);
        areaSituations[5] = (RelativeLayout) findViewById(R.id.area_situation6);
        areaSituations[6] = (RelativeLayout) findViewById(R.id.area_situation7);
        areaSituations[7] = (RelativeLayout) findViewById(R.id.area_situation8);
        areaSituations[8] = (RelativeLayout) findViewById(R.id.area_situation9);
        areaSituations[9] = (RelativeLayout) findViewById(R.id.area_situation10);
        areaSituations[10] = (RelativeLayout) findViewById(R.id.area_situation11);
        areaSituations[11] = (RelativeLayout) findViewById(R.id.area_situation12);
        areaSituations[12] = (RelativeLayout) findViewById(R.id.area_situation13);
        areaSituations[13] = (RelativeLayout) findViewById(R.id.area_situation14);
        areaSituations[14] = (RelativeLayout) findViewById(R.id.area_situation15);


        textSituations[0]  = (TextView) findViewById(R.id.situation1);
        textSituations[1]  = (TextView) findViewById(R.id.situation2);
        textSituations[2]  = (TextView) findViewById(R.id.situation3);
        textSituations[3]  = (TextView) findViewById(R.id.situation4);
        textSituations[4]  = (TextView) findViewById(R.id.situation5);
        textSituations[5]  = (TextView) findViewById(R.id.situation6);
        textSituations[6]  = (TextView) findViewById(R.id.situation7);
        textSituations[7]  = (TextView) findViewById(R.id.situation8);
        textSituations[8]  = (TextView) findViewById(R.id.situation9);
        textSituations[9]   = (TextView) findViewById(R.id.situation10);
        textSituations[10]  = (TextView) findViewById(R.id.situation11);
        textSituations[11]  = (TextView) findViewById(R.id.situation12);
        textSituations[12]  = (TextView) findViewById(R.id.situation13);
        textSituations[13]  = (TextView) findViewById(R.id.situation14);
        textSituations[14]  = (TextView) findViewById(R.id.situation15);

        areaSituations[0].setOnClickListener(this);
        areaSituations[1].setOnClickListener(this);
        areaSituations[2].setOnClickListener(this);
        areaSituations[3].setOnClickListener(this);
        areaSituations[4].setOnClickListener(this);
        areaSituations[5].setOnClickListener(this);
        areaSituations[6].setOnClickListener(this);
        areaSituations[7].setOnClickListener(this);
        areaSituations[8].setOnClickListener(this);
        areaSituations[9].setOnClickListener(this);
        areaSituations[10].setOnClickListener(this);
        areaSituations[11].setOnClickListener(this);
        areaSituations[12].setOnClickListener(this);
        areaSituations[13].setOnClickListener(this);
        areaSituations[14].setOnClickListener(this);


        if(getIntent() != null){
//            priceStr = getIntent().getStringExtra("price");
            serviceCategory = getIntent().getStringExtra("service");
            situationCategory = getIntent().getStringExtra("situation");

//            setPrice(priceStr);
            setService(serviceCategory);
            setSituation(situationCategory);
        }

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {

            case R.id.btn_con_search:
                Intent intent = new Intent();
//                intent.putExtra("price", priceStr);
                intent.putExtra("info", makeServiceCate().toString());
                intent.putExtra("place", makeStateCate().toString());


                int pc = 0;
                int sc = 0;
                int ic = 0;
//                for (int i = 0; i < isPrice.length; i++) {
//                    if(isPrice[i]) pc++;
//                }
                for (int i = 0; i < isService.length; i++) {
                    if(isService[i]) sc++;
                }
                for (int i = 0; i < isSituation.length; i++) {
                    if(isSituation[i]) ic++;
                }
//                System.out.println("price :: " + pc);
//                System.out.println("place :: " + sc);
//                System.out.println("info :: " + ic);

                intent.putExtra("info_count", pc+sc+ic);
                setResult(RESULT_OK, intent);
                finish();
                overridePendingTransition(R.anim.anim_slide_in_top, R.anim.anim_slide_out_bottom);

                break;
//
//
//            case R.id.area_price1:
//
//                if (isPrice[0]) {
//                    isPrice[0] = false;
//                    areaPrices[0].setBackgroundResource(R.drawable.bg_btn_addinfo_n);
//                    textPrice[0].setTextColor(Color.parseColor("#2a2a2a"));
//                    priceStr = "";
//                } else {
//                    isPrice[0] = true;
//                    isPrice[1] = false;
//                    isPrice[2] = false;
//                    areaPrices[0].setBackgroundResource(R.drawable.bg_btn_addinfo_p);
//                    textPrice[0].setTextColor(Color.parseColor("#ff4545"));
//                    areaPrices[1].setBackgroundResource(R.drawable.bg_btn_addinfo_n);
//                    textPrice[1].setTextColor(Color.parseColor("#2a2a2a"));
//                    areaPrices[2].setBackgroundResource(R.drawable.bg_btn_addinfo_n);
//                    textPrice[2].setTextColor(Color.parseColor("#2a2a2a"));
//                    priceStr = "PRICE01";
//                }
//
//                break;
//            case R.id.area_price2:
//
//                if (isPrice[1]) {
//                    isPrice[1] = false;
//                    areaPrices[1].setBackgroundResource(R.drawable.bg_btn_addinfo_n);
//                    textPrice[1].setTextColor(Color.parseColor("#2a2a2a"));
//                    priceStr = "";
//                } else {
//                    isPrice[0] = false;
//                    isPrice[1] = true;
//                    isPrice[2] = false;
//                    areaPrices[0].setBackgroundResource(R.drawable.bg_btn_addinfo_n);
//                    textPrice[0].setTextColor(Color.parseColor("#2a2a2a"));
//                    areaPrices[1].setBackgroundResource(R.drawable.bg_btn_addinfo_p);
//                    textPrice[1].setTextColor(Color.parseColor("#ff4545"));
//                    areaPrices[2].setBackgroundResource(R.drawable.bg_btn_addinfo_n);
//                    textPrice[2].setTextColor(Color.parseColor("#2a2a2a"));
//                    priceStr = "PRICE02";
//                }
//                break;
//            case R.id.area_price3:
//
//                if (isPrice[2]) {
//                    isPrice[2] = false;
//                    areaPrices[2].setBackgroundResource(R.drawable.bg_btn_addinfo_n);
//                    textPrice[2].setTextColor(Color.parseColor("#2a2a2a"));
//                    priceStr = "";
//                } else {
//                    isPrice[0] = false;
//                    isPrice[1] = false;
//                    isPrice[2] = true;
//                    areaPrices[0].setBackgroundResource(R.drawable.bg_btn_addinfo_n);
//                    textPrice[0].setTextColor(Color.parseColor("#2a2a2a"));
//                    areaPrices[1].setBackgroundResource(R.drawable.bg_btn_addinfo_n);
//                    textPrice[1].setTextColor(Color.parseColor("#2a2a2a"));
//                    areaPrices[2].setBackgroundResource(R.drawable.bg_btn_addinfo_p);
//                    textPrice[2].setTextColor(Color.parseColor("#4E8BD8"));
//                    priceStr = "PRICE03";
//                }
//                break;

            case R.id.area_service1:
                if (isService[0]) {
                    isService[0] = false;
                    areaServices[0].setBackgroundResource(R.drawable.bg_filter_n);
                    textServices[0].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isService[0] = true;
                    areaServices[0].setBackgroundResource(R.drawable.bg_filter_p);
                    textServices[0].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;

            case R.id.area_service2:
                if (isService[1]) {
                    isService[1] = false;
                    areaServices[1].setBackgroundResource(R.drawable.bg_filter_n);
                    textServices[1].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isService[1] = true;
                    areaServices[1].setBackgroundResource(R.drawable.bg_filter_p);
                    textServices[1].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;

            case R.id.area_service3:
                if (isService[2]) {
                    isService[2] = false;
                    areaServices[2].setBackgroundResource(R.drawable.bg_filter_n);
                    textServices[2].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isService[2] = true;
                    areaServices[2].setBackgroundResource(R.drawable.bg_filter_p);
                    textServices[2].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;

            case R.id.area_service4:
                if (isService[3]) {
                    isService[3] = false;
                    areaServices[3].setBackgroundResource(R.drawable.bg_filter_n);
                    textServices[3].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isService[3] = true;
                    areaServices[3].setBackgroundResource(R.drawable.bg_filter_p);
                    textServices[3].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;

            case R.id.area_service5:
                if (isService[4]) {
                    isService[4] = false;
                    areaServices[4].setBackgroundResource(R.drawable.bg_filter_n);
                    textServices[4].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isService[4] = true;
                    areaServices[4].setBackgroundResource(R.drawable.bg_filter_p);
                    textServices[4].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;

            case R.id.area_service6:
                if (isService[5]) {
                    isService[5] = false;
                    areaServices[5].setBackgroundResource(R.drawable.bg_filter_n);
                    textServices[5].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isService[5] = true;
                    areaServices[5].setBackgroundResource(R.drawable.bg_filter_p);
                    textServices[5].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;

            case R.id.area_service7:
                if (isService[6]) {
                    isService[6] = false;
                    areaServices[6].setBackgroundResource(R.drawable.bg_filter_n);
                    textServices[6].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isService[6] = true;
                    areaServices[6].setBackgroundResource(R.drawable.bg_filter_p);
                    textServices[6].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;

            case R.id.area_service8:
                if (isService[7]) {
                    isService[7] = false;
                    areaServices[7].setBackgroundResource(R.drawable.bg_filter_n);
                    textServices[7].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isService[7] = true;
                    areaServices[7].setBackgroundResource(R.drawable.bg_filter_p);
                    textServices[7].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;

            case R.id.area_service9:
                if (isService[8]) {
                    isService[8] = false;
                    areaServices[8].setBackgroundResource(R.drawable.bg_filter_n);
                    textServices[8].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isService[8] = true;
                    areaServices[8].setBackgroundResource(R.drawable.bg_filter_p);
                    textServices[8].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;


            case R.id.area_situation1:
                if (isSituation[0]) {
                    isSituation[0] = false;
                    areaSituations[0].setBackgroundResource(R.drawable.bg_filter_n);
                    textSituations[0].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isSituation[0] = true;
                    areaSituations[0].setBackgroundResource(R.drawable.bg_filter_p);
                    textSituations[0].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;

            case R.id.area_situation2:
                if (isSituation[1]) {
                    isSituation[1] = false;
                    areaSituations[1].setBackgroundResource(R.drawable.bg_filter_n);
                    textSituations[1].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isSituation[1] = true;
                    areaSituations[1].setBackgroundResource(R.drawable.bg_filter_p);
                    textSituations[1].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;

            case R.id.area_situation3:
                if (isSituation[2]) {
                    isSituation[2] = false;
                    areaSituations[2].setBackgroundResource(R.drawable.bg_filter_n);
                    textSituations[2].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isSituation[2] = true;
                    areaSituations[2].setBackgroundResource(R.drawable.bg_filter_p);
                    textSituations[2].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;

            case R.id.area_situation4:
                if (isSituation[3]) {
                    isSituation[3] = false;
                    areaSituations[3].setBackgroundResource(R.drawable.bg_filter_n);
                    textSituations[3].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isSituation[3] = true;
                    areaSituations[3].setBackgroundResource(R.drawable.bg_filter_p);
                    textSituations[3].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;

            case R.id.area_situation5:
                if (isSituation[4]) {
                    isSituation[4] = false;
                    areaSituations[4].setBackgroundResource(R.drawable.bg_filter_n);
                    textSituations[4].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isSituation[4] = true;
                    areaSituations[4].setBackgroundResource(R.drawable.bg_filter_p);
                    textSituations[4].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;

            case R.id.area_situation6:
                if (isSituation[5]) {
                    isSituation[5] = false;
                    areaSituations[5].setBackgroundResource(R.drawable.bg_filter_n);
                    textSituations[5].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isSituation[5] = true;
                    areaSituations[5].setBackgroundResource(R.drawable.bg_filter_p);
                    textSituations[5].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;

            case R.id.area_situation7:
                if (isSituation[6]) {
                    isSituation[6] = false;
                    areaSituations[6].setBackgroundResource(R.drawable.bg_filter_n);
                    textSituations[6].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isSituation[6] = true;
                    areaSituations[6].setBackgroundResource(R.drawable.bg_filter_p);
                    textSituations[6].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;

            case R.id.area_situation8:
                if (isSituation[7]) {
                    isSituation[7] = false;
                    areaSituations[7].setBackgroundResource(R.drawable.bg_filter_n);
                    textSituations[7].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isSituation[7] = true;
                    areaSituations[7].setBackgroundResource(R.drawable.bg_filter_p);
                    textSituations[7].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;

            case R.id.area_situation9:
                if (isSituation[8]) {
                    isSituation[8] = false;
                    areaSituations[8].setBackgroundResource(R.drawable.bg_filter_n);
                    textSituations[8].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isSituation[8] = true;
                    areaSituations[8].setBackgroundResource(R.drawable.bg_filter_p);
                    textSituations[8].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;

            case R.id.area_situation10:
                if (isSituation[9]) {
                    isSituation[9] = false;
                    areaSituations[9].setBackgroundResource(R.drawable.bg_filter_n);
                    textSituations[9].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isSituation[9] = true;
                    areaSituations[9].setBackgroundResource(R.drawable.bg_filter_p);
                    textSituations[9].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;

            case R.id.area_situation11:
                if (isSituation[10]) {
                    isSituation[10] = false;
                    areaSituations[10].setBackgroundResource(R.drawable.bg_filter_n);
                    textSituations[10].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isSituation[10] = true;
                    areaSituations[10].setBackgroundResource(R.drawable.bg_filter_p);
                    textSituations[10].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;

            case R.id.area_situation12:
                if (isSituation[11]) {
                    isSituation[11] = false;
                    areaSituations[11].setBackgroundResource(R.drawable.bg_filter_n);
                    textSituations[11].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isSituation[11] = true;
                    areaSituations[11].setBackgroundResource(R.drawable.bg_filter_p);
                    textSituations[11].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;

            case R.id.area_situation13:
                if (isSituation[12]) {
                    isSituation[12] = false;
                    areaSituations[12].setBackgroundResource(R.drawable.bg_filter_n);
                    textSituations[12].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isSituation[12] = true;
                    areaSituations[12].setBackgroundResource(R.drawable.bg_filter_p);
                    textSituations[12].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;

            case R.id.area_situation14:
                if (isSituation[13]) {
                    isSituation[13] = false;
                    areaSituations[13].setBackgroundResource(R.drawable.bg_filter_n);
                    textSituations[13].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isSituation[13] = true;
                    areaSituations[13].setBackgroundResource(R.drawable.bg_filter_p);
                    textSituations[13].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;

            case R.id.area_situation15:
                if (isSituation[14]) {
                    isSituation[14] = false;
                    areaSituations[14].setBackgroundResource(R.drawable.bg_filter_n);
                    textSituations[14].setTextColor(Color.parseColor("#2a2a2a"));
                } else {
                    isSituation[14] = true;
                    areaSituations[14].setBackgroundResource(R.drawable.bg_filter_p);
                    textSituations[14].setTextColor(Color.parseColor("#4E8BD8"));
                }
                break;

            case R.id.back_btn:
                finish();
                overridePendingTransition(R.anim.anim_slide_in_top, R.anim.anim_slide_out_bottom);
                break;

        }
    }


//    public JSONArray makePrice() {
//        JSONArray result = new JSONArray();
//        String situation;
//        for (int i = 0; i < isPrice.length; i++) {
//            if (isPrice[i]) {
//                situation = "PRICE0" + (i + 1);
//                result.put(situation);
//            }
//        }
//        return result;
//    }

    public JSONArray makeServiceCate() {
        JSONArray result = new JSONArray();
        String service;
        for (int i = 0; i < isService.length; i++) {
            if (isService[i]) {
                service = "SERVICE0" + (i + 1);
                result.put(service);
            }
        }
//        System.out.println(result.toString());
        return result;
    }

    public JSONArray makeStateCate() {
        JSONArray result = new JSONArray();
        String situation;
        for (int i = 0; i < isSituation.length; i++) {
            if (isSituation[i]) {
                if (i < 9)
                    situation = "STATE0" + (i + 1);
                else
                    situation = "STATE" + (i + 1);
                result.put(situation);
            }
        }
//        System.out.println(result.toString());
        return result;
    }



//    public void setPrice(String price){
//
//        if(price != null && price.length() > 0){
//            String replacePrice = price.replace("PRICE","");
//            int pr = Integer.parseInt(replacePrice) - 1;
//            isPrice[pr] = true;
//            areaPrices[pr].setBackgroundResource(R.drawable.bg_btn_addinfo_p);
//            textPrice[pr].setTextColor(Color.parseColor("#4E8BD8"));
//        }
//    }

    public void setService(String service){
//        System.out.println("service ::: " +service);

        if(service != null && service.length() > 0){

            try {
                JSONArray jsonArray = new JSONArray(service);

                for (int i = 0; i < jsonArray.length(); i++) {

                    String replaceSvc = jsonArray.get(i).toString().replace("SERVICE", "");
                    int st = Integer.parseInt(replaceSvc) - 1;
                    isService[st] = true;
                    areaServices[st].setBackgroundResource(R.drawable.bg_filter_p);
                    textServices[st].setTextColor(Color.parseColor("#4E8BD8"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public void setSituation(String situation){
//        System.out.println("situation ::: " +situation);

        if(situation != null && situation.length() > 0){

            try {
                JSONArray jsonArray = new JSONArray(situation);

                for (int i = 0; i < jsonArray.length(); i++) {

                    String replaceSvc = jsonArray.get(i).toString().replace("STATE", "");
                    int svc = Integer.parseInt(replaceSvc) - 1;
                    isSituation[svc] = true;
                    areaSituations[svc].setBackgroundResource(R.drawable.bg_filter_p);
                    textSituations[svc].setTextColor(Color.parseColor("#4E8BD8"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_top, R.anim.anim_slide_out_bottom);
    }
}
