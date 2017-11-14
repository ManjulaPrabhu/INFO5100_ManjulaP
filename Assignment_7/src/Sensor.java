import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Sensor extends Thread {
    private final Device device;
    private double value;
    Object notifyController = new Object();

    public Sensor(Device device) {
        this.device = device;
    }

    public double getValue() {
        return value;
    }

    public void updateValue() {
        this.value += 4.9;
        try {
            this.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            updateValue();
            if (value > 0.70) {
                synchronized (notifyController) {
                    notifyController.notify();
                }
            } else {
                continue;
            }
        }

    }
}

