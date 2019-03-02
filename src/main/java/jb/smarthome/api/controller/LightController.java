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
    static final GpioController gpio = GpioFactory.getInstance();
    static final GpioPinDigitalOutput pin27 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27);
    static final GpioPinDigitalOutput pin28 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28);
    static final GpioPinDigitalOutput pin29 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29);


    @RequestMapping("/ledState")
    @ResponseBody
    public LightResponse ledState() {
        ArrayList<Light> lightArrayList=new ArrayList<>();
        lightArrayList.add(new Light("Salon", pin27.isHigh(), 27));
        lightArrayList.add(new Light("Kuchnia", pin28.isHigh(), 28));
        lightArrayList.add(new Light("Korytarz", pin29.isHigh(), 29));

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
                System.out.println("Zgaszono światło (pin 27)");
                break;
            case 28:
                pin28.setState(PinState.LOW);
                System.out.println("Zgaszono światło (pin 28)");

                break;
            case 29:
                pin29.setState(PinState.LOW);
                System.out.println("Zgaszono światło (pin 29)");

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
                System.out.println("Zapalono światło (pin 27)");
                break;
            case 28:
                pin28.setState(PinState.HIGH);
                System.out.println("Zapalono światło (pin 28)");
                break;
            case 29:
                pin29.setState(PinState.HIGH);
                System.out.println("Zapalono światło (pin 29)");
                break;
        }
    }

    @RequestMapping("/turnAllOn")
    @ResponseBody
    public void turnAllOn()
    {
        pin27.setState(PinState.HIGH);
        pin28.setState(PinState.HIGH);
        pin29.setState(PinState.HIGH);

        System.out.println("Zapalono wszystkie światła");

    }

    @RequestMapping("/turnAllOff")
    @ResponseBody
    public void turnAllOff()
    {
        pin27.setState(PinState.LOW);
        pin28.setState(PinState.LOW);
        pin29.setState(PinState.LOW);

        System.out.println("Zgaszono wszystkie światła");

    }

    public static GpioPinDigitalOutput getPin27() {
        return pin27;
    }

    public static GpioPinDigitalOutput getPin28() {
        return pin28;
    }

    public static GpioPinDigitalOutput getPin29() {
        return pin29;
    }
}
