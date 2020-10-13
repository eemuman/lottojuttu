package fi.tuni.tamk.tiko.eemil.util;

import java.io.Console;

public class MyConsole {
    static localestrings y = new localestrings();
    static Console c = System.console();

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

    public static int containsChecker(int contains, int weeks, int jackWeeks) {
        if (contains == 7) {
            jackWeeks = weeks;
        }
        return jackWeeks;
    }

    public static void printResults(int[] containsYears, int[] containsWeeks) {
        int amount = 1;
        for (int i = 0; i < containsWeeks.length; i++) {
            System.out.println(y.allpt1 + amount + y.allpt2 + containsYears[i] + y.allpt3 + containsWeeks[i] + y.allpt4);
            amount++;
        }
    }

    public static void printResults(int jackYears, int jackWeeks) {
        System.out.println(y.jackpotpt1 + jackYears + y.jackpotpt2 + jackWeeks + y.jackpotpt3);
    }

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

    public static void startUp() {
        System.out.println(y.welocme);

    }

    public static boolean realIntChecker(int realInt) {
        if (realInt == 0) {
            System.out.println(y.errorMessageNonMinAndMax);
            return true;
        }
        return false;
    }

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
