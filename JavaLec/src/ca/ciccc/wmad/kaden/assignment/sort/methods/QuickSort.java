package ca.ciccc.wmad.kaden.assignment.sort.methods;

import java.util.Arrays;

class QuickSort extends AbstractSort {
    @Override
    public void executeSorting() {
        this.array = quickSort(this.array);
    }

    private int[] quickSort(int[] array) {
        if (array.length <= 1) {
            return array;
        }

        int[] partialSortedArray = new int[array.length];
        int[] lArr = new int[array.length - 1], rArr = new int[array.length - 1];
        int pivot = array[0];
        int lIndex = 0, rIndex = 0;

        for (int i = 1; i < array.length; ++i) {
            if (array[i] < pivot) {
                lArr[lIndex++] = array[i];
            } else {
                rArr[rIndex++] = array[i];
            }
        }

        int[] subLArr = quickSort(Arrays.copyOfRange(lArr, 0, lIndex));
        System.arraycopy(subLArr, 0, partialSortedArray, 0, lIndex);
        partialSortedArray[lIndex] = pivot;
        int[] subRArr = quickSort(Arrays.copyOfRange(rArr, 0, rIndex));
        System.arraycopy(subRArr, 0, partialSortedArray, lIndex + 1, rIndex);

        return partialSortedArray;
    }
}
