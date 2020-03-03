package by.it.samuseva.jd02_02;

import java.util.Random;

class Helper {
    private static Random generator = new Random(System.nanoTime());

    static void sleep (int timeout){
        try {
            Thread.sleep(timeout/Dispetcher.K_SPEED);
        } catch (InterruptedException e) {
            System.err.println("YPS !!!");
        }

    }

    static int random(int min, int max){
        return min+generator.nextInt(max-min+1);
    }
}
