package com.slikpay.view.viewmodel;

import androidx.lifecycle.ViewModel;

import com.slikpay.callback.GatewayCallback;
import com.slikpay.dataManager.GatewayManager;

/**
 *
 * Viewmodel object: Holds data for the payment view
 */
public class PaymentOptionVM extends ViewModel {


    private GatewayManager gatewayManager;

    public void setGatewayManager(GatewayManager gatewayManager) {
        this.gatewayManager = gatewayManager;
    }


    public void listGateWays(GatewayCallback callback){

        gatewayManager.listGateway(callback);
    }
}
