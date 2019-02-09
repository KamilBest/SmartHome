package jb.smarthome.sound;

public class LoopedSoundPlayerTask implements Runnable {
    private Sound sound;

    public LoopedSoundPlayerTask(Sound sound) {
        this.sound = sound;
    }

    @Override
    public void run() {
       while (true)
        {
            sound.loopedPlay();
            System.out.println("AAAA Alarm");
            try {
                Thread.sleep(2*1000); // The sleep time should be length of your wav file
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
