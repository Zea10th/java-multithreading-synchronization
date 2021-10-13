package org.example;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(4);
        final CarShop carShop = CarShop.getInstance();

        executorService.submit(new CarManufacturer(carShop));

        for (int i = 0; i < 3; i++) {
            executorService.submit(new Thread(null, new Customer(carShop), "Покупатель" + i));
        }
    }
}
