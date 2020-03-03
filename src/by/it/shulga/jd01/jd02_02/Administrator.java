package by.it.shulga.jd01.jd02_02;


class Administrator extends Thread {
    private BuyerQueue buyerQueue;
    private final Object monitorCashier;
    private Dispatcher dispatcher;

    public Administrator(BuyerQueue buyerQueue, Object monitorCashier, Dispatcher dispatcher) {
        this.buyerQueue = buyerQueue;
        this.monitorCashier = monitorCashier;
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        while (!dispatcher.marketIsClosed()) {
            Helper.delay(1000);
            int queueSize = buyerQueue.getBuyerQueueSize() + buyerQueue.getPensionerQueueSize();
            int onlineCashier = dispatcher.getOnlineCashier();
            if (onlineCashier == 0) {
                if (queueSize > 0) {
                    synchronized (monitorCashier) {
                        monitorCashier.notify();
                    }
                }
            } else if (queueSize / onlineCashier > 5) {
                synchronized (monitorCashier) {
                    monitorCashier.notify();
                }
            }
        }
        synchronized (monitorCashier) {//after market closet restart work and let go home all Cashier
            monitorCashier.notifyAll();
        }
    }
}
