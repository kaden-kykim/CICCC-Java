package ca.ciccc.wmad.kaden.assignment.n3.model;

import ca.ciccc.wmad.kaden.assignment.n3.model.setting.AGSetting;

import java.util.Vector;

public class AGThreadPool {

    private static final int THREAD_MUL_FACTOR = 25, FULL_QUEUE_MUL_FACTOR = 2;
    private final Object idleMutex = new Object(), runMutex = new Object();

    private Callback callback;
    private Vector<AGThread> waitQueue;
    private Vector<AGThread> runningThreads;
    private Thread scheduler;

    private int numOfThread, fullNumOfQueue;

    public AGThreadPool(Callback callback) {
        this.callback = callback;
        this.waitQueue = new Vector<>();
        this.runningThreads = new Vector<>();

        this.numOfThread = AGSetting.getInstance().getSettingSpeed() * THREAD_MUL_FACTOR;
        this.fullNumOfQueue = numOfThread * FULL_QUEUE_MUL_FACTOR;
    }

    public boolean isFull() {
        return waitQueue.size() >= fullNumOfQueue;
    }

    public boolean isRunning() {
        return (runningThreads.size() > 0);
    }

    public void addAGThread(String str) {
        synchronized (idleMutex) {
            waitQueue.add(new AGThread(str));
        }

        if (scheduler == null) {
            scheduler = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        if (waitQueue.isEmpty() || runningThreads.size() > numOfThread) {
                            Thread.sleep(10);
                        } else {
                            AGThread t = null;
                            synchronized (idleMutex) {
                                if (waitQueue.size() > 0) {
                                    t = waitQueue.remove(0);
                                }
                            }
                            if (t != null && t.getState() == Thread.State.NEW) {
                                t.moveToRunning();
                            }
                        }
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            });
            scheduler.start();
        }
    }

    public void stop() {
        if (scheduler != null) {
            scheduler.interrupt();
            scheduler = null;
        }

        synchronized (idleMutex) {
            if (waitQueue != null && !waitQueue.isEmpty()) {
                for (AGThread t : waitQueue) {
                    t.moveToRunning();
                }
                waitQueue.clear();
            }
        }
    }

    interface Callback {

        void agTask(String str);
    }

    class AGThread extends Thread {

        private final String combination;

        AGThread(String combination) {
            this.combination = combination;
        }

        @Override
        public void run() {
            callback.agTask(combination);
            moveToFinish();
        }

        public void moveToRunning() {
            if (getState() == State.NEW) {
                synchronized (runMutex) {
                    runningThreads.add(this);
                }
                start();
            }
        }

        private void moveToFinish() {
            synchronized (runMutex) {
                runningThreads.remove(this);
            }
        }
    }
}
