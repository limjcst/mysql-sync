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
     * stack.
     */
    private String stack;
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

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(final String stack) {
        this.stack = stack;
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
