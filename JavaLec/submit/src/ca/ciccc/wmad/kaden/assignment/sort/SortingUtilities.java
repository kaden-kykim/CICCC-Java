package ca.ciccc.wmad.kaden.assignment.sort;

import java.util.Random;

public class SortingUtilities {

    private static final int
            DEFAULT_ARRAY_LENGTH = 20,
            DEFAULT_ARRAY_LOWER_BOUND = 0,
            DEFAULT_ARRAY_UPPER_BOUND = 100;

    private static long checkTime;

    /**
     * Generate a random array
     *
     * @param length     the LENGTH of a generated array
     * @param lowerBound the LOWER BOUND of a generated integer (inclusive)
     * @param upperBound the UPPER BOUND of a generated integer (exclusive)
     * @return the Generated Array
     * @throws IllegalArgumentException
     */
    public static int[] generateRandomArray(int length, int lowerBound, int upperBound) throws IllegalArgumentException {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0.");
        }

        if (lowerBound >= upperBound) {
            throw new IllegalArgumentException("The Lower bound must be less than the Upper bound");
        }

        int[] randomArray = new int[length];
        int range = upperBound - lowerBound;
        Random random = new Random(System.currentTimeMillis());

        for (int i = 0; i < length; ++i) {
            randomArray[i] = random.nextInt(range) + lowerBound;
        }

        return randomArray;
    }

    public static int[] generateRandomArray() {
        return generateRandomArray(DEFAULT_ARRAY_LENGTH, DEFAULT_ARRAY_LOWER_BOUND, DEFAULT_ARRAY_UPPER_BOUND);
    }

    public static void startCheckTime() {
        checkTime = System.nanoTime();
    }

    public static long endCheckTime() {
        return System.nanoTime() - checkTime;
    }

    public static void printCheckTime(long time) {
        System.out.printf("Processing Time: %.6f ms\n", time / 100000.0);
    }
}
