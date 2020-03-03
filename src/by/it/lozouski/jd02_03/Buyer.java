package by.it.lozouski.jd02_03;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Semaphore;

class Buyer extends Thread implements IBuyer, IUseBacket {

    private volatile Map<String, Integer> backetWithBoughtGoods = new HashMap<>();
    private Integer amountOfGoods = 0;

    private static Semaphore semaphore = new Semaphore(20);

    private boolean waitFlag = false;

    public void setWaitFlag(boolean waitFlag) {
        this.waitFlag = waitFlag;
    }


    private boolean pensioner;
    private double speedPerson;


    public Map<String, Integer> getBacketWithBoughtGoods() {
        return backetWithBoughtGoods;
    }

    public Integer getAmountOfGoods() {
        return amountOfGoods;
    }



    public Buyer(int idBuyer, boolean pensioner) {
        super("ID покупателя: " + idBuyer + " ");
        if (pensioner) {
            this.pensioner = true;
            this.speedPerson = 1.5;
        } else {
            this.pensioner = false;
            this.speedPerson = 1.0;
        }
        Dispetcher.buyerEnteredMarket();
    }


    @Override
    public void run() {
        enterToMarket();
        takeBacket();
        try {
            semaphore.acquire();
            chooseGoods();
            putGoodsToBacket();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
        goToQueue();
        goToOut();
    }

    @Override
    public void enterToMarket() {
        if (pensioner) System.out.println(this + "вошел в магазин.");
        else System.out.println(this + "Вошел в магазин.");
    }

    @Override
    public void takeBacket() {
        System.out.println(this + "Взял корзинку для покупок.");
        Help.sleep((int) (Help.randomGenerate(500, 2000) * this.speedPerson));
    }

    @Override
    public void chooseGoods() {
        System.out.println(this + "Начал выбирать товары.");
        Help.sleep((int) (Help.randomGenerate(500, 2000) * this.speedPerson));
    }

    @Override
    public void putGoodsToBacket() {
        int setOfGoods = Help.randomGenerate(1, 4);
        Object[] keysArray = GoodsInThisMarket.productsForTheBuyer.keySet().toArray();

        for (int product = 1; product <= setOfGoods; product++) {
            Help.sleep((int) (Help.randomGenerate(500, 2000) * this.speedPerson));
            int randomIndex = new Random().nextInt(keysArray.length);
            Integer value = GoodsInThisMarket.productsForTheBuyer.get(keysArray[randomIndex].toString());
            backetWithBoughtGoods.put((String) keysArray[randomIndex], value);
//            System.out.println(this + "Выбрал товар: " + keysArray[randomIndex] + " Цена: " + value + "p.");
        }
        amountOfGoods = backetWithBoughtGoods.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println(this + "Закончил выбирать товары.");

    }

    @Override
    public void goToQueue() {
        synchronized (this) {
            System.out.println(this + "отправился в очередь.");
            QueueBuyer.addBuyerToQueue(this);
            this.waitFlag = true;
            while (this.waitFlag)
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void goToOut() {
        System.out.println(this + "Вышел из магазина.");
        Dispetcher.buyerLeftMarket();
    }

    @Override
    public String toString() {
        if (pensioner) return this.getName() + "Пенсионер ";
        else return this.getName();
    }
}
