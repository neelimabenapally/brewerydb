package com.example.sudhamshareddy.randombeers;

import android.media.Image;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sudhamshareddy on 23/05/2017.
 */

public class BeerRepo {


        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("data")
        @Expose
        private DataList data;
        @SerializedName("status")
        @Expose
        private String status;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public DataList getData() {
            return data;
        }

        public void setData(DataList data) {
            this.data = data;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
