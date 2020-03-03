package by.it.shulga.jd01.jd02_02;

class Cashier implements Runnable {
    private final Object monitorCashier;

    private String name;

    private BuyerQueue buyerQueue;
    private Dispatcher dispatcher;

    public Cashier(int num, BuyerQueue buyerQueue, Object monitorCashier, Dispatcher dispatcher) {
        name = "Cashier " + num;
        this.buyerQueue = buyerQueue;
        this.monitorCashier = monitorCashier;
        this.dispatcher = dispatcher;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void run() {
        while (!dispatcher.marketIsClosed()) {
            Buyer pensioner = buyerQueue.getFirstPensioner();
            if (pensioner != null) {
                serve(pensioner);
            } else {
                Buyer buyer = buyerQueue.getFirstBuyer();
                if (buyer != null) {
                    serve(buyer);
                } else {
                    synchronized (monitorCashier) {
                        try {
                            dispatcher.cashierClosed();
                            System.out.println(this + " is closed");
                            monitorCashier.wait();
                            dispatcher.cashierOpened();
                            System.out.println(this + " is opened");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        System.out.println(this + " sent day report and went to home");
    }

    private void serve(Buyer buyer) {
        System.out.println(this + " start serve " + buyer);
        Helper.delay(Helper.randNum(2000, 5000)); //Time to serve
        System.out.println(this + " print " + buyer + " check: " + buyer.getBasket() + " SUM: "
                + buyer.getBasket().values().stream().mapToInt(Integer::intValue).sum());
        System.out.println(this + " finished serve " + buyer);
        synchronized (buyer) { //Finished serve, buyer can continue
            buyer.notify();
        }
    }
}
