package com.slikpay;

import com.slikpay.di.anons.ActivityScope;
import com.slikpay.di.component.ActivityComponent;

import dagger.Subcomponent;

@Subcomponent
@ActivityScope
public interface TestActivityComponent extends ActivityComponent {

    @Subcomponent.Factory
    interface TestFactory {
        TestActivityComponent create();
    }

    void inject(GateWayManagerTest test);
}
