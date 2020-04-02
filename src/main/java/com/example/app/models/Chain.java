package com.example.app.models;

public class Chain extends Model {

    /**
     * Names of the tables who use this model.
     */
    public static final String[] TABLE_NAMES = {
    };

    /**
     * Message time.
     */
    private Long msgTime;
    /**
     * Start time.
     */
    private Long startTime;
    /**
     * Elapsed time.
     */
    private Double elapsedTime;
    /**
     * success.
     */
    private String success;
    /**
     * id for trace.
     */
    private String traceId;
    /**
     * id.
     */
    private String id;
    /**
     * pid.
     */
    private String pid;
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

    protected final boolean equals(final Chain obj) {
        return getNo() == obj.getNo() && objectEquals(msgTime, obj.getMsgTime())
            && objectEquals(startTime, obj.getStartTime()) && objectEquals(elapsedTime, obj.getElapsedTime())
            && objectEquals(success, obj.getSuccess()) && objectEquals(traceId, obj.getTraceId())
            && objectEquals(id, obj.getId()) && objectEquals(pid, obj.getPid())
            && objectEquals(cmdbId, obj.getCmdbId());
    }

    public final Long getMsgTime() {
        return msgTime;
    }

    public final void setMsgTime(final Long msgTime) {
        this.msgTime = msgTime;
    }

    public final Long getStartTime() {
        return startTime;
    }

    public final void setStartTime(final Long startTime) {
        this.startTime = startTime;
    }

    public final Double getElapsedTime() {
        return elapsedTime;
    }

    public final void setElapsedTime(final Double elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public final String getSuccess() {
        return success;
    }

    public final void setSuccess(final String success) {
        this.success = success;
    }

    public final String getTraceId() {
        return traceId;
    }

    public final void setTraceId(final String traceId) {
        this.traceId = traceId;
    }

    public final String getId() {
        return id;
    }

    public final void setId(final String id) {
        this.id = id;
    }

    public final String getPid() {
        return pid;
    }

    public final void setPid(final String pid) {
        this.pid = pid;
    }

    public final String getCmdbId() {
        return cmdbId;
    }

    public final void setCmdbId(final String cmdbId) {
        this.cmdbId = cmdbId;
    }

}
