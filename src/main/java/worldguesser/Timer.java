package worldguesser;

public class Timer {

    private long startTime;
    private long endTime;
    private boolean running;

    public void start() {
        startTime = System.currentTimeMillis();
        running = true;
    }

    public void stop() {
        endTime = System.currentTimeMillis();
        running = false;
    }

    public double getTimeSeconds() {

        if (running) {
            return (System.currentTimeMillis() - startTime) / 1000.0;
        }

        return (endTime - startTime) / 1000.0;
    }
}