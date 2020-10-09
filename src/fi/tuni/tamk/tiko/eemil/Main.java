package fi.tuni.tamk.tiko.eemil;
import fi.tuni.tamk.tiko.eemil.util.*;

//HUOM!! VUOTENA KÄYTETÄÄN 52 VIIKKOA, EI 52,143 VIIKKOA!!!!

public class Main {
    //Some Strings and parameters
    public static int min = 1;
    public static int max = 40;
    public static String numberMsg = "Give a number between 1 and 40";
    public static String errorMsg = "Give a proper number";
    public static String errorMessageNonMinAndMax = "The number has to be between 1 and 40";
    public static String errorMessageSameNumber = "You have already given this number";
    public static String calc = "Calculating how long does it take to hit the jackpot....";


    public static void main(String[] args) {
        //Init the players Lotto numbers
        int[] playerNumbers = Arrays.lottoArrayUser(min, max);
        //Go to the main loop
        MyConsole.playLotto(min, max, playerNumbers);
    }
}
