import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Controller extends Thread {
    Device device;
    Sensor heat;
    Sensor pressure;
    DecimalFormat df = new DecimalFormat("#.##");

    Controller(Device device,Sensor heat,Sensor pressure){
        this.device=device;
        this.heat=heat;
        this.pressure=pressure;
    }
    public void run(){
        device.startUp();
        boolean check=false;
        while(!check) {
            synchronized (heat.notifyController) {
                try {
                    heat.notifyController.wait(100);
                    Double heatValue=heat.getValue();
                    Double pressureValue=pressure.getValue();
                    System.out.println("heat->" + df.format(heatValue) + ", pressure->" + df.format(pressureValue));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (heat.getValue() > 70 || pressure.getValue() > 100) {
                    check = true;
                    heat.interrupt();
                    pressure.interrupt();
                    device.shutDown();
                    System.exit(0);
                }
            }
        }
    }

}
