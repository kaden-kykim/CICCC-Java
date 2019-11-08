package ca.ciccc.wmad.kaden.assignment.n3.model;

import java.util.Vector;

public class AGThreadPool {

    private static final int NUM_THREAD = 50, FULL_NUM_QUEUE = NUM_THREAD * 2;
    private final Object idleMutex = new Object(), runMutex = new Object();

    private Callback callback;
    private Vector<AGThread> waitQueue;
    private Vector<AGThread> runningThreads;
    private Thread scheduler;

    public AGThreadPool(Callback callback) {
        this.callback = callback;
        this.waitQueue = new Vector<>();
        this.runningThreads = new Vector<>();
    }

    public boolean isFull() {
        return waitQueue.size() >= FULL_NUM_QUEUE;
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
                        if (waitQueue.isEmpty() || runningThreads.size() > NUM_THREAD) {
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
