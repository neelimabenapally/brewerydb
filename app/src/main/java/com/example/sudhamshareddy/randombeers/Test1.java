package com.example.sudhamshareddy.randombeers;

/**
 * Created by sudhamshareddy on 23/05/2017.
 */
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.*;
import android.util.*;
import java.util.*;
import java.util.concurrent.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;


public class Test1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Val v1 = new Val();
        v1.execute();
    }

    public class Val extends AsyncTask<String, Void, String> {

        ArrayList<String> a1= new ArrayList<>();
        @Override
        protected String doInBackground(String... params) {

            HttpUrl url = new HttpUrl.Builder()
                    .scheme("https")
                    .host("api.brewerydb.com")
                    .addPathSegments("v2/beers")
                   // .addPathSegment("beers")
                    .addQueryParameter("key", "2f2037cd30051cf4fab93a0245f82f1f")
                    .addQueryParameter("format","json")
                    .addQueryParameter("availableId","1")
                    .build();
            System.out.println("url is"+url);

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(url).build();

            try {
                Response res= client.newCall(request).execute();
                String jsonData = res.body().string();
                JSONObject Jobject = new JSONObject(jsonData);
                JSONArray Jarray = Jobject.getJSONArray("data");

                for (int i = 0; i < Jarray.length(); i++) {
                    JSONObject object     = Jarray.getJSONObject(i);

                    a1.add(String.valueOf(object));

                }

                Log.d("op is",a1.toString());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


    }
}
