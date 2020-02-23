package com.example.app.models;

public final class Event extends Model {

    /**
     * Key.
     */
    private long id = -1;
    /**
     * timestamp.
     */
    private long time;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(final long time) {
        this.time = time;
    }

}
