package by.it.tarasevich.jd02_02;

import java.util.ArrayList;
import java.util.List;

public class Market {
    public static void main(String[] args) {
        System.out.println("---------- Market opened");
        List<Thread> threads = new ArrayList<>(1000);

        for (int i = 1; i <= 2; i++) {
            Cashier cashier = new Cashier(i);
            Thread thread = new Thread(cashier);
            threads.add(thread);
            thread.start();
        }

        int number = 0;
        while (Dispatcher.marketOpened()) {
            int count = Helper.random(0, 2);
            for (int i = 1; i <= count; i++) {
                if (Dispatcher.marketOpened()) {
                    Buyer buyer = new Buyer(++number);
                    threads.add(buyer);
                    buyer.start();
                }
            }
            Helper.sleep(1000);
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("WOW2");
            }
        }
        System.out.println("--------- Market closed");
    }
}
