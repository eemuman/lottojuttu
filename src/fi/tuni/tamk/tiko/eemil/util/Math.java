package fi.tuni.tamk.tiko.eemil.util;

public class Math {

    public static int getRandom(int min, int max) {
        //Randomize a number and return it
        return min + (int) (java.lang.Math.random() * ((max - min) + 1));
    }

    public static int[] removeRandNumb(int[] rndNumbers, int rndNumber) {
        int randSkip = 0;
        int calc = 0;
        int[] newLotto = new int[rndNumbers.length -1];
        //Take the Lotto random array and remove the random number from it to prevent duplicates
        for (int i = 0; i < newLotto.length; i++) {
            //Testing if current i is the same as current number, if it is, skip it.
            if(i == rndNumber) {
                calc++;
            }
            //Add the new numbers to the new array
            newLotto[i] = rndNumbers[calc];
            calc++;
        }

        //return the new array
        return newLotto;
    }

    public static int [] weeksToYears (int[] containsWeeks, int[] containsYears) {
        int[] years = new int[containsWeeks.length];
        // Turning the weeks to years
        for (int i = 0; i < containsWeeks.length; i++) {
            years[i] = containsWeeks[i] / 52;
        }
        Math.leftoverWeeks(containsWeeks, containsYears);
        return years;
    }
    public static int[] leftoverWeeks (int[] weeks, int[] years) {
        //Turning the years back to weeks, to calculate leftover weeks
        for (int i = 0; i < years.length; i++) {
            years[i] *= 52;
            weeks[i] -= years[i];
        }
        //Turning years back to years....
        for (int i = 0; i < weeks.length; i++) {
            years[i] /= 52;
        }
        return weeks;
    }

    public static int jWeekstoYears (int weeks) {
         return weeks / 52;
    }
    public static int jYearstoWeeks(int weeks, int years) {
        years *= 52;
        weeks -= years;
        return weeks;
    }
}
