package worldguesser;

public class Score {

    private int totalSeconds;
    private int timeSeconds;
    private int misclicks;

    public Score(int timeSeconds, int misclicks) {
        this.timeSeconds = timeSeconds;
        this.misclicks = misclicks;
        this.totalSeconds = timeSeconds + misclicks * 5;
    }

    public int getTotalSeconds() {
        return totalSeconds;
    }

    public int getTimeSeconds() {
        return timeSeconds;
    }

    public int getMisclicks() {
        return misclicks;
    }

    @Override
    public String toString() {
        return totalSeconds + "," + timeSeconds + "," + misclicks;
    }
}