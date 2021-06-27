package com.slikpay.dataManager;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("networks")
    @Expose
    private GatewayMap dataMap;

    public GatewayMap getDataMap() {
        return dataMap;
    }

    public void setDataMap(GatewayMap dataMap) {
        this.dataMap = dataMap;
    }
}
