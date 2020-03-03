package by.it.shulga.jd01.jd02_03;

import java.util.HashMap;
import java.util.Map;

class Good {

    private static Map<String, Double> goods = new HashMap<>() {
        {
            put("Хлеб",2.0);
            put("Молоко",1.0);
            put("Масло",5.0);
            put("Сметана",3.0);
            put("Томат", 2.0);
            put("Яйца", 3.2);
            put("Гречка", 1.2);
            put("Рис", 1.7);
            put("Арахис", 4.3);
            put("Вода", 0.5);
        }
    };
    static Map<String, Double> getListOfGoods() {
        return new HashMap<>(goods);
    }
}
