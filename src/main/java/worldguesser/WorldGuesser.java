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

        clicked.setWrong();

        misclicks++;
        attempts--;

        if(attempts <= 0) {
            nextCountry();
        }

        return false;
    }


    // GETTER FOR THE MISCLICKS
    public int getMisclicks() {
        return misclicks;
    }


    // GETTER FOR THE ATTEMPTS
    public int getAttempts() {
        return attempts;
    }


    // GETTER FOR THE CURRENT COUNTRY
    public Country getCurrentCountry() {
        return currentCountry;
    }


    // GETTER FOR THE REMAINING COUNTRIES
    public ArrayList<Country> getRemainingCountries() {
        return remainingCountries;
    }


    // GETTER FOR THE AMOUNT OF REMAINING COUNTRIES
    public int getRemainingCountriesSize() {
        return remainingCountries.size();
    }
}