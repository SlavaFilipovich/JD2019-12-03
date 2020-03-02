package by.it.filipovich.jd02_05;

import java.awt.im.InputContext;
import java.util.Locale;

public class Runner {
    public static void main(String[] args) {

        Manager manager = Manager.INSTANCE;

        InputContext context = InputContext.getInstance();
        Locale locale = context.getLocale();
        manager.setLocale(locale);

        System.out.print(manager.get(Messages.WELCOME));
        System.out.print(" "+manager.get(User.FIRST_NAME));
        System.out.println(" "+manager.get(User.LAST_NAME));
        System.out.println(manager.get(Messages.DATE_NOW)+" "+manager.getCurrentTime(locale));
    }
}
