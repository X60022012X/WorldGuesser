package worldguesser;

import java.util.ArrayList;

public class WorldGuesser {

    private ArrayList<Country> remainingCountries;
    private Country currentCountry;
    private int attempts;
    private int misclicks;


    // CONSTRUCTOR THAT INITIALIZES AN ARRAY FORTHE REMAINING COUNTRIES, AND SELECTS THE FIRST COUNTRY TO GUESS
    public WorldGuesser(ArrayList<Country> countries) {
        remainingCountries = new ArrayList<>(countries);
        nextCountry();
    }  


    // SELECTS THE NEXT COUNTRY TO GUESS BY RANDOMLY CHOOSING ONE FROM THE REMAINING COUNTRIES
    private void nextCountry() {

        // RANDOMLY SELECTS A COUNTRY FROM THE REMAINING COUNTRIES
        int index = (int)(Math.random() * remainingCountries.size());
        currentCountry = remainingCountries.get(index);

        // SETS THE ATTEMPTS TO 3 FOR EACH NEW COUNTRY
        attempts = 3;
    }


    // HANDLES A CLICK ON A COUNTRY
    public boolean handleClick(Country clicked) {

        // IF THE CLICKED COUNTRY IS NOT IN THE REMAINING COUNTRIES, RETURNS FALSE
        if(!remainingCountries.contains(clicked)) {
            return false;
        }

        // IF THE CLICKED COUNTRY IS THE CURRENT COUNTRY, MARKS IT AS CORRECT
        if(clicked == currentCountry) {

            // MARKS THE CLICKED COUNTRY AS CORRECT
            clicked.setCorrect();

            // REMOVES THE CLICKED COUNTRY FROM THE REMAINING COUNTRIES
            remainingCountries.remove(clicked);

            // IF THERE ARE NO MORE REMAINING COUNTRIES, SETS THE CURRENT COUNTRY TO NULL AND RETURNS TRUE
            if(remainingCountries.isEmpty()) {
                currentCountry = null;
                return true;
            }

            // SELECTS THE NEXT COUNTRY TO GUESS AND RETURNS FALSE
            nextCountry();
            return false;
        }

        // IF THE CLICKED COUNTRY IS NOT THE CURRENT COUNTRY, MARKS IT AS WRONG
        clicked.setWrong();

        // INCREMENTS THE MISCLICKS AND DECREMENTS THE ATTEMPTS
        misclicks++;
        attempts--;

        // IF THE ATTEMPTS HAVE BEEN EXHAUSTED, SELECTS THE NEXT COUNTRY TO GUESS
        if(attempts <= 0) {
            nextCountry();
        }

        // RETURNS FALSE IF THE CLICKED COUNTRY WAS NOT CORRECT
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