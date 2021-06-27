package com.slikpay.data;

import java.util.List;

public class GatewayData {

    private List<Gateway> gateways;
    private String error;

    public List<Gateway> getGateways() {
        return gateways;
    }

    public void setGateways(List<Gateway> gateways) {
        this.gateways = gateways;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
