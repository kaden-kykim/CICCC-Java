package ca.ciccc.wmad.kaden.assignment.sort.methods;

public enum SortMethods {

    INSERTION, SELECTION, BUBBLE, MERGE, QUICK;

    @Override
    public String toString() {
        return "[ " + this.name() + " SORT ]";
    }

    public static AbstractSort getNewSortObject(SortMethods sortMethods) {
        switch (sortMethods) {
            case SELECTION:
                return new SelectionSort();
            case BUBBLE:
                return new BubbleSort();
            case MERGE:
                return new MergeSort();
            case QUICK:
                return new QuickSort();
            case INSERTION:
            default:
                return new InsertionSort();
        }
    }
}
