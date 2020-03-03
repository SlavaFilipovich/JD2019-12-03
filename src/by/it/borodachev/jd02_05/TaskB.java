package by.it.borodachev.jd02_05;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public class TaskB {
    public static void main(String[] args) {
        Locale locale;
        ResMan res=ResMan.INSTANCE;
        if (args.length==2){
            locale=new Locale(args[0],args[1]);
        }
        else
        {
            locale = new Locale(System.getProperty("user.language"), System.getProperty("user.country"));
        }
        res.setLocale(locale);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(res.get(Messages.WELCOME));
            System.out.println(res.get(User.FIRST_NAME));
            System.out.println(res.get(User.LAST_NAME));
            Calendar calendar = Calendar.getInstance(locale);
            DateFormat dt = DateFormat.getDateInstance(DateFormat.LONG, locale);
            String longLocaleDate = dt.format(calendar.getTime());
            System.out.println(res.get(Messages.CURRENT_DATE)+" "+longLocaleDate);
            System.out.println(res.get(Messages.CHOOSE_ACTION));
            String expression = scanner.next();
            if (expression.toLowerCase().equals("end")) break;
            else if (expression.toLowerCase().equals("en")) {
                    locale = new Locale("en", "US");
                    res.setLocale(locale);
                }
                 else if (expression.toLowerCase().equals("by") || expression.toLowerCase().equals("be")) {
                    locale = new Locale("be", "BY");
                    res.setLocale(locale);
                }
                 else if (expression.toLowerCase().equals("ru")) {
                    locale = new Locale("ru", "RU");
                    res.setLocale(locale);
                }
        }
    }
}
