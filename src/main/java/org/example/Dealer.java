package org.example;

public class Dealer {

    private static Dealer instance;
    private int carsDelivered = 0;
    private int carCapacity = 0;
    public static final int DELAY = 3000;
    public static final int SMALL_DELAY = 1000;
    public static final int TARGET = 10;

    private Dealer() {
    }

    public static synchronized Dealer getInstance() {
        if (instance == null) {
            instance = new Dealer();
        }
        return instance;
    }

    public synchronized void deliverCar() {
        try {
            while (getCarsDelivered() <= TARGET) {
                Thread.sleep(DELAY);
                setCarsDelivered(getCarsDelivered() + 1);
                setCarCapacity(getCarCapacity() + 1);
                System.out.println("Дилер доставил 1 авто в шоурум, в наличии " + getCarCapacity() + " автомобилей.");
                notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sellCar() {
        try {
            while (getCarCapacity() == 0) {
                System.out.println("Ожидаем поставки авто.");
                wait();
            }
            Thread.sleep(SMALL_DELAY);
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто.");
            setCarCapacity(getCarCapacity() - 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sellCar();
    }

    public int getCarCapacity() {
        return carCapacity;
    }

    public int getCarsDelivered() {
        return carsDelivered;
    }

    private void setCarCapacity(int carCapacity) {
        this.carCapacity = carCapacity;
    }

    private void setCarsDelivered(int carsDelivered) {
        this.carsDelivered = carsDelivered;
    }
}
