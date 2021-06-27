package com.slikpay.dataManager;

import androidx.lifecycle.MutableLiveData;

import com.slikpay.api.NetworkApi;
import com.slikpay.data.GatewayData;
import com.slikpay.repository.GatewayService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * GatewayService Implementation class. Depends on NetworkApi Retrofit class for api communication.
 * Dependency injected by Hilt.
 */

public class GatewayServiceImpl implements GatewayService {


   @Inject
    NetworkApi networkApi; // Injected by Hilt for api communication
   private final CompositeDisposable disposable;

   //using constructor injection to inject NetworkApi dependency
   @Inject
   public GatewayServiceImpl(NetworkApi networkApi){
       this.networkApi = networkApi;
       disposable = new CompositeDisposable();
   }

    /**
     * Lists payment gateways available. Thread is managed using RxJava observable
     * @return MutableLiveData
     */
    @Override
    public MutableLiveData<GatewayData> listPaymentGateway() {


       MutableLiveData<GatewayData> response = new MutableLiveData<>();

       disposable.add(networkApi.listPaymentGateway()
       .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(apiResponse -> {
                       GatewayData gatewayData = new GatewayData();
                       GatewayMap dataMap = apiResponse.getDataMap();
                       gatewayData.setGateways(dataMap.getGateways());
                       response.setValue(gatewayData);

       }, throwable -> {
           //throwable.printStackTrace();
                       GatewayData gatewayData = new GatewayData();
                       String error = new ApiException(throwable).getError();
                       gatewayData.setError(error);
       }));




        return response;
    }

@Override
    public void cleanup(){
       if(disposable!= null && !disposable.isDisposed())
           disposable.dispose();

    }
}
