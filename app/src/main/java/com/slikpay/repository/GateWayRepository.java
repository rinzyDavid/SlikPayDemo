package com.slikpay.repository;


import com.slikpay.api.NetworkApi;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GateWayRepository {

    @Inject
    protected NetworkApi networkApi;
    @Inject
    public GateWayRepository(NetworkApi networkApi){
        this.networkApi = networkApi;
    }

    public Observable<String> listPaymentGateway(){
        return networkApi.getPaymentGateway();
    }
}
