package ca.ciccc.wmad.kaden.assignment.sort.methods;

import java.util.Arrays;

class MergeSort extends AbstractSort {
    @Override
    public void executeSorting() {
        this.array = mergeSort(this.array);
    }

    private int[] mergeSort(int[] array) {
        if (array.length <= 1) {
            return array;
        }

        return merge(mergeSort(Arrays.copyOfRange(array, 0, array.length / 2)),
                mergeSort(Arrays.copyOfRange(array, array.length / 2, array.length)));
    }

    private int[] merge(int[] lArr, int[] rArr) {
        int[] result = new int[lArr.length + rArr.length];

        for (int l = 0, r = 0; l < lArr.length || r < rArr.length; ) {
            result[l + r] = (lArr[l] < rArr[r]) ? lArr[l++] : rArr[r++];

            if (l == lArr.length) {
                System.arraycopy(rArr, r, result, l + r, rArr.length - r);
                break;
            }

            if (r == rArr.length) {
                System.arraycopy(lArr, l, result, l + r, lArr.length - l);
                break;
            }
        }

        return result;
    }
}
