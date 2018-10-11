package jb.smarthome.controller;

import jb.smarthome.sensor.temperature.dht11.DHT11;
import jb.smarthome.sensor.temperature.dht11.DHT11Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TemperatureController {

    @RequestMapping("/temperature1")
    @ResponseBody
    public String[] temperature1() {
        return readDHT11Temp(24);
    }

    @RequestMapping("/temperature2")
    @ResponseBody
    public String[] temperature2() {
       return readDHT11Temp(25);
    }

    @RequestMapping("/temperature3")
    @ResponseBody
    public String temperature3() {
        return null;
    }

    public static String[] readDHT11Temp(int pin)
    {
        DHT11 dht11 = new DHT11(pin);

        String[] results = new String[2];
        DHT11Result dht11Result = dht11.read();

        if (dht11Result.isValid()) {

            results[0] = String.valueOf(dht11Result.getTemperature());
            results[1] = String.valueOf(dht11Result.getHumidity());

            System.out.printf("Temperature: %.1f C\n", dht11Result.getTemperature());
            System.out.printf("Humidity:    %.1f %%\n", dht11Result.getHumidity());
        }
        return results;
    }
}
