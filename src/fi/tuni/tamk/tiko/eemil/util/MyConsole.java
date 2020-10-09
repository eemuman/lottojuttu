package fi.tuni.tamk.tiko.eemil.util;
import java.io.*;
import static fi.tuni.tamk.tiko.eemil.Main.*;

public class MyConsole {



    public static int readInt(int min, int max, int[] lottoNumbers, String errorMessageNonNumeric, String errorMessageNonMinAndMax, String errorMessageSameNumber, String numberMsg) {
        //Reads the users given input and tests whether the input is A) a number B) a whole number C) NOT a number the user has given already.
        Console c = System.console();
        boolean properNumber = false;
        boolean numberTrue = false;
        boolean within = false;
        boolean notTheSame = false;
        int input = 0;
        int realInt = 0;
        while (!properNumber) {
            System.out.println(numberMsg);
            //Testing if the input is actually a whole number
            while (!numberTrue) {
                try {
                    input = Integer.parseInt(c.readLine());
                    numberTrue = true;

                } catch (NumberFormatException e) {
                    System.out.println(errorMessageNonNumeric);
                }
            }
            //Testing whether the number is within the min and max numbers
            realInt = input >= min && input <= max ? input : 0;
            //If the number isn't within the given min and max, it returns zero and the error msg is given, also the loop starts again.
            if (realInt == 0) {
                System.out.println(errorMessageNonMinAndMax);
                numberTrue = false;
            } else {
                within = true;
            }
            //If the above is true, check whether the number already exists on the users lotto numbers. If it is, start the loop again.
            if (Arrays.contains(input, lottoNumbers)) {
                System.out.println(errorMessageSameNumber);
                numberTrue = false;
            }  else {
                notTheSame = true;
            }
            if(notTheSame && within){
                //If all is good, send the number to the Array.
                properNumber = true;
            }
        }
            return realInt;
    }

    public static void playLotto(int min, int max, int[] playerNumbers) {
        int contains = 0;
        int [] containsWeeks = new int[playerNumbers.length];
        int [] containsYears;
        int weeks = 0;
        System.out.println(calc);
        while(contains < 7) {
            // Generate random lotto numbers
            int[] lottoNumbers = Arrays.lottoArrayRandomNumbers(min, max);
            // Test if the numbers match (Jackpot!)
            contains = Arrays.containsSameValues(playerNumbers, lottoNumbers);
            // Go to the next week...
            weeks++;
            // Check at what point we got what.
            containsChecker(contains, weeks, containsWeeks);
        }
        for (int i = 0; i < containsWeeks.length; i++) {
            System.out.println(containsWeeks[i]);
        }

            //Calculating the years all the wins took
        containsYears = Math.weeksToYears(containsWeeks);
            //Calculating the leftover (rounding) weeks for the wins.
        Math.leftoverWeeks(containsWeeks, containsYears);
        //Print the results
        printResults(containsYears, containsWeeks);
    }

    public static int [] containsChecker(int contains,int weeks, int[] containsWeeks) {
        //Check if the current lottery win is not initialized and initialize it with how many weeks it took..
        while (contains >0) {
            contains--;
            if(containsWeeks[contains] == 0) {
                containsWeeks[contains] = weeks;
            }
        }
        return containsWeeks;
    }

    public static void printResults(int[] containsYears, int[]containsWeeks) {
        int amount = 1;
        for (int i = 0; i < containsWeeks.length; i++) {
            System.out.println("Got " + amount + " right, it took " + containsYears[i] + " years and " + containsWeeks[i] + " weeks");
            amount++;
        }
    }
}
