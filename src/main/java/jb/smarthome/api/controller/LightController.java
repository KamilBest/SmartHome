package jb.smarthome.api.controller;

import com.pi4j.io.gpio.*;
import jb.smarthome.api.model.Light;
import jb.smarthome.api.model.LightResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class LightController {
    final GpioController gpio = GpioFactory.getInstance();
    final GpioPinDigitalOutput pin27 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27);
    final GpioPinDigitalOutput pin28 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28);
    final GpioPinDigitalOutput pin29 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29);


    @RequestMapping("/ledState")
    @ResponseBody
    public LightResponse ledState() {
        ArrayList<Light> lightArrayList=new ArrayList<>();
        lightArrayList.add(new Light("Kuchnia", pin27.isHigh(), 27));
        lightArrayList.add(new Light("Kuchnia", pin28.isHigh(), 28));
        lightArrayList.add(new Light("Kuchnia", pin29.isHigh(), 29));

        LightResponse lightResponse=new LightResponse(lightArrayList);

        System.out.println(lightResponse);
        return lightResponse;
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
