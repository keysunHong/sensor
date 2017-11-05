package com.keysun.sensor.po;

import java.sql.Timestamp;

/**
 * Created by hong on 2017/11/5.
 */
public class SensorState {

    private long id;
    private String magnetic_steel;
    private String train;
    private String brush;
    private String brush_reset;
    private String raindrop;
    private String temperature_state;
    private String light_state;
    private double temperature;
    private double light;
    private Timestamp update_time;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMagnetic_steel() {
        return magnetic_steel;
    }

    public void setMagnetic_steel(String magnetic_steel) {
        this.magnetic_steel = magnetic_steel;
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train;
    }

    public String getBrush() {
        return brush;
    }

    public void setBrush(String brush) {
        this.brush = brush;
    }

    public String getBrush_reset() {
        return brush_reset;
    }

    public void setBrush_reset(String brush_reset) {
        this.brush_reset = brush_reset;
    }

    public String getRaindrop() {
        return raindrop;
    }

    public void setRaindrop(String raindrop) {
        this.raindrop = raindrop;
    }

    public String getTemperature_state() {
        return temperature_state;
    }

    public void setTemperature_state(String temperature_state) {
        this.temperature_state = temperature_state;
    }

    public String getLight_state() {
        return light_state;
    }

    public void setLight_state(String light_state) {
        this.light_state = light_state;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getLight() {
        return light;
    }

    public void setLight(double light) {
        this.light = light;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "SensorState{" +
                "id=" + id +
                ", magnetic_steel='" + magnetic_steel + '\'' +
                ", train='" + train + '\'' +
                ", brush='" + brush + '\'' +
                ", brush_reset='" + brush_reset + '\'' +
                ", raindrop='" + raindrop + '\'' +
                ", temperature_state='" + temperature_state + '\'' +
                ", light_state='" + light_state + '\'' +
                ", temperature=" + temperature +
                ", light=" + light +
                ", update_time=" + update_time +
                '}';
    }
}