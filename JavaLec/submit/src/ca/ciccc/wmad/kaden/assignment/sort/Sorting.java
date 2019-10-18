package ca.ciccc.wmad.kaden.assignment.sort;

import ca.ciccc.wmad.kaden.assignment.sort.methods.AbstractSort;
import ca.ciccc.wmad.kaden.assignment.sort.methods.SortMethods;

import java.util.Arrays;

public class Sorting {

    // User Defined Constants
    private static final int LENGTH = 5000, LOWER_BOUND = -5020, UPPER_BOUND = 8130;

    public static void main(String[] args) {
        int[] unsortedArray = null;
        try {
            unsortedArray = SortingUtilities.generateRandomArray(LENGTH, LOWER_BOUND, UPPER_BOUND);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        for (SortMethods method : SortMethods.values()) {
            AbstractSort sort = SortMethods.getNewSortObject(method);
            sort.setArray(unsortedArray);

            SortingUtilities.startCheckTime();
            sort.executeSorting();
            long processTime = SortingUtilities.endCheckTime();
            int[] sortedArray = sort.getArray();

            System.out.println(method.toString());
            System.out.println("Input:  " + Arrays.toString(unsortedArray));
            System.out.println("Output: " + Arrays.toString(sortedArray));
            SortingUtilities.printCheckTime(processTime);
            System.out.println();
        }
    }
}
