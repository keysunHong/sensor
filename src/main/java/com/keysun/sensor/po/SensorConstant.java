package com.keysun.sensor.po;

/**
 * Created by hong on 2017/11/5.
 */
public class SensorConstant {

    private long id;
    private String constant_type;
    private double constant_low;
    private double constant_hight;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConstant_type() {
        return constant_type;
    }

    public void setConstant_type(String constant_type) {
        this.constant_type = constant_type;
    }

    public double getConstant_low() {
        return constant_low;
    }

    public void setConstant_low(double constant_low) {
        this.constant_low = constant_low;
    }

    public double getConstant_hight() {
        return constant_hight;
    }

    public void setConstant_hight(double constant_hight) {
        this.constant_hight = constant_hight;
    }


    @Override
    public String toString() {
        return "SensorConstant{" +
                "id=" + id +
                ", constant_type='" + constant_type + '\'' +
                ", constant_low=" + constant_low +
                ", constant_hight=" + constant_hight +
                '}';
    }
}
