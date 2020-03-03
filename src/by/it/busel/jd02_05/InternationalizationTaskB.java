package by.it.busel.jd02_05;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class InternationalizationTaskB {

    private static final Locale localeBLR = new Locale("ru", "RU");
    private static final Locale localeGB = new Locale("en", "GB");
    private static final Locale localeRU = new Locale("ru", "RU");

    private static void verifyIfToChangeLocale(String[] args, ResourcesManager rManager) {
        Locale locale;
        if (args.length == 2) {
            locale = new Locale(args[0], args[1]);
            rManager.setLocale(locale);
        }
    }

    private static void taskBLogic(ResourcesManager rManager) {
        greetUser(rManager);
        askAndWish(rManager);
    }

    private static void greetUser(ResourcesManager rManager) {
        System.out.printf("%s, %s %s %s!\n",
                rManager.get(Message.GREETING), rManager.get(User.FIRST_NAME),
                rManager.get(User.PATRONYMIC), rManager.get(User.LAST_NAME));
        DateFormat dateInstance = DateFormat.getDateInstance(DateFormat.FULL, rManager.getLocale());
        String today = dateInstance.format(new Date());
        System.out.printf("%s: %s\n", rManager.get(Message.TODAY), today);
    }

    private static void askAndWish(ResourcesManager rManager) {
        System.out.printf("%s\n%s\n", rManager.get(Message.GREETING_QUESTION), rManager.get(Message.WISHES));
    }

    private static void printIntroductoryMessage(ResourcesManager rManager) {
        System.out.printf("%s", rManager.get(Message.INTRODUCTORY_MESSAGE));
    }

    private static void localeSwitcher(ResourcesManager rManager, String localeCode) {
        switch (localeCode) {
            case "be":
                rManager.setLocale(localeBLR);
                break;
            case "en":
                rManager.setLocale(localeGB);
                break;
            case "ru":
                rManager.setLocale(localeRU);
                break;
            default:
                System.out.printf("%s \"%s\"\n", rManager.get(Message.WRONG_LOCALE_INPUT), localeCode);
                return;
        }
        taskBLogic(rManager);
    }

    public static void main(String[] args) {
        ResourcesManager rManager = ResourcesManager.INITIALIZE;
        verifyIfToChangeLocale(args, rManager);
        taskBLogic(rManager);
        printIntroductoryMessage(rManager);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line;
            if (!(line = scanner.nextLine()).equals("end")) localeSwitcher(rManager, line);
            else
                break;
        }
    }
}
