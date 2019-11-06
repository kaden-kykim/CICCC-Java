package ca.ciccc.wmad.kaden.assignment.n3.model;

import java.util.ArrayList;

public class AGThreadPool {

    private static final int NUM_THREAD = 40, FULL_NUM_QUEUE = NUM_THREAD * 2;
    private final Object idleMutex = new Object();

    private Callback callback;
    private ArrayList<AGThread> waitQueue;
    private ArrayList<AGThread> runningThreads;
    private Thread scheduler;

    public AGThreadPool(Callback callback) {
        this.callback = callback;
        this.waitQueue = new ArrayList<>();
        this.runningThreads = new ArrayList<>();
    }

    public boolean isFull() {
        return waitQueue.size() == FULL_NUM_QUEUE;
    }

    public boolean isRunning() {
        return (runningThreads.size() > 0);
    }

    public void addAGThread(String str) {
        waitQueue.add(new AGThread(str));
        if (scheduler == null) {
            scheduler = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        if (waitQueue.isEmpty() || runningThreads.size() > NUM_THREAD) {
                            Thread.sleep(10);
                        } else {
                            AGThread t;
                            synchronized (idleMutex) {
                                t = waitQueue.remove(0);
                            }
                            if (t != null) {
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
        }
        if (waitQueue != null && !waitQueue.isEmpty()) {
            synchronized (idleMutex) {
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
            moveToWaiting();
        }

        public void moveToRunning() {
            if (getState() == State.NEW) {
                synchronized (idleMutex) {
                    runningThreads.add(this);
                }
                start();
            }
        }

        private void moveToWaiting() {
            synchronized (idleMutex) {
                runningThreads.remove(this);
            }
        }
    }
}
