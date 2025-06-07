package com.company;

public class Breaker implements Runnable {
    private final int waitSeconds;
    private volatile boolean canBreak = false;

    public Breaker(int waitSeconds) {
        this.waitSeconds = waitSeconds;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(waitSeconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        canBreak = true;
    }

    public boolean isCanBreak() {
        return canBreak;
    }
}
