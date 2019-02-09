package jb.smarthome.api.controller;

import jb.smarthome.sound.LoopedSoundPlayerTask;
import jb.smarthome.sound.Sound;
import jb.smarthome.utils.ScheduledFileReaderTask;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Controller
public class AlarmController {

    @RequestMapping("/alarm")
    @ResponseBody
    public void alarm() {
        System.out.println("Włączono alarm");

        Sound doorBell=new Sound("door_bell.wav");
        doorBell.play(15);


       /* Sound alarmSound = new Sound("alarm.wav");
        Thread loopedSound = new Thread(new LoopedSoundPlayerTask(alarmSound));
        loopedSound.start();*/



    }



    /*
    final GpioController gpioController = GpioFactory.getInstance();

    // final GpioPinDigitalInput armTheAlarmButton = gpioController.provisionDigitalInputPin(RaspiPin.GPIO_04, PinPullResistance.PULL_DOWN);
    //  final GpioPinDigitalInput turnOffAlarmButton = gpioController.provisionDigitalInputPin(RaspiPin.GPIO_05, PinPullResistance.PULL_DOWN);


    Sound alarm;
    Sound alarmActivated;
    boolean isAlarmOn = true;
    private GpioPinDigitalOutput[] led;

    @PostConstruct
    public void init() {
        alarm = new Sound("alarm.wav");
        alarmActivated = new Sound("activatingAlarm.wav");
    }
  *//*  public AlarmController() {
        alarm = new Sound("alarm.wav");
        alarmActivated = new Sound("activatingAlarm.wav");

        //  led = new GpioPinDigitalOutput[2];
        //     led[0] = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_00, "GREEN", PinState.HIGH);
        //   led[1] = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_03, "RED", PinState.LOW);

    }*//*


    @RequestMapping("/getAlarmState")
    @ResponseBody
    public boolean getAlarmState() {
        return isAlarmOn;
    }

    @RequestMapping("/turnOffAlarm")
    @ResponseBody
    public void turnOffAlarm() {
        isAlarmOn = false;
        alarm.stop();
    }

    @RequestMapping("/armingAlarm")
    @ResponseBody
    public void armingAlarm() {
        alarmActivated.play();
        motionSensorListener();
    }

    @RequestMapping("/alarm")
    @ResponseBody
    public void alarm() {
        alarm.loopedPlay();
    }

    private void motionSensorListener()
    {
        pirMotionsensor.addListener(new GpioPinListenerDigital() {

            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
//if the event state is High then print "Intruder Detected" and turn the LED ON by invoking the high() method
                if(event.getState().isHigh()){
                    System.out.println("Motion Detected!");
                    isAlarmOn=true;
                    playAlarm();
                  //  led.high();

                 //   buzzer.high();
                }
//if the event state is Low then print "All is quiet.." and make the LED OFF by invoking the low() method
                if(event.getState().isLow()){
                    System.out.println("0");
                    isAlarmOn=false;
                  //  led.low();

                  //  buzzer.low();
                }
            }
        });
    }

    *//**
     * Method to turn off the alarm
     *//*
     *//* public void turnOffTheAlarm() {

        turnOffAlarmButton.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                if (event.getState().isHigh()) {

                    alarm.stop();
               //    led[0].low();
                  //  led[1].high();
                    isAlarmOn = false;
                }


            }

        });
    }

    *//**//**
     * Method to trigger the alarm ON/OFF
     *//**//*
    public void triggerTheAlarm() {
        armTheAlarmButton.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                if (event.getState().isHigh()) {
                    isAlarmOn = !isAlarmOn;
                    alarm.stop();
                    if (isAlarmOn) {
                        led[1].low();
                        led[0].high();
                        alarmActivated.play();
                    } else {
                        led[0].low();
                        led[1].high();
                    }
                }
            }

        });
    }*//*

     *//**
     * Gets the value of alarm state
     *
     * @return isAlarmOn
     *//*
    public boolean isAlarmOn() {
        return isAlarmOn;
    }

    *//**
     * Play looped sound of alarm.
     *//*
    public void playAlarm() {
        while(isAlarmOn)
        {
            alarm.loopedPlay();
        }
        alarm.stop();
    }*/
}

