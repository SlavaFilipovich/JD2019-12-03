package by.it.shulga.jd01.jd02_02;

import java.util.ArrayList;
import java.util.List;

class Market {
    private BuyerQueue buyerQueue = new BuyerQueue();
    private Dispatcher dispatcher = new Dispatcher();
    private final Object monitorCashier = new Object();
    private List<Buyer> buyersInMarket = new ArrayList<>(1000);
    private List<Thread> cashierInMarket = new ArrayList<>(dispatcher.cashierMax);

    public static void main(String[] args) {
        Market market = new Market();
        market.writeGoodsMap();
        System.out.println("****** Open shop ******");
        market.letInCashier();
        market.letInAdministrator();
        market.letInBuyers();

        for (Buyer buyer : market.buyersInMarket) {
            try {
                buyer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Thread cashier : market.cashierInMarket) {
            try {
                cashier.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.out.println("****** Close shop ******");

    }

    public void writeGoodsMap() {
        dispatcher.writeGoodsMap();
    }

    public void letInCashier() {
        for (int i = 1; i <= dispatcher.cashierMax; i++) {
            Cashier cashier = new Cashier(i , buyerQueue, monitorCashier, dispatcher);
            Thread thread = new Thread(cashier);
            cashierInMarket.add(thread);
            thread.start();
        }
    }

    public void letInAdministrator() {
        Administrator administrator = new Administrator(buyerQueue, monitorCashier, dispatcher);
        administrator.start();
    }

    public void letInBuyers() {
        int counter = 1;
        int letIn = 1;
        int sec = 1;
        while (dispatcher.planIsNotCompleted()) {
            if ((dispatcher.getBuyerOnline() < sec % 60 && sec % 60 <= 30) || //UP
                    (dispatcher.getBuyerOnline() <= 40 + (30 - sec % 60) && sec % 60 > 30)) {   //DOWN
                letIn = Helper.randNumUntil(4);
                for (int j = 1; j <= letIn; j++) {
                    if (dispatcher.planIsNotCompleted()) {
                        Buyer buyer = new Buyer(counter++, buyerQueue, dispatcher);
                        buyer.start();
                        buyersInMarket.add(buyer);
                    }
                }
            }
            sec++;
            Helper.delay(1000);

        }
    }
}

