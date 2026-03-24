package worldguesser;

public class Score implements Comparable<Score> {
    
    private int totalSeconds;

    public Score(int timeSeconds, int misclicks) {
        this.totalSeconds = timeSeconds + misclicks * 5;
    }

    public int getTotalSeconds() {
        return totalSeconds;
    }

    @Override
    public int compareTo(Score other) {
        return Integer.compare(this.totalSeconds, other.totalSeconds);
    }
}
