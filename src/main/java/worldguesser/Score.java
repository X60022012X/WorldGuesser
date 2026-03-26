package worldguesser;

public class Score implements ForcedInterface {

    // INITIALIZES THE TOTAL SECONDS, TIME SECONDS, AND MISCLICKS OF THE SCORE
    private final double totalSeconds;
    private final double timeSeconds;
    private final int misclicks;


    // CONSTRUCTOR THAT INITIALIZES THE TOTAL SECONDS, TIME SECONDS, AND MISCLICKS OF THE SCORE
    public Score(double timeSeconds, int misclicks) {
        this.timeSeconds = timeSeconds;
        this.misclicks = misclicks;
        this.totalSeconds = timeSeconds + misclicks * 5;
    }

    
    // GETTER FOR THE TOTAL SECONDS
    public double getTotalSeconds() {
        return totalSeconds;
    }


    // GETTER FOR THE TIME SECONDS
    public double getTimeSeconds() {
        return timeSeconds;
    }


    // GETTER FOR THE MISCLICKS
    public int getMisclicks() {
        return misclicks;
    }


    // OVERRIDE OF THE TOCSV METHOD TO RETURN THE SCORE AS A STRING
    @Override
    public String toCSV() {
        return totalSeconds + "," + timeSeconds + "," + misclicks;
    }
}