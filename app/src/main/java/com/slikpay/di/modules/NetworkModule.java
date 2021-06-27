package com.slikpay.di.modules;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.slikpay.api.NetworkApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * NetworkModule Class - Hilt module for injecting retrofit dependency
 * which creates and initializes NetworkApi for making network calls
 */

@Module
@InstallIn(SingletonComponent.class)
public final class NetworkModule {


    static String baseUrl = "https://raw.githubusercontent.com";

    /**
     *Provides application cache
     * @param application Application context injected using Hilt qualifiers
     * @return Cache object
     */
    //Initializes cache
    @Provides
    static Cache provideOkHttpCache(@ApplicationContext Context application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB

        return new Cache(application.getCacheDir(), cacheSize);
    }

    /**
     * Provides Gson Object
     * @return
     */
    @Provides
   static Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .setLenient();
        return gsonBuilder.create();
    }

    @Provides
   static OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);
        return client.build();
    }

    /**
     * Provides NetworkApi retrofit class for communicating with apis
     * @param gson Gson object
     * @param okHttpClient Okhttpclient object
     * @return Networkapi Retrofit object
     */
    @Provides
   static NetworkApi provideNetworkApi(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                //.addConverterFactory(ScalarsConverterFactory.create()) //important
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
        return retrofit.create(NetworkApi.class);
    }
}
