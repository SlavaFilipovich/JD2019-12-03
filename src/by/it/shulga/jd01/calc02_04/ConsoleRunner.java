package by.it.shulga.jd01.calc02_04;

import java.util.Scanner;

class ConsoleRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        Printer printer = new Printer();
        Logger logger = new Logger();
        try {
            Var.loadVarFromFile(parser);
            logger.print("recovery of the variable list");
        } catch (CalcException e) {
            System.out.println(e.getMessage());
            logger.print(e.getMessage());
        }
        while (true) {
            String expression = scanner.nextLine();
            if (expression.equals("end")) {
                try {
                    Var.saveVarToFile();
                } catch (CalcException e) {
                    System.out.println(e.getMessage());
                    logger.print(e.getMessage());
                }
                logger.print("save to file");
                System.out.println("I hope to see you again ");
                logger.print(expression);
                break;
            }
            logger.print(expression);
            if (expression.equals("printvar")) {
                String stringVars = Var.getStringVars();
                System.out.println(stringVars);
                logger.print("print var\n" + stringVars);
                try {
                    Var.saveVarToFile();
                } catch (CalcException e) {
                    System.out.println(e.getMessage());
                    logger.print(e.getMessage());
                }
                continue;
            }
            if (expression.equals("sortvar")) {
                Var.sortAndPrintVars();
                logger.print("Data sorted\n" + Var.getStringVars());
                continue;
            }
            try {
                Var result = parser.calc(expression);
                printer.print(result);
                logger.print(result.toString());
            } catch (CalcException e) {
                System.out.println(e.getMessage());
                logger.print(e.getMessage());
            }
        }
    }
}
