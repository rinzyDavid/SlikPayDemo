package com.slikpay.api;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NetworkApi {


    @GET("/optile/checkout-android/develop/shared-test/lists/listresult.json")
    Observable<String> getPaymentGateway();
}
