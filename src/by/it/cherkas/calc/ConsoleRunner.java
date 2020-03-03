package by.it.cherkas.calc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ConsoleRunner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        Printer printer = new Printer();
        try {
            if (Files.exists(Paths.get(CalcFile.getFullFileName())))
                CalcFile.readValue(parser);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            String expression = sc.nextLine();
            if (expression.equals("end"))
                break;
            else if (expression.equals("printvar"))
                Var.printVar();
            else if (expression.equals("sortvar"))
                Var.sortVar();
            else try {
                printer.print(parser.calc(expression));
                }
            catch (CalcException e) {
                Log.writeLog(e.getMessage());
                System.out.println(e.getMessage());
            }
        }
    }
}
