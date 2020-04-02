package com.example.app.models;

public class Chain extends Model {

    /**
     * Names of the tables who use this model.
     */
    public static final String[] TABLE_NAMES = {
    };

    /**
     * Key.
     */
    private long no = -1;
    /**
     * Start time.
     */
    private long startTime;
    /**
     * Elapsed time.
     */
    private double elapsedTime;
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
     * @return id.
     */
    public int hashCode() {
        return (int) no;
    }

    /**
     * Compare with another model.
     * @param obj Another model.
     * @return Whether they are equal.
     */
    public boolean equals(final Chain obj) {
        return no == obj.getNo() && startTime == obj.getStartTime()
            && elapsedTime == obj.getElapsedTime() && stringEquals(success, obj.getSuccess())
            && stringEquals(traceId, obj.getTraceId()) && stringEquals(id, obj.getId())
            && stringEquals(pid, obj.getPid()) && stringEquals(cmdbId, obj.getCmdbId());
    }

    public final long getNo() {
        return no;
    }

    public final void setNo(final long no) {
        this.no = no;
    }

    public final long getStartTime() {
        return startTime;
    }

    public final void setStartTime(final long startTime) {
        this.startTime = startTime;
    }

    public final double getElapsedTime() {
        return elapsedTime;
    }

    public final void setElapsedTime(final double elapsedTime) {
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
