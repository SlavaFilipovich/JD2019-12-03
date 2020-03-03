package by.it.popkov.calc;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;

class ConsoleRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Printer printer = new Printer();
        Parser parser = new Parser();
        LangSwitcher langSwitcher = LangSwitcher.LANG_SWITCHER;
        try {
            if (Files.exists(Paths.get(CalcFile.getFullFileName())))
                CalcFile.readValue(parser);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {

            String expression = scanner.nextLine();
            if (expression.equals("end")) break;
            else if (expression.equals("printvar")) Var.printvar();
            else if (expression.equals("sortvar")) Var.sortvar();
            else if (expression.equals("en")) langSwitcher.setResourceBundle(new Locale("en"));
            else if (expression.equals("be")) langSwitcher.setResourceBundle(new Locale("be"));
            else if (expression.equals("ru")) langSwitcher.setResourceBundle(new Locale("ru"));
            else try {
                    printer.print(parser.calc(expression));
                } catch (CalcException e) {
                    String message = langSwitcher.getResourceBundle().getString(e.getMessage());
                    Log.writeLog(message);
                    System.out.println(message);
                }
        }
    }

}
