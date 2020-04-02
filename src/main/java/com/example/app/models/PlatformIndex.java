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
    private Long itemId;
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
    private Long timestamp;
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
        PlatformIndex model = (PlatformIndex) obj;
        return getNo() == model.getNo() && objectEquals(itemId, model.getItemId())
            && objectEquals(timestamp, model.getTimestamp()) && objectEquals(name, model.getName())
            && objectEquals(bomcId, model.getBomcId()) && objectEquals(cmdbId, model.getCmdbId())
            && objectEquals(value, model.getValue());
    }

    /**
     * hashCode.
     * @return no.
     */
    public int hashCode() {
        return (int) getNo();
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(final Long itemId) {
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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Long timestamp) {
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
