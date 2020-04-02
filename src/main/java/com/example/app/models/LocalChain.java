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

    /**
     * Compare with another object.
     * @param obj Another object.
     * @return Whether they are equal.
     */
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        return equals(this.getClass().cast(obj));
    }

    /**
     * hashCode.
     * @return no.
     */
    public int hashCode() {
        return (int) getNo();
    }

    /**
     * Compare with another model.
     * @param obj Another model.
     * @return Whether they are equal.
     */
    public boolean equals(final LocalChain obj) {
        return ((Chain) this).equals((Chain) obj) && stringEquals(dsName, obj.getDsName())
             && stringEquals(serviceName, obj.getServiceName());
    }

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
