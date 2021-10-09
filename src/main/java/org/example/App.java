package org.example;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(4);
        final Dealer dealer = Dealer.getInstance();

        Thread thread1 = new Thread(null, dealer::sellCar, "Вася");
        Thread thread2 = new Thread(null, dealer::sellCar, "Петя");
        Thread thread3 = new Thread(null, dealer::sellCar, "Лена");
        Thread thread4 = new Thread(null, dealer::deliverCar, "Дилер");

        executorService.submit(thread1);
        executorService.submit(thread2);
        executorService.submit(thread3);
        executorService.submit(thread4);

        executorService.shutdown();
    }
}
