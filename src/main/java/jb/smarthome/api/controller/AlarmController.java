package jb.smarthome.api.controller;

import com.pi4j.io.gpio.PinState;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AlarmController {
    boolean isAlarmOn = false;

    @RequestMapping("/turnOnAlarm")
    @ResponseBody
    public void alarm() {
        isAlarmOn = true;
        while(isAlarmOn)
        {
            LightController.getPin27().toggle();
            LightController.getPin28().toggle();
            LightController.getPin29().toggle();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @RequestMapping("/turnOffAlarm")
    @ResponseBody
    public void turnOffAlarm() {
        isAlarmOn = false;
        LightController.getPin27().setState(PinState.LOW);
        LightController.getPin28().setState(PinState.LOW);
        LightController.getPin29().setState(PinState.LOW);

    }
}

