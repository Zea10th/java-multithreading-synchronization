package org.example;

public class CarManufacturer implements Runnable {
    private static final int DAILY_OUTPUT = 10;
    private static final int DELAY = 2_500;
    private final CarShop carShop;

    public CarManufacturer(CarShop carShop) {
        this.carShop = carShop;
    }

    @Override
    public void run() {
        for (int i = 0; i < DAILY_OUTPUT; i++) {
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            carShop.obtainCar();
        }
        System.out.println("Производитель прекратил работу на сегодня.");
        carShop.setSupplyOver(true);
    }
}
