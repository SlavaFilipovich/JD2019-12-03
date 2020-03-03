package by.it.lozouski.jd02_03;

import java.util.Random;

class Help {
    private static Random randomGenerator = new Random(System.nanoTime());

    static void sleep(int timeOut) {
        try {
            Thread.sleep(timeOut / Dispetcher.SPEED_K);
        } catch (InterruptedException e) {
            System.out.println("TEST ERROR!");
        }
    }

    static int randomGenerate(int minTime, int maxTime) {
        return minTime + randomGenerator.nextInt(maxTime - minTime + 1);
    }

    static int randomGenerate(int maxTime) {
        return randomGenerator.nextInt(maxTime + 1);
    }


}
