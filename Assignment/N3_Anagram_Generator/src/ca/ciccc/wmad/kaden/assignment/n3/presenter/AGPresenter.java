package ca.ciccc.wmad.kaden.assignment.n3.presenter;

import ca.ciccc.wmad.kaden.assignment.n3.contract.AGContract;
import ca.ciccc.wmad.kaden.assignment.n3.model.AnagramProcess;

public class AGPresenter implements AGContract.Presenter {

    private AGContract.View view;
    private AnagramProcess anagramProcess;

    private StringBuffer stringBuffer = new StringBuffer();
    private long progressTotal;

    public AGPresenter(AGContract.View view) {
        this.view = view;
    }

    // TODO Terminate Threads
    @Override
    public void processPreTask(String inputString) {
        view.setStatusText("Preparing...");
        anagramProcess = new AnagramProcess(this, inputString);
        progressTotal = anagramProcess.getNumOfCombination();
        anagramProcess.generateCombinations();
    }

    @Override
    public void processTask() {
        while (!Thread.currentThread().isInterrupted()) {
            if (anagramProcess != null) {
                break;
            }
        }
        anagramProcess.generateAnagrams();
        view.addOutput(stringBuffer.toString());
        stringBuffer = new StringBuffer();
    }

    public synchronized void addString(String str) {
        view.addOutput(str);
    }

    public void setProgress(long progress) {
        view.setStatusText("Progress: (" + progress + "/" + progressTotal + ")");
        view.setTaskProgress((int) (progress * 100 / progressTotal));
    }
}
