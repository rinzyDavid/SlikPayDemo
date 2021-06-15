package com.slikpay.dataManager;

import com.slikpay.callback.GatewayCallback;
import com.slikpay.data.Gateway;
import com.slikpay.data.GatewayLink;
import com.slikpay.di.anons.ActivityScope;
import com.slikpay.repository.GateWayRepository;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * GateWayManager - This class handles and makes the network calls, making use of GatewayRepository class.
 * Interprets the json result, and returns Gateway Data back.
 * This class makes use of RxJava to manage threads while making network calls.
 *
 */
@ActivityScope
public class GatewayManager {


    @Inject
    GateWayRepository repository;

    private CompositeDisposable disposable;

    @Inject
    public GatewayManager(GateWayRepository repository){
        this.repository = repository;
        disposable = new CompositeDisposable();
    }

    public void listGateway(GatewayCallback callback){

        disposable.add(repository.listPaymentGateway()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result ->{

            JSONObject resultObj = new JSONObject(result);

            JSONObject networkObj = resultObj.getJSONObject("networks");
            JSONArray arrayData =  networkObj.getJSONArray("applicable");
            List<Gateway> gateways = processData(arrayData);

            callback.onGatewayList(gateways);

        },throwable -> {

            String error = new ApiException(throwable).getError();
            callback.onError(error);

        }));
    }


    private List<Gateway> processData(JSONArray arrayData) {

        List<Gateway> gateways = new ArrayList<>();
        for(int i=0;i<arrayData.length();i++){

            try{
                JSONObject data = arrayData.getJSONObject(i);
                Gateway gateway = new Gateway();
                gateway.setCode(data.getString("code"));
                gateway.setGrouping(data.getString("grouping"));
                gateway.setLabel(data.getString("label"));
                gateway.setMethod(data.getString("method"));
                gateway.setLink(processGatewayLink(data.getJSONObject("links")));
                gateways.add(gateway);
            }catch(Exception e){

            }

        }
        return gateways;
    }

    private GatewayLink processGatewayLink(JSONObject links) {
        GatewayLink link = new GatewayLink();
        try{

            link.setLogo(links.getString("logo"));
            link.setDataSelf(links.getString("self"));
            link.setLanguage(links.getString("lang"));
            link.setOperation(links.getString("operation"));
        }catch(Exception e){

        }
        return link;
    }
}
