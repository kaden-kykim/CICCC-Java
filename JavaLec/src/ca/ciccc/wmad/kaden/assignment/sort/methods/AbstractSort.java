package ca.ciccc.wmad.kaden.assignment.sort.methods;

import ca.ciccc.wmad.kaden.assignment.sort.SortingUtilities;

import java.util.Arrays;

public abstract class AbstractSort {

    protected int[] array;

    public void setArray() {
        setArray(SortingUtilities.generateRandomArray());
        System.out.println("Input:  " + Arrays.toString(this.array));
    }

    public void setArray(int[] array) {
        if (array == null) {
            setArray();
        } else {
            this.array = Arrays.copyOf(array, array.length);
        }
    }

    public int[] getArray() {
        return array;
    }

    public abstract void executeSorting();

    protected void swap(int idx1, int idx2) {
        int temp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = temp;
    }

}
