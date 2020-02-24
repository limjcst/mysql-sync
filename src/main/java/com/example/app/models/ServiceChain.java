package com.example.app.models;

public final class ServiceChain extends Chain {

    /**
     * Names of the tables who use this model.
     */
    public static final String[] TABLE_NAMES = {
        "trace_csf",
        "trace_osb",
    };

    /**
     * Name of service.
     */
    private String serviceName;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(final String serviceName) {
        this.serviceName = serviceName;
    }

}
