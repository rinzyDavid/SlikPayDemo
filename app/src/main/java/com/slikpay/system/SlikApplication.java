package com.slikpay.system;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class SlikApplication extends Application {

   // protected ApplicationComponent appComponent ;

    public void onCreate() {
        super.onCreate();
        //appComponent = DaggerApplicationComponent.builder()
        //        .applicationModule(new ApplicationModule(this))
        //        .networkModule(new NetworkModule(getResources().getString(R.string.payment_api)))


            //    .build();

       // appComponent.inject(this);


    }
/*
    public ApplicationComponent getAppComponent(){
        return appComponent;
    }

 */
}
