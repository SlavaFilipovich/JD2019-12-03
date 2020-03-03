package by.it.shulga.jd01.jd02_01;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class Helper {

    static final Map<String, Integer> goodsMap = new HashMap<>();
    static void writeGoodsMap(){
        goodsMap.put("water", 1);
        goodsMap.put("bread", 2);
        goodsMap.put("apples", 3);
        goodsMap.put("milk", 4);
        goodsMap.put("eggs", 5);
        goodsMap.put("cake", 6);
        goodsMap.put("meat", 7);
    }

    private static Random random = new Random(System.nanoTime());

    public static int randNum(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
    public static int randNumUntil(int max) {
        return randNum(0, max);
    }
}
