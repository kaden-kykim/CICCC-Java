package ca.ciccc.wmad.kaden.assignment.sort.methods;

class BubbleSort extends AbstractSort {
    @Override
    public void executeSorting() {
        for (int i = array.length - 1; i >= 1; --i) {
            boolean isSwapped = false;
            for (int j = 0; j < i; ++j) {
                if (array[j] > array[j + 1]) {
                    swap(j, j + 1);
                    isSwapped = true;
                }
            }

            if (!isSwapped) {
                break;
            }
        }
    }
}
