package com.example.app.models;

public final class LocalChain extends Chain {

    /**
     * Names of the tables who use this model.
     */
    public static final String[] TABLE_NAMES = {
        "trace_local",
    };

    /**
     * Name of service.
     */
    private String serviceName;
    /**
     * Name of ds.
     */
    private String dsName;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(final String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDsName() {
        return dsName;
    }

    public void setDsName(final String dsName) {
        this.dsName = dsName;
    }

}
