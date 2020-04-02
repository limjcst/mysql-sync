package com.example.app.models;

public final class ServiceChain extends Chain {

    /**
     * Names of the tables who use this model.
     */
    public static final String[] TABLE_NAMES = {
        "trace_csf",
        "trace_osb",
        "trace_remote_process",
        "trace_fly_remote",
    };

    /**
     * Name of service.
     */
    private String serviceName;

    /**
     * Compare with another object.
     * @param obj Another object.
     * @return Whether they are equal.
     */
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        ServiceChain model = (ServiceChain) obj;
        return ((Chain) this).equals((Chain) model) && stringEquals(serviceName, model.getServiceName());
    }

    /**
     * hashCode.
     * @return no.
     */
    public int hashCode() {
        return (int) getNo();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(final String serviceName) {
        this.serviceName = serviceName;
    }

}
