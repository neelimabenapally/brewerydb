package com.example.sudhamshareddy.randombeers.InterfacesUsed;

import com.example.sudhamshareddy.randombeers.BeerRepo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import java.util.Map;
import java.util.*;

import retrofit2.http.*;
import retrofit2.Callback;


/**
 * Created by sudhamshareddy on 23/05/2017.
 */

public interface Fetchbeers {
    @GET("/v2/beer/random")


    Call<BeerRepo> getBeerlist(@QueryMap  Map<String, String> params);




}
