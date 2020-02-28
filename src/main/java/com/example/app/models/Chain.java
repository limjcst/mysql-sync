package com.example.app.models;

public class Chain extends Model {

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

}
