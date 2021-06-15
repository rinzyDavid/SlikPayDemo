package com.slikpay.repository;


import com.slikpay.api.NetworkApi;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 *
 * GatewayRepository class - This handles network api calls to retrieve data
 * from server. Depends NetworkApi interface.
 */

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
