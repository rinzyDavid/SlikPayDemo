package com.slikpay.di.component;

import android.app.Application;
import android.content.Context;

import com.slikpay.api.NetworkApi;
import com.slikpay.di.anons.ApplicationContext;
import com.slikpay.di.modules.ActivityModule;
import com.slikpay.di.modules.ApplicationModule;
import com.slikpay.di.modules.NetworkModule;
import com.slikpay.system.SlikApplication;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, ActivityModule.class})
public interface ApplicationComponent {

    void inject(SlikApplication application);

    @ApplicationContext
    Context getContext();

    NetworkApi networkApi();
    Application getApplication();

    ActivityComponent.Factory activityComponentFactory();
}
