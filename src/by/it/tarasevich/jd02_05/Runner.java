package by.it.tarasevich.jd02_05;

import java.util.Locale;
import java.util.ResourceBundle;

public class Runner {
    public static void main(String[] args) {
        Locale rus = new Locale("ru", "RU");
        Locale bel = new Locale("be", "BY");
        Locale eng = new Locale("en", "US");

        String p = System.getProperty("user.dir") + "/src/by/it/tarasevich/jd02_05/text";


        ResourceBundle s1 = ResourceBundle.getBundle(System.getProperty("user.dir") + "/src/by/it/tarasevich/jd02_05/text", rus);
        ResourceBundle s2 = ResourceBundle.getBundle("by.it.tarasevich.jd02_05.text", bel);
        ResourceBundle s3 = ResourceBundle.getBundle("by.it.tarasevich.jd02_05.text", eng);

        String str1 = s1.getString("user.name, " + "message.welcome");
        String str2 = s2.getString("user.name, " + "message.welcome");
        String str3 = s3.getString("user.name, " + "message.welcome");

        System.out.println(str1);

    }

}
