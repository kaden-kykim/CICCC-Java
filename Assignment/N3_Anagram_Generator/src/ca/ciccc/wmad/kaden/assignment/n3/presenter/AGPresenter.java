package ca.ciccc.wmad.kaden.assignment.n3.presenter;

import ca.ciccc.wmad.kaden.assignment.n3.contract.AGContract;
import ca.ciccc.wmad.kaden.assignment.n3.model.AnagramProcess;
import ca.ciccc.wmad.kaden.assignment.n3.model.dic.AGDictionary;

public class AGPresenter implements AGContract.Presenter {

    private AGContract.View view;
    private AnagramProcess anagramProcess;

    private long progressTotal;

    public AGPresenter(AGContract.View view) {
        this.view = view;
        AGDictionary.getInstance();
    }

    @Override
    public void processPreTask(String inputString) {
        anagramProcess = new AnagramProcess(this, inputString);
        progressTotal = anagramProcess.getNumOfCombination();
        view.setTotalNumberOfCombination(progressTotal);
        anagramProcess.generateCombinations();
    }

    @Override
    public void processTask() {
        while (!Thread.currentThread().isInterrupted()) {
            if (anagramProcess != null) {
                break;
            }
        }
        if (!Thread.currentThread().isInterrupted()) {
            anagramProcess.generateAnagrams();
            anagramProcess = null;
        }
    }

    public synchronized void addString(String str) {
        view.addOutput(str);
    }

    public void setProgress(long progress) {
        view.setStatusText("Progress: (" + progress + "/" + ((progressTotal > 0) ? progressTotal : "Over " + Long.MAX_VALUE) + ")");
        view.setTaskProgress(progress);
    }
}
