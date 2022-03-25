package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarShop extends ReentrantLock {

    private static CarShop instance;
    private static Lock lock;
    private volatile int carCapacity = 0;
    private boolean isSupplyOver = false;
    private boolean isClosed = false;

    private CarShop() {
    }

    public static CarShop getInstance() {
        if (instance == null) {
            instance = new CarShop();
            lock = new ReentrantLock();
        }
        return instance;
    }


    public boolean sellCar(long delay) {
        boolean isSold = false;
        try {
            if (lock.tryLock() && carCapacity != 0) {
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
        notifyCarCapacity();
    }

    private void notifyCarCapacity() {
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
