package by.it.samuseva.jd02_03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Market {
    public static void main(String[] args) {
        new Good();

        System.err.println("-----------THE MARKET HAS OPENED-----------");
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= Dispetcher.getCountCashier(); i++) {
         executor.execute(new Cashier(i));
        }
        executor.shutdown();

        int number = 0;
        while (Dispetcher.marketOpened()){
            int count = Helper.random(0,2);
            for (int i = 1; i <= count; i++) {
                if (Dispetcher.marketOpened()){
                    Buyer buyer = new Buyer(++number);
                    if (number%4 == 0) {
                        buyer.pensioner = true;
                    }
                    buyer.start();
                }
            }
            Helper.sleep(1000);
        }

        try {
            while (!executor.awaitTermination(1000, TimeUnit.MILLISECONDS));
                System.err.println("-----------THE MARKET HAS CLOSED-----------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
