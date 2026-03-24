package worldguesser;

import java.util.ArrayList;

public class WorldGuesser {

    private ArrayList<Country> remainingCountries;
    private Country currentCountry;

    private int attempts;
    private int misclicks;

    private long startTime;

    public void startGame(ArrayList<Country> countries) {

        remainingCountries = new ArrayList<>(countries);

        startTime = System.currentTimeMillis();

        nextCountry();
    }

    private void nextCountry() {

        int index = (int)(Math.random() * remainingCountries.size());

        currentCountry = remainingCountries.get(index);

        attempts = 0;

        System.out.println("Find: " + currentCountry.getName());
    }

    public boolean handleClick(Country clicked) {

        // ignore clicks on finished countries
        if(!remainingCountries.contains(clicked)) {
            return false;
        }

        // correct click
        if(clicked == currentCountry) {

            clicked.setCorrect();

            remainingCountries.remove(clicked);

            if(remainingCountries.isEmpty()) {
                return true;
            }

            nextCountry();
            return false;
        }

        // wrong click
        clicked.setWrong();

        misclicks++;
        attempts++;

        if(attempts >= 3) {
            nextCountry();
        }

        return false;
    }

    public int getMisclicks() {
        return misclicks;
    }

    public long getStartTime() {
        return startTime;
    }

    public Country getCurrentCountry() {
        return currentCountry;
    }
}