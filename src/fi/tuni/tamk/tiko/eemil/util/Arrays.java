package fi.tuni.tamk.tiko.eemil.util;
import fi.tuni.tamk.tiko.eemil.Main;

public class Arrays {

    public static int [] toIntArray(String [] array) {
        int[] tempArray = new int[array.length];
        for (int i=0;i<array.length;++i) {
            tempArray[i] = Integer.parseInt(array[i]);
        }
        return tempArray;
    }

    public static boolean contains(int value, int [] array) {
        for (int j : array) {
            if (j == value) {
                return true;
            }
        }
        return false;
    }

    public static int containsSameValues(int[] array1, int[] array2, boolean onlyJackpot) {
        int amountOfContains = 0;
        if(!onlyJackpot) {
            for (int k : array1) {
                for (int j = 0; j < array1.length; j++) {
                    if (k == array2[j]) {
                        amountOfContains++;
                    }
                }
            }
        } else {
            for (int i = 0; i < array1.length; i++) {
                if(array1[i] != array2[i]) {
                    break;
                }
                amountOfContains++;
            }
        }
        return amountOfContains;
    }

    public static int[] lottoArrayUser(int min, int max) {
        //Make a new array for users Lotto numbers
        int[] lottoNumbers = new int[7];
        // Start building users Lotto numbers array
        for (int i = 0; i < lottoNumbers.length; i++) {
            lottoNumbers[i] = MyConsole.readInt(min, max, lottoNumbers, Main.errorMsg, Main.errorMessageNonMinAndMax, Main.errorMessageSameNumber, Main.numberMsg);
        }
        return lottoNumbers;
    }

    public static int[] lottoArrayRandomNumbers(int min, int max) {
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
