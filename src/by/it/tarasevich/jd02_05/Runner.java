package by.it.tarasevich.jd02_05;

import java.util.Locale;
import java.util.ResourceBundle;

public class Runner {
    public static void main(String[] args) {
        Locale rus = new Locale("ru", "RU");
        Locale bel = new Locale("be", "BY");
        Locale eng = new Locale("en", "US");

        //String p = "by.it.tarasevich.jd02_05.text.properties";
        //тут ошибка, явно лишнее слово properties т.к. нужен reference к ресурсам
        //а вот, то что ресурсы через точку, а не через слеш это как раз правильно
        String p = "by.it.tarasevich.jd02_05.text";
        //String p = System.getProperty("user.dir") + "/src/by/it/tarasevich/jd02_05/text";

        ResourceBundle s1 = ResourceBundle.getBundle(p, rus);
        ResourceBundle s2 = ResourceBundle.getBundle(p, bel);
        ResourceBundle s3 = ResourceBundle.getBundle(p, eng);

        //каждый ключ читается отдельно, их склеивать нельзя,
        //сначала нужно извечь, а потом использовать
        //например, так вот будет работать
        String str1 = s1.getString("user.name") + s1.getString("message.welcome");
        //или так даже лучше, можно вставить пробел
        String str2 = String.format("%s %s", s2.getString("user.name"), s2.getString("message.welcome"));
        //а так не будет
        //String str3 = s3.getString("user.name, " + "message.welcome");

        System.out.println(str1);
        System.out.println(str2);
    }
}
