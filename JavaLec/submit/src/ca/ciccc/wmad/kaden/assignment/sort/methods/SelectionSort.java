package ca.ciccc.wmad.kaden.assignment.sort.methods;

class SelectionSort extends AbstractSort {
    @Override
    public void executeSorting() {
        for (int i = 0; i < array.length - 1; ++i) {
            int min = array[i], minIndex = i;
            for (int j = i; j < array.length; ++j) {
                if (min > array[j]) {
                    min = array[j];
                    minIndex = j;
                }
            }
            swap(i, minIndex);
        }
    }
}
