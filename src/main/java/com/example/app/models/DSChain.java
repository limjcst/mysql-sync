package com.example.app.models;

public final class DSChain extends Chain {

    /**
     * Names of the tables who use this model.
     */
    public static final String[] TABLE_NAMES = {
        "trace_jdbc",
    };

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
     * @return id.
     */
    public int hashCode() {
        return (int) getNo();
    }

    /**
     * Compare with another model.
     * @param obj Another model.
     * @return Whether they are equal.
     */
    public boolean equals(final DSChain obj) {
        return ((Chain) this).equals((Chain) obj) && stringEquals(dsName, obj.getDsName());
    }

    public String getDsName() {
        return dsName;
    }

    public void setDsName(final String dsName) {
        this.dsName = dsName;
    }

}
