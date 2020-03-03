package by.it.ban.jd02_05;


import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        ResMan res = ResMan.INSTANCE;
        Scanner sc = new Scanner(System.in);
        Locale locale = Locale.ENGLISH;
        if (args.length == 2) {
            locale = new Locale(args[0], args[1]);
        }
        res.setLocale(locale);
        while (true) {
            System.out.println(locale.toString());
            DateFormat date = DateFormat.getDateInstance(DateFormat.FULL, locale);
            Date d = new Date();
            String stringDate = date.format(d);
            System.out.println(stringDate);

            System.out.println(res.get(Messages.WELCOME));
            System.out.print(res.get(User.FIRST_NAME)+" ");
            System.out.println(res.get(User.LAST_NAME));
            System.out.println(res.get(Messages.QUESTION));

            String cmd = sc.nextLine();
            if (cmd.equals("en"))
                locale=new Locale("en", "US");
            else if (cmd.equals("ru"))
                locale=new Locale("ru", "RU");
            else if (cmd.equals("be"))
                locale=new Locale("be", "BY");
            else if (cmd.equals("end"))
                break;
            res.setLocale(locale);

        }
    }
}
