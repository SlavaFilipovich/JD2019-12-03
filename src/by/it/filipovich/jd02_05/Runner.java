package by.it.filipovich.jd02_05;

import java.util.Locale;

public class Runner {
    public static void main(String[] args) {

        Manager manager = Manager.INSTANCE;
        if (args.length==2){
            Locale locale=new Locale(args[0],args[1]);
            manager.setLocale(locale);
        }

        manager.setLocale(new Locale("be","BY"));

        System.out.print(manager.get(Messages.WELCOME));
        System.out.print(" "+manager.get(User.FIRST_NAME));
        System.out.println(" "+manager.get(User.LAST_NAME));
    }
}
