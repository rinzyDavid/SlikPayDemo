package com.slikpay.repository;

import io.reactivex.Observable;

public interface GatewayRepo {

    Observable<String> listPaymentGateway();
}
