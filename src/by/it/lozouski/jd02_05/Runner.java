package by.it.lozouski.jd02_05;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

class Runner {
    public static void main(String[] args) {
        Scanner sc1 = new Scanner(System.in);
        while (true) {
            String inputText = selectLanguage(sc1);
            if (inputText.equals("end")) break;
            }
        }

    static String selectLanguage(Scanner sc1) {
        ChangeLanguageService changeLanguageService = ChangeLanguageService.START;
        String inputText = sc1.nextLine();
        if (inputText.equals("ru")){
            changeLanguageService.setLocale(new Locale("ru", "RU"));
            printTask(changeLanguageService);
        }
        if (inputText.equals("be")){
            changeLanguageService.setLocale(new Locale("be", "BY"));
            printTask(changeLanguageService);
        }
        if (inputText.equals("en")) {
            changeLanguageService.setLocale(new Locale("en", "US"));
            printTask(changeLanguageService);
        }
        return inputText;
    }

    static void printTask(ChangeLanguageService changeLanguageService) {
        System.out.println(changeLanguageService.get(Messages.WELCOME));
        System.out.println(changeLanguageService.get(User.FIRSTNAME));
        System.out.println(changeLanguageService.get(User.LASTNAME));
        System.out.println(changeLanguageService.get(Messages.QUESTION));

        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, changeLanguageService.getResourceBundle().getLocale());
        System.out.println(changeLanguageService.get(Messages.TODAY) + " " + df.format(new Date()));
    }
}
