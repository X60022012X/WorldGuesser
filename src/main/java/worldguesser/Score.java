package worldguesser;

public class Score implements ForcedInterface {

    private final double totalSeconds;
    private final double timeSeconds;
    private final int misclicks;

    public Score(double timeSeconds, int misclicks) {
        this.timeSeconds = timeSeconds;
        this.misclicks = misclicks;
        this.totalSeconds = timeSeconds + misclicks * 5;
    }

    public double getTotalSeconds() {
        return totalSeconds;
    }

    public double getTimeSeconds() {
        return timeSeconds;
    }

    public int getMisclicks() {
        return misclicks;
    }

    @Override
    public String toString() {
        return totalSeconds + "," + timeSeconds + "," + misclicks;
    }

    @Override
    public String toCSV() {
        return totalSeconds + "," + timeSeconds + "," + misclicks;
    }
}