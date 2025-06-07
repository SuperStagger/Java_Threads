package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of threads: ");
        int numberOfThreads = scanner.nextInt();

        for (int i = 1; i <= numberOfThreads; i++) {
            Breaker breaker = new Breaker(i * 2); // 2s, 4s, 6s, ...
            MyThread myThread = new MyThread(i, breaker);
            myThread.start();
            new Thread(breaker).start();
        }

        scanner.close();
    }
}