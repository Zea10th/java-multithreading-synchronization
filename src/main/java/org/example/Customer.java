package org.example;

public class Customer implements Runnable {
    private final CarShop carShop;

    public Customer(CarShop carShop) {
        this.carShop = carShop;
    }

    @Override
    public void run() {
        while (!carShop.isClosed()) {
            carShop.sellCar();
        }
    }
}
