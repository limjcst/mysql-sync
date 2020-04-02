package com.example.app.models;

public final class PlatformIndex extends Model {

    /**
     * Names of the tables who use this model.
     */
    public static final String[] TABLE_NAMES = {
        "os_linux",
        "db_oracle_11g",
        "mw_redis",
        "mw_activemq",
        "dcos_container",
        "dcos_docker",
    };

    /**
     * id for item.
     */
    private long itemId;
    /**
     * name.
     */
    private String name;
    /**
     * id for bomc.
     */
    private String bomcId;
    /**
     * timestamp.
     */
    private long timestamp;
    /**
     * value.
     */
    private String value;
    /**
     * id for cmdb.
     */
    private String cmdbId;

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
    public boolean equals(final PlatformIndex obj) {
        return ((Model) this).equals((Model) obj) && itemId == obj.getItemId()
            && timestamp == obj.getTimestamp() && stringEquals(name, obj.getName())
            && stringEquals(bomcId, obj.getBomcId()) && stringEquals(cmdbId, obj.getCmdbId())
            && stringEquals(value, obj.getValue());
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(final long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getBomcId() {
        return bomcId;
    }

    public void setBomcId(final String bomcId) {
        this.bomcId = bomcId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final long timestamp) {
        this.timestamp = timestamp;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public String getCmdbId() {
        return cmdbId;
    }

    public void setCmdbId(final String cmdbId) {
        this.cmdbId = cmdbId;
    }

}
