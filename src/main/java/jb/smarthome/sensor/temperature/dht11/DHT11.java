package jb.smarthome.sensor.temperature.dht11;
import java.util.ArrayList;
import java.util.List;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.GpioUtil;

public class DHT11 {
    // DHT11 sensor reader class for Raspberry Pi

    private int pin; // GPIO pin number
    private List<Integer> data    = new ArrayList<>(10000);
    private List<Integer> lengths = new ArrayList<>(40); // will contain the lengths of data pull up periods

    public DHT11(int pin) {
        // setup wiringPi
        if (Gpio.wiringPiSetup() != 0) {
            throw new RuntimeException("Initialization of the GPIO has failed.");
        }
        this.pin = pin;
        GpioUtil.export(pin, GpioUtil.DIRECTION_OUT);
    }

    public DHT11Result read() {
        data.clear();
        lengths.clear();

        // change to output, then send start signal
        Gpio.pinMode(pin, Gpio.OUTPUT);

        Gpio.digitalWrite(pin, Gpio.LOW);
        Gpio.delay(18); // ms

        Gpio.digitalWrite(pin, Gpio.HIGH);

        // change to input
        Gpio.pinMode(pin, Gpio.INPUT);

        // collect data into a list
        collectInput();

        // parse lengths of all data pull up periods
        parseDataPullUpLengths();

        // if bit count mismatch, return error (4 byte data + 1 byte checksum)
        if (lengths.size() != 40)
            return new DHT11Result(DHT11Result.ERR_MISSING_DATA, 0.0, 0.0, data.size(), lengths.size());

        // calculate bits from lengths of the pull up periods
        boolean[] bits = calculateBits();

        // we have the bits, calculate bytes
        byte[] bytes = bitsToBytes(bits);

        // calculate checksum and check
        if (bytes[4] != calculateChecksum(bytes))
            return new DHT11Result(DHT11Result.ERR_CRC, 0.0, 0.0, data.size(), lengths.size());

        // ok, we have valid data, return it
        return new DHT11Result(DHT11Result.ERR_NO_ERROR,
                Double.parseDouble(bytes[2] + "." + bytes[3]),
                Double.parseDouble(bytes[0] + "." + bytes[1]),
                data.size(), lengths.size());
    }

    // this is used to determine where is the end of the data
    private final int MAX_UNCHANGED_COUNT = 500;

    private void collectInput() {
        // collect the data while unchanged found
        int unchangedCount = 0;

        int last = -1;
        while (true) {
            int current = Gpio.digitalRead(pin);
            data.add(current);

            if (last != current) {
                unchangedCount = 0;
                last = current;
            } else {
                if (++unchangedCount > MAX_UNCHANGED_COUNT) break;
            }
        }
    }

    protected enum SignalTransition {
        STATE_INIT_PULL_DOWN      ,
        STATE_INIT_PULL_UP        ,
        STATE_DATA_FIRST_PULL_DOWN,
        STATE_DATA_PULL_UP        ,
        STATE_DATA_PULL_DOWN
    };

    private void parseDataPullUpLengths() {
        SignalTransition state = SignalTransition.STATE_INIT_PULL_DOWN;

        int currentLength = 0; // will contain the length of the previous period

        for (int current : data) {
            currentLength++;

            switch (state) {
                case STATE_INIT_PULL_DOWN:
                    if (current == Gpio.LOW) {
                        // ok, we got the initial pull down
                        state = SignalTransition.STATE_INIT_PULL_UP;
                    }
                    break;

                case STATE_INIT_PULL_UP:
                    if (current == Gpio.HIGH) {
                        // ok, we got the initial pull up
                        state = SignalTransition.STATE_DATA_FIRST_PULL_DOWN;
                    }
                    break;

                case STATE_DATA_FIRST_PULL_DOWN:
                    if (current == Gpio.LOW) {
                        // we have the initial pull down, the next will be the data pull up
                        state = SignalTransition.STATE_DATA_PULL_UP;
                    }
                    break;

                case STATE_DATA_PULL_UP:
                    if (current == Gpio.HIGH) {
                        // data pulled up, the length of this pull up will determine whether it is 0 or 1
                        currentLength = 0;
                        state = SignalTransition.STATE_DATA_PULL_DOWN;
                    }
                    break;

                case STATE_DATA_PULL_DOWN:
                    if (current == Gpio.LOW) {
                        // pulled down, we store the length of the previous pull up period
                        lengths.add(currentLength);
                        state = SignalTransition.STATE_DATA_PULL_UP;
                    }
                    break;
            }
        }
    }

    private boolean[] calculateBits() {
        boolean[] bits = new boolean[40];

        int longestPullUp  = lengths.stream().mapToInt(Integer::intValue).max().getAsInt();
        int shortestPullUp = lengths.stream().mapToInt(Integer::intValue).min().getAsInt();

        // use the halfway to determine whether the period it is long or short
        int halfway = shortestPullUp + (longestPullUp - shortestPullUp) / 2;

        int i = 0;
        for (int length : lengths) bits[i++] = length > halfway;

        return bits;
    }

    private byte[] bitsToBytes(boolean[] bits) {
        byte[] bytes = new byte[5];
        byte   value = 0;

        for (int i = 0; i < bits.length; i ++) {
            value <<= 1;
            if (bits[i]) value |= 1;

            if (i % 8 == 7) {
                bytes[i / 8] = value;
                value = 0;
            }
        }
        return bytes;
    }

    private byte calculateChecksum(byte[] bytes) {
        return (byte)(bytes[0] + bytes[1] + bytes[2] + bytes[3]);
    }
}