package jb.smarthome.utils;

import java.io.BufferedReader;
import java.io.IOException;

public class ScheduledFileReaderTask implements Runnable {
    private String filePath;

    public ScheduledFileReaderTask() {
    }

    public ScheduledFileReaderTask(String filePath) {
        this.filePath = filePath;
    }

    public int readValueFromFile(String filePath) {
        int value = 0;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new java.io.FileReader(filePath));
            String currentLine = reader.readLine();
            value = Integer.valueOf(currentLine);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public void run() {
        int value = readValueFromFile(filePath);
        System.out.println(value);
    }



    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
