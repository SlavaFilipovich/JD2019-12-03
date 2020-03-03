package by.it.cherkas.jd02_02;


import java.util.*;

public class Market {

    private static int countBuyers = 0;
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

        Map<Good, Integer> goods = new LinkedHashMap<>();
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
        List<Thread> threads = new ArrayList<>(1000);

        int numberOfCashiers = 0;
        Cashier cashier = new Cashier(++numberOfCashiers);
        Thread thread = new Thread(cashier);
        threads.add(thread);
        thread.start();

        while (countBuyers<=100){
            Thread.sleep(6000);
            int count = Helper.random(0, 2);
            for (int i = 0; i <=count ; i++) {
                countBuyers++;
                new Buyer(countBuyers, goods, backet);

                }
            Thread newCashier = null;
            if (countBuyers > 1 && countBuyers % 5 == 1 && numberOfCashiers< 5) {
                newCashier = new Thread(new Cashier(++numberOfCashiers));
                threads.add(newCashier);
                newCashier.start();
            }
            if (countBuyers != 1 && ((numberOfCashiers * 5) - countBuyers == 1)) {
                Thread removedCashier = threads.remove(threads.size()-1);
                --numberOfCashiers;
            if (!removedCashier.isInterrupted()){
                removedCashier.interrupt();
            }
            }
        }
        System.out.println("---------- Market closed");
    }

}
