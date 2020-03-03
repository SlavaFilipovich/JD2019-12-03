package by.it.shulga.jd01.jd02_03;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;

class Buyer extends Thread implements IBuyer, IUseBacket {
    boolean pensioner;
    double speed;
    Map<String, Double> backet = new HashMap<>();
    private static Semaphore semaphore = new Semaphore(20);
    private static Semaphore bucketSemaphore = new Semaphore(50);
    boolean isWaiting = false;

    public Buyer(int number) {
        super("Покупатель N" + number + " ");
        Dispatcher.buyerEnter();
    }

    public Buyer(int number, boolean pensioner) {
        super("Покупатель N" + number + " ");
        this.pensioner = pensioner;
        if (pensioner)
            this.speed = 1.5;
        else
            this.speed = 1.0;
        Dispatcher.buyerEnter();
    }

    @Override
    public void run() {
        enterTheMarket();
        try {
            bucketSemaphore.acquire();
            takeBacket();
            try {
                semaphore.acquire();
                chooseGoods();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
            putGoodsToBacket();

            goToQueue();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bucketSemaphore.release();
        }
        exitTheMarket();
    }

    @Override
    public void enterTheMarket() {
        if (this.pensioner)
            System.out.println(this + "вошел в магазин (Пенсионер)");
        else
            System.out.println(this + "вошел в магазин");
    }

    @Override
    public void chooseGoods() {
        System.out.println(this + "приступил к выбору товара");
        Helper.sleep((int) (Helper.random(500, 2000) * this.speed));
        System.out.println(this + "окончил выбор товара");
    }

    @Override
    public void goToQueue() {
        System.out.println(this + " стал в очередь");
        synchronized (this) {
            isWaiting = true;
            if (this.pensioner) {
                QueuePensioneer.add(this);
            } else {
                QueueBuyer.add(this);
            }
            Helper.printQueue();
            while (this.isWaiting)
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }

    @Override
    public void exitTheMarket() {
        System.out.println(this + "вышел из магазина");
        Dispatcher.buyerLeave();
    }


    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public void takeBacket() {

        System.out.println(this + "взял корзину");
    }

    @Override
    public void putGoodsToBacket() {
        Helper.sleep((int) (Helper.random(500, 2000) * this.speed));
        int numberOfGoods = Helper.random(1, 4);

        Map<String, Double> listOfGoods = Good.getListOfGoods();
        Set<String> keySets = listOfGoods.keySet();

        ArrayList<String> listOfKeys = new ArrayList<>(keySets);

        for (int i = 0; i < numberOfGoods; i++) {
            int indexRandom = Helper.random(0, listOfKeys.size() - 1);
            String chosenGood = listOfKeys.remove(indexRandom);
            Double prise = listOfGoods.get(chosenGood);
            this.backet.put(chosenGood, prise);
            System.out.println(this + "положил в корзину: " + chosenGood + " " + prise);
        }
    }

    public void setWaitingFlag(boolean b) {
        isWaiting = b;
    }
}