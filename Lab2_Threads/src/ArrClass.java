package com.company;

public class ArrClass {
    private final int dim;
    private final int threadNum;
    public final int[] arr;
    private int globalMin;
    private int globalMinIdx;

    public ArrClass(int dim, int threadNum) {
        this.dim = dim;
        arr = new int[dim];
        this.threadNum = threadNum;
        for (int i = 0; i < dim; i++) {
            arr[i] = i;
        }
        int badIdx = (int)(Math.random() * dim);
        arr[badIdx] = -20000000;
    }
    public String partMin(int startIndex, int finishIndex){
        int min = arr[startIndex];
        int minIdx = startIndex;
        for(int i = startIndex + 1; i < finishIndex; i++){
            if (arr[i] < min) {
                min = arr[i];
                minIdx = i;
            }
        }
        return "[Single thread] Min: " + min + " at index " + minIdx;
    }
    private int threadCount = 0;
    synchronized public void incThreadCount(){
        threadCount++;
        notify();
    }
    private int getThreadCount() {
        return threadCount;
    }
    synchronized public void collectMin(int min, int idx){
        if (min < globalMin) {
            globalMin = min;
            globalMinIdx = idx;
        }
    }
    public String threadMin(){
        threadCount = 0;
        globalMin = arr[0];
        globalMinIdx = 0;
        ThreadMin[] threadMins = new ThreadMin[threadNum];
        int chunk = dim / threadNum;
        for (int i = 0; i < threadNum; i++) {
            int start = i * chunk;
            int end = (i == threadNum - 1) ? dim : (start + chunk);
            threadMins[i] = new ThreadMin(start, end, this);
            threadMins[i].start();
        }
        synchronized (this) {
            while (getThreadCount() < threadNum){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return "[Parallel] Min: " + globalMin + " at index " + globalMinIdx;
    }
}