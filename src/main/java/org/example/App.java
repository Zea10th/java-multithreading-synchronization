package org.example;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static final long DELAY = 1000L;
    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(4);
        final CarShop carShop = new CarShop();

        executorService.submit(new CarManufacturer(carShop));

        for (int i = 0; i < 3; i++) {
            Thread.sleep(DELAY);
            executorService.submit(new Thread(null, new Customer(carShop), "Покупатель" + i));
        }

        while (true) {
            Thread.sleep(DELAY);
            if (carShop.isClosed()) {
                executorService.shutdown();
                break;
            }
        }
    }
}
