package by.it.cherkas.jd02_03;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Market {

    public static void main(String[] args) throws InterruptedException {
        Good tPaper = new Good(" туалетная бумага");
        Good cucumber = new Good(" овощи");
        Good bread = new Good(" хлеб");
        Good fruits = new Good(" фрукты");
        Good salt = new Good(" соль");
        Good sugar = new Good(" сахар");
        Good potato = new Good(" картофель");
        Good tomato = new Good(" томат");
        Good cheese = new Good(" сыр");
        Good chicken = new Good(" курица");

        ConcurrentHashMap<Good, Integer> goods = new ConcurrentHashMap<>(15);
        goods.put(tPaper, 5);
        goods.put(cucumber, 2);
        goods.put(bread, 1);
        goods.put(fruits, 15);
        goods.put(salt, 1);
        goods.put(sugar, 2);
        goods.put(potato, 3);
        goods.put(tomato, 2);
        goods.put(cheese, 4);
        goods.put(chicken, 25);
        Backet backet = new Backet();

        System.out.println("------------- Market opened");
        CopyOnWriteArrayList<Thread> threads = new CopyOnWriteArrayList<Thread>();

        int number = 0;

        if (Dispatcher.marketOpened()) {
            Buyer buyer = new Buyer(++number, goods, backet);
            buyer.start();
        }

        int numberOfCashiers = 0;
        Cashier cashier = new Cashier(++numberOfCashiers);
        Thread thread = new Thread(cashier);
        threads.add(thread);
        thread.start();

        while (Dispatcher.marketOpened()) {
            Helper.sleep(10000000);
            int count = Helper.random(0, 2);
            for (int i = 0; i <= count; i++) {
                if (Dispatcher.marketOpened()) {
                    Buyer buyer = new Buyer(++number, goods, backet);
                    buyer.start();
                }

            }
            Helper.sleep(1000);
            Thread newCashier = null;
            if (number > 1 && number % 5 == 1 && numberOfCashiers < 5) {
                newCashier = new Thread(new Cashier(++numberOfCashiers));
                threads.add(newCashier);
                newCashier.start();
            }
            if (number != 1 && numberOfCashiers != 1 && ((numberOfCashiers * 5) - number == 1)) {
                Thread removedCashier = threads.remove(threads.size() - 1);
                if (!removedCashier.isInterrupted() && removedCashier.isAlive()) {
                    System.out.println("Касса № " + numberOfCashiers + " закрывается");
                    removedCashier.interrupt();
                }
                --numberOfCashiers;
            }
        }
        while (true) {
            if (Dispatcher.marketClosed()) {
                Helper.sleep(100000);
                System.out.println("---------- Market closed");
                break;
            }

        }
    }
}
