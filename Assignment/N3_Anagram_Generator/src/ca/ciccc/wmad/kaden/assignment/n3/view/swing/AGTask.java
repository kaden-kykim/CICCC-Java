package ca.ciccc.wmad.kaden.assignment.n3.view.swing;

import javax.swing.*;

public class AGTask extends SwingWorker<Void, Void> {

    private Callback callback;

    public AGTask(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected Void doInBackground() {
        callback.processTask();
        return null;
    }

    @Override
    protected void done() {
        callback.taskDone();
    }

    public void updateProgress(int progress) {
        setProgress(progress);
    }

    interface Callback {

        void processTask();

        void taskDone();

    }
}
