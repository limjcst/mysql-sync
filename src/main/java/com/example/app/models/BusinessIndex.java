package com.example.app.models;

public final class BusinessIndex extends Model {

    /**
     * Names of the tables who use this model.
     */
    public static final String[] TABLE_NAMES = {
        "esb",
    };

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
        BusinessIndex model = (BusinessIndex) obj;
        return getNo() == model.getNo() && stringEquals(serviceName, model.getServiceName())
            && startTime == model.getStartTime() && avgTime == model.getAvgTime()
            && num == model.getNum() && succeeNum == model.getSucceeNum()
            && succeeRate == model.getSucceeRate();
    }

    /**
     * hashCode.
     * @return no.
     */
    public int hashCode() {
        return (int) getNo();
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
