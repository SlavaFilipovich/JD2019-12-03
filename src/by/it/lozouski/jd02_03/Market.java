package by.it.lozouski.jd02_03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Market {
    public static void main(String[] args) {
        System.out.println("------------------МАГАЗИН ОТКРЫЛСЯ.-------------------");
        GoodsInThisMarket.productListInThisMarket(); //заполнил магазин товарами

        ExecutorService fixThreadPool = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 5; i++) {
            Cashier cashier = new Cashier(i);
            fixThreadPool.execute(cashier);
        }
        fixThreadPool.shutdown();

        ControllerCashiers controllerCashiers = new ControllerCashiers();
        controllerCashiers.start();

        int buyerId = 1;
        while (Dispetcher.marketOpened()){
            int count = Help.randomGenerate(0,2);
            for (int i = 1; i <= count; i++) {
                if (Dispetcher.marketOpened()){
                    Buyer currentBuyer;
                    if (buyerId % 4 == 0) currentBuyer = new Buyer(buyerId++,true);
                    else currentBuyer = new Buyer(buyerId++,false);
                    currentBuyer.start();
                }
            }
            Help.sleep(1000);
        }

        try {
           while (!fixThreadPool.awaitTermination(1000, TimeUnit.MILLISECONDS));
            System.out.println("------------------МАГАЗИН ЗАКРЫЛСЯ.-------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        for (Thread thisThread : threads) {
//            try {
//                thisThread.join();
//            } catch (InterruptedException e) {
//                System.out.println("TEST ERROR2!");
//            }
//        }

    }
}
