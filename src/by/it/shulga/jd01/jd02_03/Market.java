package by.it.shulga.jd01.jd02_03;

import java.util.ArrayList;
import java.util.List;

class Market {

    public static void main(String[] args) {
        System.out.println("---------- Магазин открыт ---------");
        List<Thread> threads = new ArrayList<>(1000);
        Administrator administrator = new Administrator();
        Thread thread = new Thread(administrator);
        threads.add(thread);
        thread.start();

        int number = 1;
        for (int j = 0; j < 2; j++) {
            for (int time = 0; time < 30; time++) {
                //int count = Helper.random(0, time+10-getAlive(buyers));
                int alive= Dispatcher.getCountBuyer();
                int count = 2*(time+10)-alive;
                System.out.print("на "+(j*60+time)+" сек. в магазине "+alive+" человек");
                if(count>0) System.out.println(", добавим "+count+" покупателей");
                else System.out.println();
                Buyer buyer;
                for (int i = 1; i <= count; i++) {
                    if (0 == number % 4) {
                        buyer = new Buyer(number++, true);
                    } else {
                        buyer = new Buyer(number++);
                    }
                    threads.add(buyer);
                    buyer.start();
                }
                Helper.sleep(1000);
            }
            for (int time = 30; time <= 60; time++) {
                //int count = Helper.random(0, 2);
                int alive= Dispatcher.getCountBuyer();
                int count = 40+(30-time)-alive;
                System.out.print("на "+(j*60+time)+" сек. в магазине "+alive+" человек");
                if(count>0) System.out.println(", добавим "+count+" покупателей");
                else System.out.println();
                Buyer buyer;
                for (int i = 1; i <= count; i++) {
                    if (0 == number % 4) {
                        buyer = new Buyer(number++, true);
                    } else {
                        buyer = new Buyer(number++);
                    }
                    threads.add(buyer);
                    buyer.start();
                }
                Helper.sleep(1000);
            }
        }
        for (Thread thread1 : threads) {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("---------- Магазин закрыт ---------");
    }

    private static int getAlive(List<Buyer> buyers) {
        int count=0;
        for (Buyer buyer : buyers) {
            if(buyer.isAlive())
                count++;
        }
        return count;
    }

}