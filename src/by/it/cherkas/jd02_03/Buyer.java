package by.it.cherkas.jd02_03;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;


public class Buyer extends Thread implements Runnable, IBuyer, IUseBacket {
    private  int num;
    private String name;
    private Backet backet;
    private Good good;
    ConcurrentHashMap<Good, Integer> goods;
    private ConcurrentHashMap<Good, Integer> backets;
    private boolean waitingFlag = false;

    void setWaitingFlag(boolean waitingFlag){
        this.waitingFlag = waitingFlag;
    }

    public Buyer(int num, ConcurrentHashMap<Good, Integer> goods, Backet backet ) {
        super("Покупатель № " + num + " ");
        Dispatcher.buyerEnter();
        this.goods = goods;
        this.backet = backet;

    }

    @Override
    public void run(){
        enterToMarket();
        takeBacket();
        chooseGoods();
        putGoodsToBacket();
        goToQueue();
        goOut();
    }

    @Override
    public String toString(){
        return this.getName();
    }

    @Override
    public void enterToMarket(){
        System.out.println(this + "вошел в магазин");
    }

    @Override
    public void takeBacket(){
        //if (name != null){
            backet = new Backet();
            System.out.println(this + "взял корзину");
       // }
    }

    @Override
    public void chooseGoods(){
        Semaphore semaphore = new Semaphore(20);
        try {
            semaphore.acquire();
            System.out.println(this + "начал выбирать товар");
        }
        catch (InterruptedException e) {
            System.err.println(this + "выбрал товар");
        }
        finally {
            semaphore.release(20);
        }
    }

    @Override
    public void goToQueue(){
        System.out.println(this + "поешел в очередь");
        QueueBuyer.add(this);
        pause();
 //       synchronized (this){
 //           try {
 //               this.wait();
 //           } catch (InterruptedException e) {
 //               e.printStackTrace();
 //           }
 //       }
    }

    private void pause() {
        try {
            int pause = Helper.random(500, 2000);
            Thread.sleep(pause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void putGoodsToBacket() {
            int count = (int) (Math.random() * 4) + 1;
            for (int i = 0; i <= count; i++) {
                int index = (int) (Math.random() * 9);
                //Random random = new Random();
                Good[] goodsArray = goods.keySet().toArray(new Good[0]);
                good = goodsArray[index];

                if (backets == null) {
                    backets = new ConcurrentHashMap<>();
                }
                if (good != null) {
                    backets.put(good, index);
                    pause();
                    System.out.println(this + "положил " + backets + "в корзину");
                }
            }
        }

        @Override
                public void goOut(){
        Dispatcher.buyerLeave();
        --num;
        int checkSum = 0;
            for (Integer value : backets.values()) {
                checkSum += value;
            }
            System.out.println(this + "сумма чека " + checkSum);
            System.out.println(this + "вышел из магазина");

    }
}
