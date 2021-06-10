package com.slikpay;

import android.content.Context;

import com.slikpay.di.component.ApplicationComponent;
import com.slikpay.di.modules.ActivityModule;
import com.slikpay.di.modules.ApplicationModule;
import com.slikpay.di.modules.NetworkModule;
import com.slikpay.system.SlikApplication;

import org.junit.Before;

import javax.inject.Singleton;

import dagger.Component;

import static org.mockito.Mockito.mock;

public class BaseUnitTest extends SlikApplication {

    TestApplicationComponent component;


    Context context = mock(Context.class);
    String baseurl = "https://raw.githubusercontent.com";


    @Before
    public void setUp() throws Exception {

        component = DaggerTestApplicationComponent.builder()
                .applicationModule(new TestApplicationModule(this))
                .testNetworkModule(new TestNetworkModule(baseurl))
                .build();
        component.inject(this);
    }

    public TestApplicationComponent getAppComponent(){
        return component;
    }

}
