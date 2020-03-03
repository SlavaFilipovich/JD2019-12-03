package by.it.samuseva.jd02_03;

import java.util.concurrent.atomic.AtomicInteger;

class Dispetcher {
    static  final  int K_SPEED = 1000;

    private static final int PLAN = 100;
    private static final int countCashier = 5;

    private static AtomicInteger countBuyer = new AtomicInteger(0);
    private static AtomicInteger enterBayer = new AtomicInteger(0);


    public static int getCountCashier() {
        return countCashier;
    }
    static void enterBayer(){
            enterBayer.getAndIncrement();
            countBuyer.getAndIncrement();
    }

    static void leftBayer(){
            countBuyer.getAndDecrement();
    }

    static boolean marketOpened(){
            return enterBayer.get() < PLAN;
    }
    static boolean marketClosed(){
            return PLAN == enterBayer.get() && countBuyer.get() == 0;
    }
}
