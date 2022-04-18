package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarShop extends ReentrantLock {

    private static Lock lock;
    private volatile int carCapacity = 0;
    private boolean isSupplyOver = false;
    private boolean isClosed = false;

    public CarShop() {
        lock = new ReentrantLock();
    }

    public boolean sellCar() {
        boolean isSold = false;
        try {
            lock.lock();
            if (carCapacity != 0) {
                carCapacity--;
                isSold = true;
            }
        } finally {
            lock.unlock();
            return isSold;
        }
    }

    public void obtainCar() {
        carCapacity++;
        System.out.println("Продается новый автомобиль!");
        System.out.println("Ожидают продажи " + carCapacity + " автомобиля(ей).");
    }

    public boolean isClosed() {
        if (isSupplyOver && carCapacity == 0) isClosed = true;
        return isClosed;
    }

    public void setSupplyIsOver() {
        this.isSupplyOver = true;
    }
}
