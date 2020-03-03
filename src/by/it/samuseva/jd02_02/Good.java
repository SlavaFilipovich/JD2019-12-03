package by.it.samuseva.jd02_02;

import java.util.*;

class Good {
    private static Map<String, Integer> goods = new HashMap<>();
    private static Set<Map.Entry<String, Integer>> entries = goods.entrySet();
    private static List<String> list = new ArrayList<>(entries.size());

    Good() {
        goods.put("Milk", 10);
        goods.put("Bread", 25);
        goods.put("Meat", 100);
        goods.put("Chicken", 55);
        goods.put("Banana", 7);
        goods.put("Orange", 4);
        goods.put("Apple", 8);
        goods.put("Lemon", 9);
        goods.put("Water", 1);
        goods.put("Ice-cream", 6);
        goods.put("Juicy", 5);
        goods.put("Cake", 2);
        goods.put("Yogurt", 3);
        goods.put("Jam", 15);
        for (Map.Entry<String, Integer> entry : entries) {
            list.add(entry.getKey()+ " " + entry.getValue());
        }
    }
    static int goodsLength(){
        return goods.size();
    }

    static String getGoods(int number) {
        return  list.get(number);
    }
    static void saveBecket(String good){

        Set<String> keys = goods.keySet();
        String[] mas = good.split(" ");
        if (keys.equals(mas[0])) {


        }
    }
}
