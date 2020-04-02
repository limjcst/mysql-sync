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
     * Key.
     */
    private long id = -1;
    /**
     * id for item.
     */
    private long itemId;
    /**
     * name.
     */
    private String name;
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
     * @return id.
     */
    public int hashCode() {
        return (int) id;
    }

    /**
     * Compare with another model.
     * @param obj Another model.
     * @return Whether they are equal.
     */
    public boolean equals(final PlatformIndex obj) {
        return id == obj.getId() && itemId == obj.getItemId() && stringEquals(name, obj.getName())
            && timestamp == obj.getTimestamp() && stringEquals(value, obj.getValue())
            && stringEquals(cmdbId, obj.getCmdbId());
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
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
