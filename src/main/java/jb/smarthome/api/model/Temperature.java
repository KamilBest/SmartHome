package jb.smarthome.api.model;

public class Temperature {
    private String room;
    private Double temperature;
    private Double humidity;

    public Temperature() {
    }

    public Temperature(String room, Double temperature, Double humidity) {
        this.room = room;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "room='" + room + '\'' +
                ", temperature='" + temperature + '\'' +
                ", humidity='" + humidity + '\'' +
                '}';
    }
}
