package worldguesser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class ScoreStorage {
    
    private String file;

    public ScoreStorage(String file) {
        this.file = file;
    }

    public void saveScore(Score score) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(score.toString() + "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println("Error writing score to file: " + e);
        }
    }

    public ArrayList<Integer> topFiveScores() {

        ArrayList<Integer> scores = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(file));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isBlank()) {
                    continue;
                }
                String[] parts = line.split(",");

                int totalSeconds = Integer.valueOf(parts[0]);

                scores.add(totalSeconds);
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading score from file: " + e);
        }

        Collections.sort(scores);

        ArrayList<Integer> fiveBestScores = new ArrayList<>();

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
