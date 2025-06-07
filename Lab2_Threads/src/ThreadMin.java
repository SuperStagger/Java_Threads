package com.company;

public class ThreadMin extends Thread {
    private final int startIndex;
    private final int finishIndex;
    private final ArrClass arrClass;

    public ThreadMin(int startIndex, int finishIndex, ArrClass arrClass) {
        this.startIndex = startIndex;
        this.finishIndex = finishIndex;
        this.arrClass = arrClass;
    }
    @Override
    public void run() {
        if (startIndex >= finishIndex) {
            arrClass.incThreadCount();
            return;
        }
        int min = arrClass.arr[startIndex];
        int minIdx = startIndex;
        for (int i = startIndex + 1; i < finishIndex; i++) {
            if (arrClass.arr[i] < min) {
                min = arrClass.arr[i];
                minIdx = i;
            }
        }
        arrClass.collectMin(min, minIdx);
        arrClass.incThreadCount();
    }
}