package Model;

public class Timer {
    private int verbleibendeSekunden;
    private volatile boolean running;
    private Thread timerThread;

    static void main(String[] args) {
        Timer  zeit = new Timer(50);
        zeit.startTimer();
    }

    public Timer(int verbleibendeSekunden) {
        this.verbleibendeSekunden = verbleibendeSekunden;
    }

    public void startTimer() {
        running = true;
        timerThread = new Thread(() -> {
            while (running && verbleibendeSekunden != 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                verbleibendeSekunden--;
                System.out.println(verbleibendeSekunden);
            }
        });
        timerThread.start();


    }
    public void stopTimer() {
        running = false;
    }

    public int getVerbleibendeSekunden() {
        return verbleibendeSekunden;
    }
}
