package com.example.app.models;

public final class BusinessIndex extends Model {

    /**
     * Names of the tables who use this model.
     */
    public static final String[] TABLE_NAMES = {
        "esb",
    };

    /**
     * Key.
     */
    private long id = -1;
    /**
     * Name of service.
     */
    private String serviceName;
    /**
     * startTime.
     */
    private long startTime;
    /**
     * avgTime.
     */
    private double avgTime;
    /**
     * num.
     */
    private long num;
    /**
     * succeeNum.
     */
    private long succeeNum;
    /**
     * succeeRate.
     */
    private double succeeRate;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(final String serviceName) {
        this.serviceName = serviceName;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(final long startTime) {
        this.startTime = startTime;
    }

    public double getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(final double avgTime) {
        this.avgTime = avgTime;
    }

    public long getNum() {
        return num;
    }

    public void setNum(final long num) {
        this.num = num;
    }

    public long getSucceeNum() {
        return succeeNum;
    }

    public void setSucceeNum(final long succeeNum) {
        this.succeeNum = succeeNum;
    }

    public double getSucceeRate() {
        return succeeRate;
    }

    public void setSucceeRate(final double succeeRate) {
        this.succeeRate = succeeRate;
    }

}
