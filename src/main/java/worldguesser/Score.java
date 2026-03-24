package worldguesser;

public class Score {

    private final int totalSeconds;
    private final int timeSeconds;
    private final int misclicks;

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