package ca.ciccc.wmad.kaden.assignment.n3.contract;

public interface AGContract {

    interface View {

        void start();

        void setTaskProgress(long progress);

        void setStatusText(String statusText);

        void addOutput(String anagram);

        void setTotalNumberOfCombination(long totalNumberOfCombination);

    }

    interface Presenter {

        void processPreTask(String inputString);

        void processTask();

    }

}
