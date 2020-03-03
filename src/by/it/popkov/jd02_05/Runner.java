package by.it.popkov.jd02_05;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

class Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LangSwitcher langSwitch = LangSwitcher.LANG_SWITCHER;
        if (args.length == 2) {
            Locale locale = new Locale(args[0], args[1]);
            langSwitch.setResourceBundle(locale);
        }
        print(langSwitch);
        while (true) {

            switch (scanner.next()) {
                case "ru":
                    langSwitch.setResourceBundle(new Locale("ru", "RU"));
                    break;
                case "be":
                    langSwitch.setResourceBundle(new Locale("be", "BY"));
                    break;
                case "en":
                    langSwitch.setResourceBundle(new Locale("en", "US"));
                    break;
            }
            print(langSwitch);

        }


    }

    static void print(LangSwitcher langSwitch) {
        System.out.println(langSwitch.getResourceBundle().getString(Message.WELCOME));
        System.out.println(langSwitch.getResourceBundle().getString(User.FIRST_NAME));
        System.out.println(langSwitch.getResourceBundle().getString(User.SECOND_NAME));
        DateFormat date = DateFormat.getDateInstance(DateFormat.FULL, langSwitch.getResourceBundle().getLocale());
        System.out.println(date.format(new Date()));
    }
}
