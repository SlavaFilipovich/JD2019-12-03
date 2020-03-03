package by.it.lozouski.jd02_03;

import java.util.HashMap;
import java.util.Map;

class GoodsInThisMarket {
    static final Map<String, Integer> productsForTheBuyer = new HashMap<>();

    static void productListInThisMarket() {
        productsForTheBuyer.put("Хлеб Бородинский.", 8);
        productsForTheBuyer.put("Мясо Свинина. 1кг.", 25);
        productsForTheBuyer.put("Молоко Здраувшка. 1л.", 9);
        productsForTheBuyer.put("Шоколад Коммунарка.", 14);
        productsForTheBuyer.put("Пиво Жигулевское 1.5л.", 19);
        productsForTheBuyer.put("Сахар 1кг.", 15);
        productsForTheBuyer.put("Квас 1л", 13);
        productsForTheBuyer.put("Помидоры 1кг.", 10);
        productsForTheBuyer.put("Масло Сливочное 100гр.", 17);
        productsForTheBuyer.put("Мясо Свинина. 2кг.", 50);
        productsForTheBuyer.put("Водка Бульбашь 0.5л", 30);
        productsForTheBuyer.put("Сало Крестьянское 0.5кг.", 26);
        productsForTheBuyer.put("Макароны Макфа 500гр.", 20);
        productsForTheBuyer.put("Рис Краснодарский 500гр.", 22);
        productsForTheBuyer.put("Кетчуп Чумак.", 12);
        productsForTheBuyer.put("Бананы 1 кг.", 16);
    }
}
