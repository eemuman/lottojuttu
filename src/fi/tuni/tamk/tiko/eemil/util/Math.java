package fi.tuni.tamk.tiko.eemil.util;

/**
 * Some math methods that the program uses
 */
public class Math {

    /**
     * Generate a random number
     *
     * @param min the min
     * @param max the max
     * @return the random number
     */
    public static int getRandom(int min, int max) {
        //Randomize a number and return it
        return min + (int) (java.lang.Math.random() * ((max - min) + 1));
    }

    /**
     * This method removes the random number from the random lotto number generator method
     *
     * @param rndNumbers array with the numbers
     * @param rndNumber  the number to be removed
     * @return new array without the used number
     */
    public static int[] removeRandNumb(int[] rndNumbers, int rndNumber) {
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

    /**
     * Turns weeks to years
     *
     * @param containsWeeks the contains weeks
     * @param containsYears the contains years
     * @return years
     */
    public static int [] weeksToYears (int[] containsWeeks, int[] containsYears) {
        int[] years = new int[containsWeeks.length];
        // Turning the weeks to years
        for (int i = 0; i < containsWeeks.length; i++) {
            years[i] = containsWeeks[i] / 52;
        }
        return years;
    }

    /**
     * Turn years back to weeks to calculate leftover weeks
     *
     * @param weeks the weeks
     * @param years the years
     * @return leftover weeks
     */
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

    /**
     * Calculate weeks to years, used when doing only Jackpot calculations
     *
     * @param weeks the weeks
     * @return years
     */
    public static int weekstoYears (int weeks) {
         return weeks / 52;
    }

    /**
     * years to leftover weeks, used when doing only Jackpot calculations
     *
     * @param weeks the weeks
     * @param years the years
     * @return the int
     */
    public static int leftoverWeeks(int weeks, int years) {
        years *= 52;
        weeks -= years;
        return weeks;
    }
}
