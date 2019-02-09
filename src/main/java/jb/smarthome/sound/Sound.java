package jb.smarthome.sound;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class Sound {

    private Clip clip;

    public Sound(String fileName) {
        try {
            File soundFile = new File("/home/pi/projekty/SmartHome/src/main/java/jb/smarthome/sound/" + fileName);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            this.clip = AudioSystem.getClip();
            clip.open(audioIn);

            clip.addLineListener(event -> {
                if(LineEvent.Type.STOP.equals(event.getType())) {
                    clip.close();
                }
            });

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play(int duration) {
        clip.start();
        try {
            Thread.sleep(duration*1000); // The sleep time should be length of your wav file
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void loopedPlay() {
        clip.loop(10000);
    }

    public void stop() {
        clip.stop();
    }


}