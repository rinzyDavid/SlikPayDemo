package com.slikpay.callback;

import com.slikpay.data.Gateway;

import java.util.List;

public interface GatewayCallback {
    void onGatewayList(List<Gateway> gateways);
    void onError(String error);
}
