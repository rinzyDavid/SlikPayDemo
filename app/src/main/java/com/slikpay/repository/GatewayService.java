package com.slikpay.repository;

import androidx.lifecycle.MutableLiveData;

import com.slikpay.data.GatewayData;

public interface GatewayService {

    MutableLiveData<GatewayData>listPaymentGateway();
    void cleanup();
}
