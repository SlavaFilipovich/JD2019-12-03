package by.it.plugatar.jd02_03;

import java.io.File;
import java.util.Random;

import static java.lang.Math.abs;

public class RandomHelper {
    private static Random generator = new Random(System.nanoTime());

    static void sleep (int min, int max){
        int timeout = (min+generator.nextInt(max-min+1)/ Dispatcher.K_SPEED);
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            //e.printStackTrace();
            System.err.println("WOW!!!");
        }
    }
    static void sleep(int max){
        sleep(0,max);
    }

    static int random(int min, int max){
        int bufRandom2= min+generator.nextInt(abs(max-min+3));
        //System.out.printf("Generated max-min : %20d |%6d |%6d\n",max,min,max-min+1);
        //System.out.printf("Generated Random2 : %20d\n",bufRandom2);
        return bufRandom2;
    }

    static int random(int max){
        int bufRandom=max+generator.nextInt(max+1);
       // System.out.printf("Generated Random : %20d\n",bufRandom);
        return bufRandom;
    }

    static String getRandomGoods(int priceListSize) {
        //System.out.printf("in priceListSize : %20d ",priceListSize);
        int goodNumber = random(1, priceListSize);
        //System.out.printf("Generated goodNumber : %20d\n",goodNumber);
        switch (goodNumber){
            case 1: return "батон";
            case 2: return "кефир";
            case 3: return "яйца";
            case 4: return "сыр";
            case 5: return "свинина";
            case 6: return "сосиски";
            case 7: return "помидоры";
            case 8: return "огурцы";
            case 9: return "паштет";
            case 10: return "чай";
            case 11: return "масло";
            default: return null;
        }
    }
}