package by.it.lozouski.jd02_03;

import java.util.concurrent.atomic.AtomicInteger;

class Dispetcher {

    private static final int BUYERS_PLAN = 100;
    static final int SPEED_K = 10000;

    private static final AtomicInteger buyersEnterCounter = new AtomicInteger(0);
    private static final AtomicInteger buyersCounter = new AtomicInteger(0);



    static boolean marketOpened() {
            return buyersEnterCounter.get() < BUYERS_PLAN;
    }

    static boolean marketClosed() {
            return buyersEnterCounter.get() == BUYERS_PLAN && buyersCounter.get() == 0;
    }

    static void buyerEnteredMarket() {
            buyersEnterCounter.getAndIncrement();
            buyersCounter.getAndIncrement();
    }

    static void buyerLeftMarket() {
            buyersCounter.getAndDecrement();
    }
}
