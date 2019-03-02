package jb.smarthome.api.controller;

import jb.smarthome.api.model.Temperature;
import jb.smarthome.api.model.TemperatureResponse;
import jb.smarthome.sensor.temperature.dht11.DHT11;
import jb.smarthome.sensor.temperature.dht11.DHT11Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class TemperatureController {

    @RequestMapping("/temperatureAndHumidity")
    @ResponseBody
    public TemperatureResponse temperature() {
        Temperature temperature1 = readDHT11Temp(24, "Pokój1");
        Temperature temperature2 = readDHT11Temp(25, "Kuchnia");
        Temperature temperature3 = readDHT11Temp(3, "Sypialnia");

        ArrayList<Temperature> temperatures = new ArrayList<>();
        temperatures.add(temperature1);
        temperatures.add(temperature2);
        temperatures.add(temperature3);

        TemperatureResponse temperatureResponse = new TemperatureResponse();
        temperatureResponse.setTemperatures(temperatures);
        temperatureResponse.setAvgTemperature(averageTempOrHumidity(temperatures, true));
        temperatureResponse.setAvgHumidity(averageTempOrHumidity(temperatures, false));

        System.out.println(temperatureResponse);
        return temperatureResponse;
    }

    private static Temperature readDHT11Temp(int pin, String room) {
        DHT11 dht11 = new DHT11(pin);
        Temperature temperature = new Temperature();
        temperature.setRoom(room);
           while(true) {
               DHT11Result dht11Result = dht11.read();
               temperature.setTemperature(dht11Result.getTemperature());
               temperature.setHumidity(dht11Result.getHumidity());

               if(((temperature.getTemperature() != null && temperature.getHumidity() != null) && (temperature.getTemperature()!=0.0 && temperature.getHumidity()!=0.0)))
                   break;
              // System.out.printf("Temperature: %.1f C\n", temperature.getTemperature());
               //System.out.printf("Humidity:    %.1f %%\n", temperature.getHumidity());
           }
        return temperature;
    }

    private static Double averageTempOrHumidity(ArrayList<Temperature> temperatures, boolean temperatureAVG) {
        int sum = 0;
        double avg;
        for (Temperature temperature : temperatures) {
            if (temperatureAVG) {
                sum += temperature.getTemperature();
            } else {
                sum += temperature.getHumidity();

            }
        }
        avg = sum / temperatures.size();
        return avg;


    }


}
