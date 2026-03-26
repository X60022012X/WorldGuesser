package worldguesser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class ScoreStorage {
    
    // INITIALIZES THE FILE PATH FOR THE SCORE STORAGE
    private String file;
    private ArrayList<Double> scores;
    private ArrayList<Double> topFiveScores;


    // CONSTRUCTOR THAT INITIALIZES THE FILE PATH AND LOADS THE SCORES FROM THE FILE
    public ScoreStorage(String file) {
        this.file = file;
        this.scores = this.getScoreFromCsv();
        this.topFiveScores = this.findTopFiveScores();
    }


    // LOADS THE SCORES FROM THE FILE AND RETURNS THEM AS AN ARRAYLIST OF DOUBLES
    private ArrayList<Double> getScoreFromCsv() {
        ArrayList<Double> scores = new ArrayList<>();

        // TRY TO OPEN THE FILE FOR READING, AND CATCH ANY EXCEPTIONS THAT OCCUR
        try {
            // CREATES A SCANNER TO READ THE FILE
            Scanner scanner = new Scanner(new File(file));

            // ITERATES THROUGH EACH LINE OF THE FILE, EXTRACTS THE SCORE AND ADDS IT TO THE ARRAYLIST
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // IF THE LINE IS BLANK, SKIP IT
                if (line.isBlank()) {
                    continue;
                }

                // SPLITS THE LINE BY COMMA, EXTRACTS THE FIRST VALUE AS THE SCORE, AND ADDS IT TO THE ARRAYLIST
                String[] parts = line.split(",");
                double totalSeconds = Double.valueOf(parts[0]);
                scores.add(totalSeconds);
            }

            // CLOSES THE SCANNER
            scanner.close();

        // IF AN EXCEPTION OCCURS, PRINT AN ERROR MESSAGE
        } catch (Exception e) {
            System.out.println("Error reading score from file: " + e);
        }

        // RETURNS THE SCORES AS AN ARRAYLIST OF DOUBLES
        return scores;
    }


    // FINDS THE TOP FIVE SCORES BY SORTING THE SCORES AND RETURNING THE FIRST FIVE ELEMENTS OF THE SORTED LIST
    private ArrayList<Double> findTopFiveScores() {

        // SORTS THE SCORES IN ASCENDING ORDER
        Collections.sort(this.scores);

        // CREATES AN ARRAYLIST TO HOLD THE TOP FIVE SCORES
        ArrayList<Double> fiveBestScores = new ArrayList<>();

        // IF THERE ARE FIVE OR FEWER SCORES, RETURNS ALL OF THEM
        if (this.scores.size() <= 5) {
            fiveBestScores = new ArrayList<>(this.scores);
        
        // OTHERWISE, RETURNS THE FIRST FIVE ELEMENTS OF THE SORTED LIST
        } else {
            for (int i = 0; i < 5; i++) {
                fiveBestScores.add(scores.get(i));
            }
        }

        // RETURNS THE TOP FIVE SCORES AS AN ARRAYLIST OF DOUBLES
        return fiveBestScores;
    }  


    // SAVES A SCORE TO THE FILE BY APPENDING IT TO THE END OF THE FILE
    public void writeScoreToCsv(Score score) {
        // TRY TO OPEN THE FILE FOR WRITING, AND CATCH ANY EXCEPTIONS THAT OCCUR
        try {
            // OPENS THE FILE FOR WRITING IN APPEND MODE, WRITES THE SCORE TO THE FILE IN CSV FORMAT
            FileWriter writer = new FileWriter(file, true);
            writer.write(score.toCSV() + "\n");
            writer.close();

        // IF AN EXCEPTION OCCURS, PRINT AN ERROR MESSAGE
        } catch (Exception e) {
            System.out.println("Error writing score to file: " + e);
        }
    }


    // GETTER FOR THE TOP FIVE SCORES
    public ArrayList<Double> getTopFiveScores() {
        return topFiveScores;
    }


    // GETTER FOR ALL SCORES
    public ArrayList<Double> getAllScores() {
        return scores;
    }
}
