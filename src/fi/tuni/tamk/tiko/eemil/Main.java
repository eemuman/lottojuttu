package fi.tuni.tamk.tiko.eemil;

import fi.tuni.tamk.tiko.eemil.util.Arrays;
import fi.tuni.tamk.tiko.eemil.util.MyConsole;

import java.util.Locale;


//HUOM!! VUOTENA KÄYTETÄÄN 52 VIIKKOA, EI 52,143 VIIKKOA!!!!

public class Main {
    //Some Strings and parameters
    public static int min = 1;
    public static int max = 40;
    public static Locale currentLocale;


    public static void main(String[] args) {
        String language = "fi";
        String country = "FI";
        currentLocale = new Locale(language, country);
        
        //Init the players Lotto numbers
        int[] playerNumbers = Arrays.lottoArrayUser(min, max);
        //Sort the users Lotto numbers
        Arrays.sortNumbers(playerNumbers);
        String [] userLotto = MyConsole.leadingZero(playerNumbers);
        //Setup the lotto and start playing
        MyConsole.config(userLotto, playerNumbers);
    }
}
