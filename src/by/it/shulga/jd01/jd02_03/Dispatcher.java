package by.it.shulga.jd01.jd02_03;

import java.util.concurrent.atomic.AtomicInteger;

class Dispatcher {
    static final int K_SPEED=100;
    static final int ALL_CASHIERS=5;
    private static final int PLAN = 100;


    private static AtomicInteger enterBuyer = new AtomicInteger(0);
    private static AtomicInteger countBuyer = new AtomicInteger(0);
    private static AtomicInteger cashCount = new AtomicInteger(0);
    private static double allCash = 0;
    static final Object allCashMon=new Object();


    static boolean marketOpened() {
        return enterBuyer.get() < PLAN;
    }

    static boolean marketNotClosed() {
        return countBuyer.get() != 0;
    }

    static void buyerEnter() {
        enterBuyer.getAndIncrement();
        countBuyer.getAndIncrement();

    }

    static void buyerLeave() {
        countBuyer.getAndDecrement();
    }

    static int getCountBuyer() {
        return countBuyer.get();
    }
    static void cashOpen() {
        cashCount.getAndIncrement();

    }

    static void cashClose() {
        cashCount.getAndDecrement();
    }

    static int getCashCount() {
        return cashCount.get();

    }
    static void addAllCash(double sum) {
        synchronized (allCashMon) {
            allCash+=sum;
        }

    }

    public static double getAllCash() {
        synchronized (allCashMon) {
            return allCash;
        }
    }
}