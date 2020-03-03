package by.it.busel.calc02_05;

import java.util.Scanner;

class ConsoleRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        Printer printer = new Printer();
        Storage storage = new Storage();
        Logger logger = new Logger();
        ResourcesManager rManager = new ResourcesManager();
        while (true) {
            String varExpression = scanner.nextLine();
            if (!varExpression.equals("end") && !varExpression.equals("конец")) {
                if (ConsoleCommands.call(varExpression)) continue;
                try {
                    Var result = parser.calc(varExpression.replace("\\s+", ""));
                    printer.printAndSave(varExpression, result.toString());
                } catch (CalcException e) {
                    printer.printAndSave(varExpression, e.getMessage());
                }
            } else {
                break;
            }

        }
//        storage.clearStorage();
    }
}
