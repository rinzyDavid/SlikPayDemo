package com.slikpay.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.slikpay.data.GatewayData;
import com.slikpay.repository.GatewayService;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 *
 * Viewmodel object: Holds data for the payment view. Hilt manages the injection of GatewayService dependency to
 * PaymentOptionVM.
 */
@HiltViewModel
public class PaymentOptionVM extends ViewModel {


    GatewayService gatewayService; // GatewayService injected by Hilt
    private MutableLiveData<GatewayData> responseData;


   @Inject
    public PaymentOptionVM(GatewayService gatewayService){
        this.gatewayService = gatewayService;
    }




    public LiveData<GatewayData> getGateWays(){
        responseData = gatewayService.listPaymentGateway();
        return responseData;
    }



    public void onCleared(){
       gatewayService.cleanup();
    }



}
