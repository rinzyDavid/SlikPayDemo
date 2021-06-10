package com.slikpay;

import com.slikpay.api.NetworkApi;
import com.slikpay.dataManager.GatewayManager;
import com.slikpay.di.component.ActivityComponent;
import com.slikpay.di.component.ApplicationComponent;
import com.slikpay.di.modules.ActivityModule;
import com.slikpay.di.modules.ApplicationModule;
import com.slikpay.di.modules.NetworkModule;
import com.slikpay.repository.GateWayRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, TestNetworkModule.class, ActivityModule.class})
public interface TestApplicationComponent extends ApplicationComponent {

    NetworkApi networkApi();
    GateWayRepository repository();
    GatewayManager gatewayManager();
    void inject(BaseUnitTest test);

    TestActivityComponent.TestFactory testComponentFactory();
}
