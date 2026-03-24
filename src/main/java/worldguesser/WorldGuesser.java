package worldguesser;

import java.util.ArrayList;

public class WorldGuesser {

    private ArrayList<Country> remainingCountries;
    private Country currentCountry;
    private int attempts;
    private int misclicks;

    public WorldGuesser(ArrayList<Country> countries) {
        remainingCountries = new ArrayList<>(countries);
        nextCountry();
    }

    private void nextCountry() {
        int index = (int)(Math.random() * remainingCountries.size());
        currentCountry = remainingCountries.get(index);
        attempts = 3;

        System.out.println("Find: " + currentCountry.getName());  //REMOVE BEFORE FLIGHT
    }

    public boolean handleClick(Country clicked) {

        if(!remainingCountries.contains(clicked)) {
            return false;
        }

        if(clicked == currentCountry) {

            clicked.setCorrect();

            remainingCountries.remove(clicked);

            if(remainingCountries.isEmpty()) {
                currentCountry = null;
                return true;
            }

            nextCountry();
            return false;
        }

        // wrong click
        clicked.setWrong();

        misclicks++;
        attempts--;

        if(attempts <= 0) {
            nextCountry();
        }

        return false;
    }


    public int getMisclicks() {
        return misclicks;
    }

    public int getAttempts() {
        return attempts;
    }

    public Country getCurrentCountry() {
        return currentCountry;
    }
}