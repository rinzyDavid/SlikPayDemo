package com.slikpay;

import com.google.gson.Gson;
import com.slikpay.api.NetworkApi;
import com.slikpay.repository.GateWayRepository;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MokRepository  {

    private NetworkApi networkApi;
    public MokRepository(String baseUrl){


        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create()) //important
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .client(new OkHttpClient.Builder().build())
                .build();

        networkApi = retrofit.create(NetworkApi.class);



    }




}
