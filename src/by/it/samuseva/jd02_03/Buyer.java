package by.it.samuseva.jd02_03;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

class Buyer extends Thread implements IBuyer, IUseBacket {
    boolean pensioner = false;
    private boolean flagQueue = false;

    private static Semaphore semaphore = new Semaphore(20);
    private Map<String, Integer> goodBacket = new HashMap<>();

    public Map<String, Integer> getGoodBacket() {
        return goodBacket;
    }

    void setFlagQueue(boolean flagQueue) {
        this.flagQueue = flagQueue;
    }

    Buyer(int number) {
        super("Buyer №" + " " + number);
        Dispetcher.enterBayer();
    }

    @Override
    public void run() {
        enterToMarket();
        try {
            semaphore.acquire();
            chooseGoods();
            goToQueue();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
        goOut();
    }

    @Override
    public void enterToMarket() {
        System.out.println(this + " " + "entered the market.");

    }

    @Override
    public void chooseGoods() {
        System.out.println(this + " " +"started choosing by goods.");
        takeBacket();
        int timeout = Helper.random(500, 2000);
        if (pensioner) Helper.sleep(timeout*3/2);
        else  Helper.sleep(timeout);
        System.out.println(this + " " + "closed choosing by goods.");

    }

    @Override
    public void goToQueue() {
        System.out.println(this + " " + "go to queue.");
        QueueBuyer.add(this);
        synchronized (this){
            try {
                this.wait();
                flagQueue = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void goOut() {
        System.out.println(this + " " + "left the market.");
        Dispetcher.leftBayer();

    }

    @Override
    public String toString() { return  this.getName(); }

    @Override
    public void takeBacket() {
        System.out.println(this + " " + "took a basket.");
        int timeout = Helper.random(500, 2000);
        int countGoods = Helper.random(1,4);
        for (int i = 1; i <=countGoods ; i++) {
            putGoodsToBacket();
            if (pensioner) Helper.sleep(timeout*3/2);
            else  Helper.sleep(timeout);
        }

    }

    @Override
    public void putGoodsToBacket() {
        int goodNumber = Helper.random(0, Good.goodsLength()-1);
        String good = Good.getGoods(goodNumber);

        String[] mas = good.split(" ");
        this.goodBacket.put(mas[0], Integer.parseInt(mas[1]));

        System.out.println(this + " " + "chose" + " " + good + " " + "took and put in a basket.");

    }
}
