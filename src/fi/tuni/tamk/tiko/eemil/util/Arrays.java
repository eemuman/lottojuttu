package fi.tuni.tamk.tiko.eemil.util;


/**
 * Array methods to use at the lotto app
 */
public class Arrays {
    static localestrings y = new localestrings();

    /**
     * To int array int [ ]. Not used
     *
     * @param array the array
     * @return the int [ ]
     */
    public static int [] toIntArray(String [] array) {
        int[] tempArray = new int[array.length];
        for (int i=0;i<array.length;++i) {
            tempArray[i] = Integer.parseInt(array[i]);
        }
        return tempArray;
    }

    /**
     * Tests whether the array already contains the number that is being tested
     *
     * @param value the value
     * @param array the array
     * @return the boolean
     */
    public static boolean contains(int value, int [] array) {
        for (int j : array) {
            if (j == value) {
                System.out.println(y.errorMessageSameNumber);
                return true;
            }
        }
        return false;
    }

    /**
     * Tests how many of the same number the two arrays have, returns the amount
     *
     * @param array1 the array 1
     * @param array2 the array 2
     * @return amount of numbers that are the same
     */
    public static int containsSameValues(int[] array1, int[] array2) {
        int amountOfContains = 0;
            for (int k : array1) {
                for (int j = 0; j < array1.length; j++) {
                    if (k == array2[j]) {
                        amountOfContains++;
                    }
                }
            }
        return amountOfContains;
    }

    /**
     * Faster test that is being used when doing only jackpot calculations, it returns a false as soon as there is a different number between the arrays
     *
     * @param array1 the array 1
     * @param array2 the array 2
     * @return the boolean
     */
    public static boolean containSameValuesOnlyJ(int[] array1, int[] array2) {
        for (int i = 0; i < array1.length; i++) {
            if(array1[i] != array2[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * Users lotto number array sorter
     *
     * @param min the min number that can be given
     * @param max the max number that can be given
     * @return users lotto numbers in array
     */
    public static int[] lottoArrayUser(int min, int max) {
        //Make a new array for users Lotto numbers
        int[] lottoNumbers = new int[7];
        // Start building users Lotto numbers array
        for (int i = 0; i < lottoNumbers.length; i++) {
            lottoNumbers[i] = MyConsole.readInt(min, max, lottoNumbers);
        }
        return lottoNumbers;
    }

    /**
     * Generate lotto numbers, this method uses an array where the number is being removed after to prevent duplicates.
     *
     * @return the randomized lotto numbers
     */
    public static int[] lottoArrayRandomNumbers() {
        int tempnumber = 1;
        int number = 0;
        boolean Done = false;
        int [] RndNumbers = new int[40];
 // Build an 40 number long array which contains numbers 1....40
        for (int i = 0; i < RndNumbers.length; i++) {
            RndNumbers[i] = tempnumber;
            tempnumber++;
        }
        //Init the actual lotto numbers
        int [] RndLotto = new int[7];

        while(!Done) {
            //Get an random number to get put from the array into the lotto numbers
            int randNumber = Math.getRandom(0, RndNumbers.length - 1);

            //Take the number from the array
            int arrayNumberRand = RndNumbers[randNumber];

            //Insert the number into the array
            RndLotto[number] = arrayNumberRand;

            //Remove the number from the array
            RndNumbers = Math.removeRandNumb(RndNumbers, randNumber);
            //Add a number to the array
            number++;
            //Check if we've gone through the whole array, if yes, return the new Lotto numbers.
            Done = number == RndLotto.length;
        }

        return RndLotto;
    }

    /**
     * Test duplicates boolean. not used
     *
     * @param arraya the arraya
     * @return the boolean
     */
    public static boolean testDuplicates(int[] arraya) {
        //Test if an Array has duplicate numbers
        for (int i = 0; i < arraya.length; i++) {
            for (int j = i + 1; j < arraya.length; j++) {
                if(arraya[i] == arraya[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Sort an array so that the numbers go from the smallest to largest
     *
     * @param numbers the numbers
     * @return sorted array
     */
    public static int[] sortNumbers(int[] numbers) {
        //Lets put the numbers into order from smallest to highest
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i +1; j < numbers.length; j++) {
                if(numbers[i] > numbers[j]) {
                    int temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
        return numbers;
    }

    

}
