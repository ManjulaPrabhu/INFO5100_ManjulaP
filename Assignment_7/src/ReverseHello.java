public class ReverseHello extends Thread {
    String inputString;
    static int threadCounter = 51;

    public ReverseHello(String inputString) {
        this.inputString = inputString;
    }

    public void run() {
        ReverseHello reverseHelloThreadObject = new ReverseHello("Hello from Thread num");
        threadCounter--;
        if (threadCounter > 0) {
            System.out.println(inputString + (threadCounter));
            reverseHelloThreadObject.start();
        } else {
            for (Thread t : Thread.getAllStackTraces().keySet()) {
                if (t.getState() == Thread.State.RUNNABLE) {
                    t.interrupt();
                }
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReverseHello reverseHelloThread = new ReverseHello("Hello from Thread num");
        reverseHelloThread.start();

    }
}