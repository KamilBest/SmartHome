package jb.smarthome.api.controller;

import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LightController {
    final GpioController gpio = GpioFactory.getInstance();
    final GpioPinDigitalOutput pin27 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27);
    final GpioPinDigitalOutput pin28 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28);
    final GpioPinDigitalOutput pin29 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29);


    @RequestMapping("/ledState")
    @ResponseBody
    public boolean[] ledState() {
        boolean[] isOn = new boolean[3];
        isOn[0] = pin27.isHigh();
        isOn[1] = pin28.isHigh();
        isOn[2] = pin29.isHigh();
        System.out.println(isOn);
        return isOn;
    }

    @RequestMapping("/turnOff")
    @ResponseBody
    public void turnOffLed(@RequestParam int pin) {
        switch(pin)
        {
            case 27:
                pin27.setState(PinState.LOW);
                break;
            case 28:
                pin28.setState(PinState.LOW);
                break;
            case 29:
                pin29.setState(PinState.LOW);
                break;
        }
    }

    @RequestMapping("/turnOn")
    @ResponseBody
    public void turnOnLed(@RequestParam int pin){
        switch(pin)
        {
            case 27:
                pin27.setState(PinState.HIGH);
                break;
            case 28:
                pin28.setState(PinState.HIGH);
                break;
            case 29:
                pin29.setState(PinState.HIGH);
                break;
        }
    }
}
