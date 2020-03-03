package by.it.tarasevich.jd02_03;

import java.util.concurrent.atomic.AtomicInteger;

public class Dispatcher {
    static final int K_SPEED = 10000;

    private static final int PLAN = 100;

    private final static AtomicInteger enterBuyer = new AtomicInteger(0);
    private final static AtomicInteger countBuyer = new AtomicInteger(0);

    static boolean marketOpened() {
        return enterBuyer.get() < PLAN;
    }

    static boolean marketClosed() {

        return enterBuyer.get() == PLAN && countBuyer.get() == 0;

    }

    static void buyerEnter() {
        enterBuyer.getAndIncrement();
        countBuyer.getAndIncrement();
    }

    static void buyerLeave() {
        countBuyer.getAndDecrement();
    }
}
