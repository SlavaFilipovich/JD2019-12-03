package by.it.shulga.jd01.jd02_02;

import java.util.HashMap;
import java.util.Map;

class Buyer extends Thread implements IBuyer, IUseBacket {

    private volatile Map<String, Integer> basket = new HashMap<>();

    private boolean pensioneer = false;

    private BuyerQueue buyerQueue;
    private Dispatcher dispatcher;

    public Buyer(int name, BuyerQueue buyerQueue, Dispatcher dispatcher) {
        super("Buyer " + name);
        this.buyerQueue = buyerQueue;
        this.dispatcher = dispatcher;
        if (Helper.randNum(1, 4) == 4) {
            pensioneer = true;
            super.setName(this.getName() + " (pensioneer)");
        }
        dispatcher.buyerComeIn();
    }

    @Override
    public Map<String, Integer> getBasket() {
        return basket;
    }

    @Override
    public void run() {
        enterToMarket();
        takeBacket();
        chooseGoods();
        putGoodsToBacket();
        goToQueue();
        goOut();
    }

    @Override
    public void goToQueue() {
        if (this.pensioneer) {
            System.out.println(this + " go to pensioner's queue");
            buyerQueue.addToPensionerQueue(this);
        } else {
            System.out.println(this + " go to united queue");
            buyerQueue.addToQueue(this);
        }
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void takeBacket() {
        if (!pensioneer) Helper.delay((Helper.randNum(500, 2000)));
        else Helper.delay((int) (Helper.randNum(500, 2000) * 1.5));
        System.out.println(this + " take basket");
    }

    @Override
    public void putGoodsToBacket() {
        int randNum = Helper.randNum(1, 4);
        for (int goods = 1; goods <= randNum; goods++) {
            if (!pensioneer) Helper.delay((Helper.randNum(500, 2000)));
            else Helper.delay((int) (Helper.randNum(500, 2000) * 1.5));
            int iteratorCounter = 1;
            int selectedGood = Helper.randNum(1, 7);
            for (Map.Entry<String, Integer> entry : dispatcher.getGoodsMap().entrySet()) {
                if (iteratorCounter == selectedGood) {
                    String entryKey = entry.getKey();
                    Integer entryValue = entry.getValue();
                    if (basket.containsKey(entryKey)) {
                        basket.put(entryKey, basket.get(entryKey) + entryValue);
                        System.out.println(this + " put more " + entryKey + " cost: " + entryValue);
                        break;
                    }
                    basket.put(entryKey, entryValue);
                    System.out.println(this + " put " + entryKey + " cost: " + entryValue);
                    break;
                }
                iteratorCounter++;
            }
        }

    }

    @Override
    public String toString() {
        return super.getName();
    }

    @Override
    public void enterToMarket() {
        System.out.println(this + " came in to our shop");
    }

    @Override
    public void chooseGoods() {
        if (!pensioneer) Helper.delay((Helper.randNum(500, 2000)));
        else Helper.delay((int) (Helper.randNum(500, 2000) * 1.5));
        System.out.println(this + " picked goods");
    }

    @Override
    public void goOut() {
        System.out.println(this + " went out from our shop");
        dispatcher.buyerWentOut();
    }
}
