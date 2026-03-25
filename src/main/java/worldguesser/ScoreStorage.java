package worldguesser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class ScoreStorage {
    
    // INITIALIZES THE FILE PATH FOR THE SCORE STORAGE
    private String file;


    // CONSTRUCTOR THAT INITIALIZES THE FILE PATH FOR THE SCORE STORAGE
    public ScoreStorage(String file) {
        this.file = file;
    }

    public void saveScore(Score score) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(score.toCSV() + "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println("Error writing score to file: " + e);
        }
    }

    public ArrayList<Double> loadScores() {
        ArrayList<Double> scores = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(file));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isBlank()) {
                    continue;
                }
                String[] parts = line.split(",");

                double totalSeconds = Double.valueOf(parts[0]);

                scores.add(totalSeconds);
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading score from file: " + e);
        }
        return scores;
    }


    public ArrayList<Double> topFiveScores() {

        ArrayList<Double> scores = loadScores();

        Collections.sort(scores);

        ArrayList<Double> fiveBestScores = new ArrayList<>();

        if (scores.size() <= 5) {
            fiveBestScores = new ArrayList<>(scores);
        } else {
            for (int i = 0; i < 5; i++) {
                fiveBestScores.add(scores.get(i));
            }
        }
        return fiveBestScores;
    }  
}
