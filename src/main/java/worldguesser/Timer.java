package worldguesser;

public class Timer {

    // INITIALIZES THE START TIME, END TIME, AND RUNNING STATUS OF THE TIMER
    private long startTime;
    private long endTime;
    private boolean running;


    // STARTS THE TIMER
    public void start() {
        startTime = System.currentTimeMillis();
        running = true;
    }


    // STOPS THE TIMER
    public void stop() {
        endTime = System.currentTimeMillis();
        running = false;
    }


    // GETS THE ELAPSED TIME IN SECONDS
    public double getTimeSeconds() {

        // IF THE TIMER IS STILL RUNNING CALCULATES THE ELAPSED TIME
        if (running) {
            return (System.currentTimeMillis() - startTime) / 1000.0;
        }

        // IF THE TIMER HAS STOPPED CALCULATES THE ELAPSED TIME
        return (endTime - startTime) / 1000.0;
    }
}