package by.it.tarasevich.jd02_03;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Market {
    public static void main(String[] args) {
        System.out.println("---------- Market opened");

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 2; i++) {
            Cashier cashier = new Cashier(i);
            fixedThreadPool.execute(cashier);
        }
        fixedThreadPool.shutdown();


        int number = 0;
        while (Dispatcher.marketOpened()) {
            int count = Helper.random(0, 2);
            for (int i = 1; i <= count; i++) {
                if (Dispatcher.marketOpened()) {
                    Buyer buyer = new Buyer(++number);
                    buyer.start();
                }
            }
            Helper.sleep(1000);
        }
        try {
            while (!fixedThreadPool.awaitTermination(1000, TimeUnit.MILLISECONDS)) ;
            System.out.println("--------- Market closed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
