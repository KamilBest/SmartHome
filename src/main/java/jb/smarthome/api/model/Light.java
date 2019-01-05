package jb.smarthome.api.model;

public class Light {
    private String room;
    private boolean isOn;
    private int pin;

    public Light() {
    }

    public Light(String room, boolean isOn, int pin) {
        this.room = room;
        this.isOn = isOn;
        this.pin = pin;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public boolean getIsOn() {
        return isOn;
    }

    public void setIsOn(boolean isOn) {
        isOn = isOn;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "Light{" +
                "room='" + room + '\'' +
                ", isOn=" + isOn +
                ", pin=" + pin +
                '}';
    }
}
