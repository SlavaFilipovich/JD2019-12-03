package by.it.plugatar.jd02_03;

import java.util.HashMap;
import java.util.Map;

public class Goods {
    static Map<String, Double> priceList = new HashMap<>();

    Goods(){
        priceList.put("батон", 1.0);
        priceList.put("кефир", 1.5);
        priceList.put("яйца", 2.1);
        priceList.put("сыр", 9.0);
        priceList.put("свинина", 8.1);
        priceList.put("сосиски", 7.1);
        priceList.put("помидоры", 4.5);
        priceList.put("огурцы", 7.5);
        priceList.put("паштет", 3.2);
        priceList.put("чай", 3.1);
        priceList.put("масло", 2.2);
    }
    //размера списка товаров
    int getPriceListSize() {
        return priceList.size();
    }

    //цены товара из списка
    double getPrice(String goods) {
        return priceList.getOrDefault(goods, (double) 0);
    }
}
