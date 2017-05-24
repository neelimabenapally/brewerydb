package com.example.sudhamshareddy.randombeers;

import android.os.AsyncTask;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.lang.reflect.Array;
import java.util.List;
import retrofit2.*;
import android.util.*;
import okhttp3.logging.HttpLoggingInterceptor;
import java.util.*;
import android.graphics.*;
import  java.io.*;
import  java.net.*;
import android.view.*;
import android.content.*;


import com.example.sudhamshareddy.randombeers.InterfacesUsed.Fetchbeers;

import static com.example.sudhamshareddy.randombeers.R.drawable.no_label;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView title= (TextView) findViewById(R.id.textView1);
        final ImageView label= (ImageView) findViewById(R.id.imageView1);
        final TextView description = (TextView) findViewById(R.id.textView4);
        Button next = (Button) findViewById(R.id.button);

        next.setOnClickListener(MainActivity.this);






        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);


        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl("https://api.brewerydb.com/")
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );


        Retrofit retrofit =
                builder
                        .client(
                                httpClient.build()
                        )
                        .build();


        Fetchbeers client =  retrofit.create(Fetchbeers.class);
        Log.d("Eg1","inside");

        Map<String, String> params = new HashMap<String, String>();
        params.put("key", "2f2037cd30051cf4fab93a0245f82f1f");
        params.put("format", "json");
        params.put("availableId", "1");

        // Fetch a list of the Github repositories.
        Call<BeerRepo> call =
                client.getBeerlist(params);


// Execute the call asynchronously. Get a positive or negative callback.


        call.enqueue(new Callback<BeerRepo>() {
            @Override public void onResponse(Call<BeerRepo> call, Response<BeerRepo> response) {
                System.out.println("Sud: " + response.body().getMessage());
               // Log.d("Message is",response.body().getMessage());
              //  Log.d("Name is:",response.body().getData().getName());
              //  Log.d("Description is:",response.body().getData().getDescription());



                //Log.d("Label is:",response.body().getData().getLabels().getMedium());

                if(response.body().getData().getName()!=null && !response.body().getData().getName().isEmpty() && !response.body().getData().getName().equals("null")){
                   // String name = response.body().getData().getName();
                    title.setText(response.body().getData().getName());
                }
                else{
                    title.setText("No Values!");
                }

                if(response.body().getData().getDescription()!=null && !response.body().getData().getDescription().isEmpty() && !response.body().getData().getDescription().equals("null")){
                   // String Description = response.body().getData().getDescription();
                    description.setText(response.body().getData().getDescription());
                }

                else {
                    description.setText("No Description");
                }

                if(response.body().getData().getLabels() != null  && !response.body().getData().getLabels().equals("null")) {

                    if (response.body().getData().getLabels().getMedium() != null && !response.body().getData().getLabels().getMedium().isEmpty() && !response.body().getData().getLabels().getMedium().equals("null")) {
                        // String Image = response.body().getData().getLabels().getMedium();

                        new ImageLoadTask(response.body().getData().getLabels().getMedium(), label).execute();

                    } else {
                        label.setImageResource(no_label);
                    }
                }
                else {
                    label.setImageResource(no_label);
                }

            }
            @Override public void onFailure(Call<BeerRepo> call, Throwable t) {
            }
        });



}

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(MainActivity.this,
                MainActivity.class);

        startActivity(intent);

    }


    public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private ImageView imageView;

        public ImageLoadTask(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
        }

    }

}
