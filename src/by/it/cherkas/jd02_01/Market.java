package by.it.cherkas.jd02_01;


import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;

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
        Queue<Buyer> queue = new ArrayDeque<>();
        while (countBuyers<=120){
            Thread.sleep(1000);
            int count = Helper.random(0, 2);
            for (int i = 0; i <=count ; i++) {
                countBuyers++;
                if (countBuyers<120){
                    Buyer buyer = new Buyer(countBuyers, goods, backet);
                    queue.add(buyer);
                }
            }
            for (Buyer buyer : queue) {
                buyer.join();
            }
        }
        System.out.println("---------- Market closed");
    }

}
