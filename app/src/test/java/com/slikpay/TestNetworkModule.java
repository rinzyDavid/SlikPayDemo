package com.slikpay;

import com.google.gson.Gson;
import com.slikpay.api.NetworkApi;
import com.slikpay.di.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class TestNetworkModule  {

    String baseUrl;
    public TestNetworkModule(String baseUrl) {

        this.baseUrl = baseUrl;
    }


    @Provides
    @Singleton
    NetworkApi provideNetworkApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create()) //important
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .client( new OkHttpClient.Builder().build())
                .build();
        return retrofit.create(NetworkApi.class);
    }
}
