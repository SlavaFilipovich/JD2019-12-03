package by.it.busel.jd02_05;

import java.util.Locale;

class InternationalizationTaskA {
    public static void main(String[] args) {
        ResourcesManager rManager = ResourcesManager.INITIALIZE;
        verifyIfToChangeLocale(args, rManager);
        greetUser(rManager);
        askWaitWish(rManager);
    }

    private static void verifyIfToChangeLocale(String[] args, ResourcesManager rManager) {
        if (args.length == 2) {
            rManager.setLocale(new Locale(args[0], args[1]));
        }
    }

    private static void greetUser(ResourcesManager rManager) {
        System.out.printf("%s, %s %s %s!\n",
                rManager.get(Message.GREETING), rManager.get(User.FIRST_NAME),
                rManager.get(User.PATRONYMIC), rManager.get(User.LAST_NAME));
    }

    private static void askWaitWish(ResourcesManager rManager) {
        System.out.printf("%s\n", rManager.get(Message.GREETING_QUESTION));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s\n", rManager.get(Message.WISHES));
    }
}
