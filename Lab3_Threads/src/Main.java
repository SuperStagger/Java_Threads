package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter storage capacity: ");
        int storageSize = scanner.nextInt();

        System.out.print("Enter number of producers: ");
        int producers = scanner.nextInt();

        System.out.print("Enter number of consumers: ");
        int consumers = scanner.nextInt();

        System.out.print("Enter total number of items: ");
        int totalItems = scanner.nextInt();

        scanner.close();

        Main main = new Main();
        main.starter(storageSize, producers, consumers, totalItems);
    }

    private void starter(int storageSize, int producers, int consumers, int totalItems) {
        Manager manager = new Manager(storageSize);

        int baseItemsPerProducer = totalItems / producers;
        int extraProducer = totalItems % producers;

        int baseItemsPerConsumer = totalItems / consumers;
        int extraConsumer = totalItems % consumers;

        // Запускаємо всіх виробників
        for (int i = 0; i < producers; i++) {
            int itemsToProduce = baseItemsPerProducer + (i < extraProducer ? 1 : 0);
            new Producer(itemsToProduce, manager, i + 1); // передаємо id для виводу
        }

        // Запускаємо всіх споживачів
        for (int i = 0; i < consumers; i++) {
            int itemsToConsume = baseItemsPerConsumer + (i < extraConsumer ? 1 : 0);
            new Consumer(itemsToConsume, manager, i + 1); // передаємо id для виводу
        }
    }
}