package jb.smarthome.api.model;

import java.util.ArrayList;
import java.util.List;

public class TemperatureResponse {
    private ArrayList<Temperature> temperatures;
    private Double avgTemperature;
    private Double avgHumidity;

    public TemperatureResponse() {
    }

    public TemperatureResponse(ArrayList<Temperature> temperatures, Double avgTemperature, Double avgHumidity) {
        this.temperatures = temperatures;
        this.avgTemperature = avgTemperature;
        this.avgHumidity = avgHumidity;
    }

    public ArrayList<Temperature> getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(ArrayList<Temperature> temperatures) {
        this.temperatures = temperatures;
    }

    public Double getAvgTemperature() {
        return avgTemperature;
    }

    public void setAvgTemperature(Double avgTemperature) {
        this.avgTemperature = avgTemperature;
    }

    public Double getAvgHumidity() {
        return avgHumidity;
    }

    public void setAvgHumidity(Double avgHumidity) {
        this.avgHumidity = avgHumidity;
    }

    @Override
    public String toString() {
        return "TemperatureResponse{" +
                "temperatures=" + temperatures +
                ", avgTemperature='" + avgTemperature + '\'' +
                ", avgHumidity='" + avgHumidity + '\'' +
                '}';
    }
}
