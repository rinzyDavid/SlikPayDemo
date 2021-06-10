package com.slikpay.data;

public class Gateway {
    private String code;
    private String label;
    private String method;
    private String grouping;
    private GatewayLink link;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }

    public GatewayLink getLink() {
        return link;
    }

    public void setLink(GatewayLink link) {
        this.link = link;
    }
}
