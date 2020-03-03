package com.example.app.models;

public final class DSChain extends Chain {

    /**
     * Names of the tables who use this model.
     */
    public static final String[] TABLE_NAMES = {
        "trace_jdbc",
        "trace_local",
    };

    /**
     * Name of ds.
     */
    private String dsName;

    public String getDsName() {
        return dsName;
    }

    public void setDsName(final String dsName) {
        this.dsName = dsName;
    }

}
