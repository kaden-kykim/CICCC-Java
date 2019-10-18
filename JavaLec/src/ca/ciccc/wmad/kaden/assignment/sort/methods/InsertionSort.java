package ca.ciccc.wmad.kaden.assignment.sort.methods;

class InsertionSort extends AbstractSort {
    @Override
    public void executeSorting() {
        for (int i = 1; i < array.length; ++i) {
            for (int j = i; j >= 1; --j) {
                if (array[j] < array[j - 1]) {
                    swap(j, j-1);
                } else {
                    break;
                }
            }
        }
    }
}
