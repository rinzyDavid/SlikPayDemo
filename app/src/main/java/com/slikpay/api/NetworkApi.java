package com.slikpay.api;

import com.slikpay.dataManager.ApiResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NetworkApi {

    @GET("/optile/checkout-android/develop/shared-test/lists/listresult.json")
    Observable<ApiResponse> listPaymentGateway();
}
