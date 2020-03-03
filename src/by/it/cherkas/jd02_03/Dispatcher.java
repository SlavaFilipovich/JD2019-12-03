package by.it.cherkas.jd02_03;

import java.util.concurrent.atomic.AtomicInteger;

class Dispatcher {

    static final int K_SPEED = 10000;

    private static final int PLAN = 100;

    private static AtomicInteger enterBuyer = new AtomicInteger(0);
    private static AtomicInteger countBuyer = new AtomicInteger(0);

    static boolean marketOpened(){

            return enterBuyer.get() < PLAN;

    }

    static boolean marketClosed(){

            return enterBuyer.get() == PLAN && countBuyer.get() ==0;

    }

    static void buyerEnter() {
            enterBuyer.getAndIncrement();
            countBuyer.getAndIncrement();
        }


    static void buyerLeave() {
            countBuyer.getAndDecrement();
        }
    }

