package org.example;

import java.util.Random;

public class Customer implements Runnable {
    private final CarShop carShop;
    private static byte ticketNo = 0;
    private long delay;
    private final String CUSTOMER_NAME;

    public Customer(CarShop carShop) {
        this.carShop = carShop;
        ticketNo++;
        CUSTOMER_NAME = "Покупатель " + ticketNo;
        delay = new Random().nextInt(3000);
    }

    @Override
    public void run() {

        while (!carShop.isClosed()) {
            System.out.println(CUSTOMER_NAME + " зашел в автосалон.");

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (carShop.sellCar(delay)) {
                System.out.println(CUSTOMER_NAME + " уехал на новеньком авто.");
            } else {
                System.out.println(CUSTOMER_NAME + " не дождался и вышел из автосалона.");
            }
        }
    }
}
