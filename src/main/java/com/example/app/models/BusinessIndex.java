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
    private Long startTime;
    /**
     * avgTime.
     */
    private Double avgTime;
    /**
     * num.
     */
    private Long num;
    /**
     * succeeNum.
     */
    private Long succeeNum;
    /**
     * succeeRate.
     */
    private Double succeeRate;

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
        return getNo() == model.getNo() && objectEquals(serviceName, model.getServiceName())
            && objectEquals(startTime, model.getStartTime()) && objectEquals(avgTime, model.getAvgTime())
            && objectEquals(num, model.getNum()) && objectEquals(succeeNum, model.getSucceeNum())
            && objectEquals(succeeRate, model.getSucceeRate());
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

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(final Long startTime) {
        this.startTime = startTime;
    }

    public Double getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(final Double avgTime) {
        this.avgTime = avgTime;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(final Long num) {
        this.num = num;
    }

    public Long getSucceeNum() {
        return succeeNum;
    }

    public void setSucceeNum(final Long succeeNum) {
        this.succeeNum = succeeNum;
    }

    public Double getSucceeRate() {
        return succeeRate;
    }

    public void setSucceeRate(final Double succeeRate) {
        this.succeeRate = succeeRate;
    }

}
