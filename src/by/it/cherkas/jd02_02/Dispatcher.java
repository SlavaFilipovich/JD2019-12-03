package by.it.cherkas.jd02_02;

 class Dispatcher {

    static final int K_SPEED = 10000;
    private static final Object monitor = new Object();

    private static final int PLAN = 100;

    private static int enterBuyer = 0;
    private static int countBuyer = 0;

    static boolean marketOpened(){
        synchronized (monitor) {
            return enterBuyer < PLAN;
        }
    }

    static boolean marketClosed(){
        synchronized (monitor) {
            return enterBuyer == PLAN && countBuyer ==0;
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
