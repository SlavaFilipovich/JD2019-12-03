package by.it.tarasevich.jd02_02;

public class Dispatcher {
    static final int K_SPEED = 10000;

    private static final Object monitor = new Object();

    private static final int PLAN = 100;

    private static int enterBuyer = 0;
    private static int countBuyer = 0;

    static boolean marketOpened() {
        synchronized (monitor) { //volatile ????
            return enterBuyer < PLAN;
        }
    }

    static boolean marketClosed() {
        synchronized (monitor) { //volatile ????
            return enterBuyer == PLAN && countBuyer == 0;
        }
    }

    static void buyerEnter() {
        synchronized (monitor) {
            enterBuyer++;
            countBuyer++;
        }
    }

    static void buyerLeave() {
        synchronized (monitor) {
            countBuyer--;
        }
    }
}
