package jb.smarthome.sensor;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class MotionSensor {
    final GpioController gpioController = GpioFactory.getInstance();
    private final GpioController gpioPIRMotionSensor = GpioFactory.getInstance();
    private final GpioPinDigitalInput pirMotionsensor;

    private boolean isMotionSensorStateHigh;
    public MotionSensor(Pin pin) {
        //RaspiPin.GPIO_02
        pirMotionsensor = gpioPIRMotionSensor.provisionDigitalInputPin(pin, PinPullResistance.PULL_DOWN);
    }


    public void setMotionSensorListener()
    {
        pirMotionsensor.addListener((GpioPinListenerDigital) event -> {
//if the event state is High then print "Intruder Detected" and turn the LED ON by invoking the high() method
            if(event.getState().isHigh()){
                System.out.println("Motion Detected!");
                isMotionSensorStateHigh =true;
            }
//if the event state is Low then print "All is quiet.." and make the LED OFF by invoking the low() method
            if(event.getState().isLow()){
                System.out.println("0");
                isMotionSensorStateHigh =false;
            }
        });
    }

    public boolean isMotionSensorStateHigh() {
        return isMotionSensorStateHigh;
    }

    public void setMotionSensorStateHigh(boolean motionSensorStateHigh) {
        isMotionSensorStateHigh = motionSensorStateHigh;
    }
}
