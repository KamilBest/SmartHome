package jb.smarthome.api.controller;

import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LedController {
    final GpioController gpio = GpioFactory.getInstance();
    final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);

    @RequestMapping("/ledState")
    @ResponseBody
    public boolean ledState() {
        boolean isOn = pin.isHigh();
        System.out.println(isOn);
        return isOn;
    }

    @RequestMapping("/turnOffLed")
    @ResponseBody
    public void turnOffLed() {
        pin.setState(PinState.LOW);
    }

    @RequestMapping("/turnOnLed")
    @ResponseBody
    public void turnOnLed()
    {
        pin.setState(PinState.HIGH);
    }
}
