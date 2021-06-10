package com.slikpay.di.modules;

import android.app.Application;
import android.content.Context;

import com.slikpay.di.anons.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application app) {
        mApplication = app;
    }

    @Singleton
    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Singleton
    @Provides
    Application provideApplication() {
        return mApplication;
    }
}
