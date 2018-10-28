package jb.smarthome.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Controller
public class ServoController {

    private final int CENTER_POSITION = 150;
    private final int MAX_POSITION = 250;
    private final int MIN_POSITION = 60;

    private static int servoPosition = 150;

    @PostConstruct
    public void init() {

        Runtime runTime = Runtime.getRuntime();

        try {
            runTime.exec("gpio mode 23 pwm");
            runTime.exec("gpio pwm-ms");
            runTime.exec("gpio pwmc 192");
            runTime.exec("gpio pwmr 2000");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/centerServo")
    @ResponseBody
    public void centerServo() {

        servoPosition = CENTER_POSITION;
        turnServo(servoPosition);

    }

    @RequestMapping("/turnLeft")
    @ResponseBody
    public int turnRight() {

        if (servoPosition + 10<= MAX_POSITION) {
            servoPosition += 10;
            turnServo(servoPosition);
        }
        return servoPosition;
    }

    @RequestMapping("/turnRight")
    @ResponseBody
    public int turnLeft() {
        if (servoPosition -10>= MIN_POSITION) {
            servoPosition -= 10;
            turnServo(servoPosition);
        }
        return servoPosition;
    }

    private void turnServo(int servoPosition) {
        Runtime runTime = Runtime.getRuntime();
        try {
            runTime.exec("gpio pwm 23 " + servoPosition);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }


}