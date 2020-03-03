package by.it.tarasevich.jd02_01;

import java.util.ArrayList;
import java.util.List;

public class Market {
    public static void main(String[] args) {
        System.out.println("---------- Market opened");
        List<Buyer> buyers = new ArrayList<>(1000);
        int number = 0;
        for (int time = 0; time <= 120; time++) {
            int count = Helper.random(0, 2);
            for (int i = 1; i <= count; i++) {
                Buyer buyer = new Buyer(++number);
                buyers.add(buyer);
                buyer.start();
            }
            Helper.sleep(1000);
        }

        for (Buyer buyer : buyers) {
            try {
                buyer.join();
            } catch (InterruptedException e) {
                System.out.println("WOW2");
            }
        }
        System.out.println("--------- Market closed");
    }
}
