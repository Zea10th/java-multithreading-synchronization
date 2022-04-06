package org.example;

public class Customer implements Runnable {
    private final CarShop carShop;
    private static byte ticketNo = 0;
    private static long delay = 3000;
    private final String CUSTOMER_NAME;

    public Customer(CarShop carShop) {
        this.carShop = carShop;
        CUSTOMER_NAME = "Покупатель " + ++ticketNo;
    }

    @Override
    public void run() {
        while (!carShop.isClosed()) {
            System.out.println(CUSTOMER_NAME + " зашел в автосалон.");

            try {
                Thread.sleep(delay);
                if (carShop.sellCar()) {
                    System.out.println("Success: " + CUSTOMER_NAME + " уехал на новеньком авто.");
                } else {
                    System.out.println("Failed: " + CUSTOMER_NAME + " не дождался и вышел из автосалона.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
