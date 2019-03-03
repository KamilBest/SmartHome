package jb.smarthome.api.controller;

import com.pi4j.io.gpio.RaspiPin;
import jb.smarthome.sensor.MotionSensor;
import jb.smarthome.utils.ScheduledFileReaderTask;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Controller
public class SensorsController {
    private MotionSensor motionSensor;
    @RequestMapping("/fireSensor")
    @ResponseBody
    public String fireSensor() {
        String file ="/home/pi/projekty/scripts/flameSensor/flameSensorReadings.txt";
        return String.valueOf(readValeFromFile(file));
    }

    @RequestMapping("/gasSensor")
    @ResponseBody
    public String gasSensor() {
        String file ="/home/pi/projekty/scripts/gasSensor/gasSensorReadings.txt";
        return String.valueOf(readValeFromFile(file));
    }

    private int readValeFromFile(String filePath)
    {
        ScheduledExecutorService executor =
                Executors.newSingleThreadScheduledExecutor();
        ScheduledFileReaderTask scheduledFileReaderTask = new ScheduledFileReaderTask(filePath);
        executor.scheduleAtFixedRate(scheduledFileReaderTask, 0, 5, TimeUnit.SECONDS);
        return scheduledFileReaderTask.readValueFromFile(filePath);

    }

    @RequestMapping("/motionSensor")
    @ResponseBody
    public Boolean motionSensor()
    {
        return motionSensor.isMotionSensorStateHigh();
    }

    @PostConstruct
    private void prepareMotionSensor()
    {
        motionSensor=new MotionSensor(RaspiPin.GPIO_02);
        motionSensor.setMotionSensorListener();
    }
}
