package com.wjk.foodnotemini.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wjk on 2018. 5. 15..
 */

public class Bean {

    public String stat;


    public response response;

    public String getStat() {
        return stat;
    }

    public Bean.response getResponse() {
        return response;
    }

    public class response{


        public String title;
        public String contents_srl;
        public String header;
        public String contents;
        public String phone;
        public String place;
        public String lat;
        public String lng;

        public List<String> images = new ArrayList<>();

        public String getTitle() {
            return title;
        }

        public String getContents_srl() {
            return contents_srl;
        }

        public String getHeader() {
            return header;
        }

        public String getContents() {
            return contents;
        }

        public String getPhone() {
            return phone;
        }

        public String getPlace() {
            return place;
        }

        public String getLat() {
            return lat;
        }

        public String getLng() {
            return lng;
        }

        public List<String> getImages() {
            return images;
        }
    }
}
