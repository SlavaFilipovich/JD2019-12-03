package by.it.kuzmichalex.jd02_05;


import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Locale locale = new Locale(System.getProperty("user.language"), System.getProperty("user.country"));
        ResMan res = ResMan.INSTANCE;
        res.setLocale(locale);

        if (args.length == 2) {
            locale = new Locale(args[0], args[1]);
            res.setLocale(locale);
        }
        do {
            System.out.println(res.get(Messages.FIRSTNAME) + " " + res.get(Messages.LASTNAME));
            System.out.println(res.get(Messages.WELCOME));
            System.out.println(res.get(Messages.QUESTION));
            System.out.print(res.get(Messages.CURRENT_DATE) + " ");
            Calendar calendar = Calendar.getInstance(locale);
            DateFormat dt = DateFormat.getDateInstance(DateFormat.LONG, locale);
            String longLocaleDate = dt.format(calendar.getTime());
            System.out.println(longLocaleDate);
            System.out.println(res.get(Messages.ENTER_ACTION));

            String expression = scanner.next();
            if (expression.toLowerCase().equals("end")) break;
            else if (expression.toLowerCase().equals("en")) {
                locale = new Locale("en", "US");
                res.setLocale(locale);
                //res.setLocale(Locale.ENGLISH);
            } else if (expression.toLowerCase().equals("by") || expression.toLowerCase().equals("be")) {
                locale = new Locale("be", "BY");
                res.setLocale(locale);
            } else if (expression.toLowerCase().equals("ru")) {
                locale = new Locale("ru", "RU");
                res.setLocale(locale);
            }
        } while (true);
    }

}
