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
    public boolean equals(final BusinessIndex obj) {
        return id == obj.getId() && stringEquals(serviceName, obj.getServiceName())
            && startTime == obj.getStartTime() && avgTime == obj.getAvgTime()
            && num == obj.getNum() && succeeNum == obj.getSucceeNum()
            && succeeRate == obj.getSucceeRate();
    }

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
