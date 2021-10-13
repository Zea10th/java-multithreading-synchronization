package org.example;

public class CarShop {

    private static CarShop instance;
    private volatile int carCapacity = 0;
    private boolean isClosed = false;
    private boolean isSupplyOver = false;

    private CarShop() {
    }

    public static synchronized CarShop getInstance() {
        if (instance == null) {
            instance = new CarShop();
        }
        return instance;
    }


    public synchronized void sellCar() {
        while (carCapacity < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        carCapacity--;
        System.out.println(Thread.currentThread().getName() + " купил 1 автомобиль.");
        getCarCapacity();
        notify();
        if (isSupplyOver && carCapacity == 0) {
            System.out.println("Автосалон закрыт.");
            isClosed = true;
        }
    }

    public synchronized void obtainCar() {
        carCapacity++;
        System.out.println("Производитель доставил 1 автомобиль.");
        getCarCapacity();
        notify();
    }

    private void getCarCapacity() {
        System.out.println("Ожидают продажи " + carCapacity + " автомобиля(ей).");
    }

    public void setSupplyOver(boolean supplyOver) {
        isSupplyOver = supplyOver;
    }

    public boolean isClosed() {
        return isClosed;
    }
}
