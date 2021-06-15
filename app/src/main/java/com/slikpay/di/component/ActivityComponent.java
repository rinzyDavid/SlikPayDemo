package com.slikpay.di.component;

import com.slikpay.di.anons.ActivityScope;
import com.slikpay.view.payment.PaymentOptionActivity;

import dagger.Subcomponent;

/**
 * ActivityComponent - Dagger Subcomponent for injecting dependencies into
 * activities and fragments
 *
 */

@Subcomponent
@ActivityScope
public interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        ActivityComponent create();
    }

    //Inject dagger managed dependencies to activity and fragment
    void inject(PaymentOptionActivity activity);
}
