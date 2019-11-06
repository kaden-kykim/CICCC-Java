package ca.ciccc.wmad.kaden.assignment.n3.contract;

public interface AGContract {

    interface View {

        void setTaskProgress(int progress);

        void setStatusText(String statusText);

        void addOutput(String anagram);

    }

    interface Presenter {

        void processPreTask(String inputString);

        void processTask();

    }

}
