package by.it.shulga.jd01.jd02_01;

import java.util.HashMap;
import java.util.Map;

class Buyer extends Thread implements IBuyer, IUseBacket {
    private Map<String, Integer> basket = new HashMap<>();
    private boolean pensioneer = false;
    private double slow = 1.0;

    public Buyer(int name) {
        super("Buyer " + name);
        Dispatcher.buyerCounter++;
    }


    @Override
    public void run() {
        if (Helper.randNum(1, 4) == 4) makePensioneer();
        enterToMarket();
        takeBacket();
        chooseGoods();
        putGoodsToBacket();
        goOut();
    }

    public void makePensioneer() {
        pensioneer = true;
        slow = 1.5;
        super.setName(this.getName() + " (pensioneer)");
    }

    @Override
    public void takeBacket() {
        try {
            sleep((long) (Helper.randNum(500, 2000) / Dispatcher.SPEED_BOOST * slow));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this + " take basket");
    }

    @Override
    public void putGoodsToBacket() {
        int randNum = Helper.randNum(1, 4);
        for (int goods = 1; goods <= randNum; goods++) {
            try {
                sleep((long) (Helper.randNum(500, 2000) / Dispatcher.SPEED_BOOST * slow));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int iteratorCounter = 1;
            int selectedGood = Helper.randNum(1, 7);
            for (Map.Entry<String, Integer> entry : Helper.goodsMap.entrySet()) {
                if (iteratorCounter == selectedGood) {
                    basket.put(entry.getKey(), entry.getValue());
                    System.out.println(this + " put " + entry.getKey() + " cost: " + entry.getValue());
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
        try {
            sleep((long) (Helper.randNum(500, 2000) / Dispatcher.SPEED_BOOST * slow));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this + " picked goods");
    }

    @Override
    public void goOut() {
        System.out.println(this + " went out from our shop");
        Dispatcher.buyerCounter--;
    }
}
