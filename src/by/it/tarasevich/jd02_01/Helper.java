package by.it.tarasevich.jd02_01;

import java.util.Random;

public class Helper {
    private static Random generator = new Random(System.nanoTime());


    static void sleep(int timiout) {
        try {
            Thread.sleep(timiout / Dispatcher.K_SPEED);
        } catch (InterruptedException e) {
            System.out.println("WOW");
        }
    }

    static int random(int min, int max) {
        return min + generator.nextInt(max - min + 1);
    }

    static int random(int max) {
        return generator.nextInt(max + 1);
    }


}
