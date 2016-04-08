package ua.kiev.PhaserImplementation;


import java.util.concurrent.Callable;
import java.util.concurrent.Phaser;

public class CalculateRunnable implements Callable<Long> {

    private int [] resultsOfThread;
    private long resultOfExecution;
    private Phaser phaser;

    public CalculateRunnable(int[] resultsOfThread, Phaser phaser) {
        this.resultsOfThread = resultsOfThread;
        this.resultOfExecution = 0L;
        this.phaser = phaser;
    }

    @Override
    public Long call() {
        for (int l : resultsOfThread) {
            resultOfExecution += l*l;
        }
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " result of thread execution is " + resultOfExecution);
        System.out.println(threadName + " Start awaiting on Phase " + phaser.getPhase());
        phaser.arriveAndAwaitAdvance();
        System.out.println(threadName + " stop awaiting on Phase " + phaser.getPhase());
        return resultOfExecution;
    }

    public int[] getResultsOfThread() {
        return resultsOfThread;
    }

    public long getResultOfExecution() {
        return resultOfExecution;
    }

    public Phaser getPhaser() {
        return phaser;
    }
}