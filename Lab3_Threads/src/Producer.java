package com.company;

public class Producer implements Runnable{
    private final int itemNumbers;
    private final Manager manager;
    private final int id;

    public Producer(int itemNumbers, Manager manager, int id) {
        this.itemNumbers = itemNumbers;
        this.manager = manager;
        this.id = id;
        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < itemNumbers; i++) {
            try {
                manager.full.acquire();
                manager.access.acquire();

                String item = "item_" + id + "_" + i;
                manager.storage.add(item);
                System.out.println("Producer #" + id + " produced " + item);

                manager.access.release();
                manager.empty.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}