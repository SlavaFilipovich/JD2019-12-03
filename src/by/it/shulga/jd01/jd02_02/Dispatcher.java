package by.it.shulga.jd01.jd02_02;

import java.util.HashMap;
import java.util.Map;

class Dispatcher {


    final static int SPEED_BOOST = 100;
    final int cashierMax = 5;
    final int plan = 100;

    private int buyerOnline = 0;
    private int dayBuyerNum = 0;

    private final Map<String, Integer> goodsMap = new HashMap<>();

    public void writeGoodsMap() {
        goodsMap.put("water", 1);
        goodsMap.put("bread", 2);
        goodsMap.put("apples", 3);
        goodsMap.put("milk", 4);
        goodsMap.put("eggs", 5);
        goodsMap.put("cake", 6);
        goodsMap.put("meat", 7);
    }

    public Map<String, Integer> getGoodsMap() {
        return goodsMap;
    }

    private final Object onlineCashierMonitor = new Object();
    private int onlineCashier = cashierMax;

    public synchronized boolean planIsNotCompleted() {
        return dayBuyerNum < plan;
    }

    public synchronized boolean marketIsClosed() {
        return dayBuyerNum == plan && buyerOnline == 0;
    }

    public synchronized void buyerComeIn() {
        buyerOnline++;
        dayBuyerNum++;
    }

    public synchronized int getBuyerOnline() {
        return buyerOnline;
    }

    public synchronized void buyerWentOut() {
        buyerOnline--;
    }

    public void cashierClosed() {
        synchronized (onlineCashierMonitor){
            onlineCashier--;
        }
    }

    public void cashierOpened() {
        synchronized (onlineCashierMonitor){
            onlineCashier++;
        }
    }

    public int getOnlineCashier() {
        synchronized (onlineCashierMonitor){
            return  onlineCashier;
        }
    }
}
