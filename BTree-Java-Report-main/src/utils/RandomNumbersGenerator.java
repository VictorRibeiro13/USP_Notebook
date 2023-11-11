package utils;

import java.util.LinkedHashSet;
import java.util.Random;

public class RandomNumbersGenerator {
    private static final int randomSeed = 4311;

    public  static int[] generateRandomArray(int randomKeysArrayLength) {
        if (randomKeysArrayLength <= 0) randomKeysArrayLength = 10000;

        LinkedHashSet<Integer> auxHashSet = new LinkedHashSet<>();
        int[] finalIntArray = new int[randomKeysArrayLength];
        Random random = new Random(randomSeed);
        int count = 0;

        // Required to guarantee that will not be any repeated number
        while (auxHashSet.size() < randomKeysArrayLength) {
            auxHashSet.add(random.nextInt(randomKeysArrayLength * 2));
        }

        for (int value: auxHashSet) {
            finalIntArray[count] = value;
            count++;
        }

        return finalIntArray;
    }


    public static int[] pickRandomValuesFromExistentArray(int[] existentArray, int lengthNewArray) {
        LinkedHashSet<Integer> auxHashSet = new LinkedHashSet<>();
        int[] finalIntArray = new int[lengthNewArray];
        Random random = new Random();
        int count = 0;

        // Required to guarantee that will not be any repeated number
        while (auxHashSet.size() < lengthNewArray) {
            int index = random.nextInt(existentArray.length);
            auxHashSet.add(existentArray[index]);
        }

        for (int value: auxHashSet) {
            finalIntArray[count] = value;
            count++;
        }

        return finalIntArray;
    }
}
