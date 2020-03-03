package by.it.shulga.jd01.jd02_02;


import java.util.Random;

class Helper {

    static void delay(int delay) {
        try {
            Thread.sleep(delay / Dispatcher.SPEED_BOOST);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Random random = new Random(System.nanoTime());

    public static int randNum(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public static int randNumUntil(int max) {
        return randNum(0, max);
    }
}
