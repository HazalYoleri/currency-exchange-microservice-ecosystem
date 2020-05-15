package com.spring.cloud.limitservice.bean;

public class LimitConfiguration {
    private int min;
    private int max;

    protected LimitConfiguration() {

    }


    public LimitConfiguration(int min, int max) {
        super();
        this.min = min;
        this.max = max;
    }

    public int getMax() {
        return max;

    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
