package com.company;

public class MyThread extends Thread {
    private final int id;
    private final Breaker breaker;

    public MyThread(int id, Breaker breaker) {
        this.id = id;
        this.breaker = breaker;
    }

    @Override
    public void run() {
        long sum = 0;
        while (!breaker.isCanBreak()) {
            sum++;
        }
        System.out.println("Thread " + id + " - sum: " + sum);
    }
}
