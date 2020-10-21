package fi.tuni.tamk.tiko.eemil.util;

import java.io.Console;
/**
 *This class contains several methods that help during the Lotto application
 */

public class MyConsole {

    static localestrings y = new localestrings();
    static Console c = System.console();

    /**
     * This method tests the user input against several checks, the checks are as follows:
     * -That it actually is a a whole number
     * -That the number is between the min and max
     * -That it is not already been given by the user
     *
     * @param min The minimum number that the users number has to be
     * @param max The maximum number that the users number has to be
     * @param lottoNumbers Is as parameter to check whether the user has already given the number
     * @return After doing the checks, returns the users input
     */
    public static int readInt(int min, int max, int[] lottoNumbers) {
        //Reads the users given input and tests whether the input is A) a number B) a whole number C) NOT a number the user has given already.
        boolean properNumber = false;
        boolean numberTrue = false;
        int input = 0;
        int realInt = 0;
        while (!properNumber) {
            System.out.println(y.numberMsg);
            while(!numberTrue) {
                //Testing if the input is actually a whole number
                input = properNumber();
                numberTrue = true;
            }
            //Testing whether the number is within the min and max numbers
            realInt = input >= min && input <= max ? input : 0;
            //If the number isn't within the given min and max, or it has already been used, it returns zero and the error msg is given, also the loop starts again.
            if (realIntChecker(realInt) || Arrays.contains(input, lottoNumbers)) {
                numberTrue = false;
            }
            if (numberTrue) {
                //If all is good, send the number to the Array.
                properNumber = true;
            }
        }
        return realInt;
    }

    /**
     * This method is the main Lotto method, it generates new lotto numbers, tests them against the users numbers and calculates how long it took
     *
     * @param playerNumbers Users lotto numbers
     * @param userLotto Users lotto numbers with leading zeroes
     * @param onlyJackpot Parameter to check if we want to only calculate the jackpot
     * @param showAll Parameter to check if we want to show all the lotto numbers the program generates
     */
    public static void playLotto(int[] playerNumbers, String[] userLotto, boolean onlyJackpot, boolean showAll) {
        int contains = 0;
        int jackWeeks = 0;
        int jackYears;
        int[] containsWeeks = new int[playerNumbers.length];
        int[] containsYears = new int[playerNumbers.length];
        int weeks = 0;
        String[] randomLotto;
        System.out.println(y.calc);
        while (contains < 7) {
            // Generate random lotto numbers
            int[] lottoNumbers = Arrays.lottoArrayRandomNumbers();
            // Put the random numbers into order from smallest -> highest
            if (showAll || onlyJackpot) {
                Arrays.sortNumbers(lottoNumbers);
            }
            // Test if the numbers match (Jackpot!)
            if(!onlyJackpot) {
                contains = Arrays.containsSameValues(playerNumbers, lottoNumbers);
            } else {
                if(Arrays.containSameValuesOnlyJ(playerNumbers, lottoNumbers)) {
                    contains = 7;
                }
            }
            // Go to the next week...
            weeks++;
            // Check at what point we got what.
            if (!onlyJackpot) {
                containsChecker(contains, weeks, containsWeeks);
            } else {
                jackWeeks = containsChecker(contains, weeks, jackWeeks);
            }
            if (showAll) {
                randomLotto = leadingZero(lottoNumbers);
                // Print the numbers, there will be MANY!!!
                printNumbers(userLotto, randomLotto);
            }
        }
        if (!onlyJackpot) {
            //Calculating the years all the wins took
            containsYears = Math.weeksToYears(containsWeeks, containsYears);
            containsWeeks = Math.leftoverWeeks(containsWeeks, containsYears);
            //Print the results
            printResults(containsYears, containsWeeks);
        } else {
            jackYears = Math.weekstoYears(jackWeeks);
            jackWeeks = Math.leftoverWeeks(jackWeeks, jackYears);
            printResults(jackYears, jackWeeks);
        }
    }

    /**
     * This method calculates how long it took to win
     *
     * @param contains Check if the user has already won that certain win
     * @param weeks How many weeks it has been
     * @param containsWeeks Used to calculate how many weeks does it take per win
     * @return returns containsWeeks
     */
    public static int[] containsChecker(int contains, int weeks, int[] containsWeeks) {
        //Check if the current lottery win is not initialized and initialize it with how many weeks it took..
        while (contains > 0) {
            contains--;
            if (containsWeeks[contains] == 0) {
                containsWeeks[contains] = weeks;
            }
        }

        return containsWeeks;
    }

    /**
     * Polymorph of the above method, used when calculating only the jackpot win
     *
     * @param contains Check if the user has already won that certain win
     * @param weeks How many weeks it has been
     * @param jackWeeks Used to calculate how many weeks does it take per win
     * @return jackWeeks
     */
    public static int containsChecker(int contains, int weeks, int jackWeeks) {
        if (contains == 7) {
            jackWeeks = weeks;
        }
        return jackWeeks;
    }

    /**
     * Prints how long each won took
     *
     * @param containsYears How many years it took, per win
     * @param containsWeeks How many weeks it took, per win
     */
    public static void printResults(int[] containsYears, int[] containsWeeks) {
        int amount = 1;
        for (int i = 0; i < containsWeeks.length; i++) {
            System.out.println(y.allpt1 + amount + y.allpt2 + containsYears[i] + y.allpt3 + containsWeeks[i] + y.allpt4);
            amount++;
        }
    }

    /**
     * Polymorph of the above, used when only jackpot is enabled
     * @param jackYears How many years it took, per win
     * @param jackWeeks How many weeks it took, per win
     */
    public static void printResults(int jackYears, int jackWeeks) {
        System.out.println(y.jackpotpt1 + jackYears + y.jackpotpt2 + jackWeeks + y.jackpotpt3);
    }

    /**
     * Adds leading zeroes to an a array
     *
     * @param numbers the numbers you want to add leading zeroes into
     * @return returns the array with leading zeroes.
     */
    public static String[] leadingZero(int[] numbers) {
        //Add leading zero if number is < 10
        String[] temp = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < 10) {
                String format = String.format("%02d", numbers[i]);
                temp[i] = format;
            } else {
                temp[i] = String.valueOf(numbers[i]);
            }
        }
        return temp;
    }

    /**
     * Used to print all the calculated lotto numbers, when that parameter is enabled
     *
     * @param userLotto Users lotto numbers
     * @param randLotto Randomized lotto numbers
     */
    public static void printNumbers(String[] userLotto, String[] randLotto) {
        //Print the users and the generated lotto numbers
        System.out.print("Users numbers: ");
        for (String lotto : userLotto) {
            System.out.print("[" + lotto + "] ");
        }
        System.out.println();
        System.out.print("Lotto numbers: ");
        for (String lotto : randLotto) {
            System.out.print("[" + lotto + "] ");
        }
        System.out.println();
    }

    /**
     * A simple config, horrible execution
     *
     * @param userLotto User lottonumbers with leading zeroes
     * @param playerNumbers Users lottonumbers without leading zeroes
     */
    public static void config(String[] userLotto, int[] playerNumbers) {
        Console c = System.console();
        String config;
        boolean first = false;
        boolean second = false;
        boolean onlyJackpot = false;
        boolean showAll = false;

        //Not actually storing these at the moment
        while (!first) {
            System.out.println(y.firstQuestion);
            config = c.readLine();
            if (config.equals("1")) {
                onlyJackpot = true;
                first = true;
            } else if (config.equals("2")) {
                first = true;
            } else {
                System.out.println(y.invalidInput);
            }
        }
        while (!second) {
            System.out.println(y.secondQuestion);
            config = c.readLine();
            if (config.equals("1")) {
                showAll = true;
                second = true;
            } else if (config.equals("2")) {
                second = true;
            } else {
                System.out.println(y.invalidInput);
            }
        }
        System.out.println(y.thanks);
        playLotto(playerNumbers, userLotto, onlyJackpot, showAll);
    }


    /**
     * @param realInt An integer
     * @return returns true or false
     */
    public static boolean realIntChecker(int realInt) {
        if (realInt == 0) {
            System.out.println(y.errorMessageNonMinAndMax);
            return true;
        }
        return false;
    }

    /**
     * This method takes user input and tests that its an whole number
     * @return An integer that is tested to be a whole number
     */
    public static int properNumber() {
        boolean numberTrue = false;
        int input = 0;
        while (!numberTrue) {
            try {
                input = Integer.parseInt(c.readLine());
                numberTrue = true;

            } catch (NumberFormatException e) {
                System.out.println(y.errorMsg);
            }
        }
        return input;
    }
}
