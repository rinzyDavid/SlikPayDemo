package com.slikpay.system;

import android.app.Application;

import com.slikpay.R;
import com.slikpay.di.component.ApplicationComponent;
import com.slikpay.di.component.DaggerApplicationComponent;
import com.slikpay.di.modules.ApplicationModule;
import com.slikpay.di.modules.NetworkModule;

public class SlikApplication extends Application {

    protected ApplicationComponent appComponent ;

    public void onCreate() {
        super.onCreate();
        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(getResources().getString(R.string.payment_api)))


                .build();

        appComponent.inject(this);


    }

    public ApplicationComponent getAppComponent(){
        return appComponent;
    }
}
