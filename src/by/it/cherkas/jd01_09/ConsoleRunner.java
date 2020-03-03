package by.it.cherkas.jd01_09;

import java.util.Scanner;

public class ConsoleRunner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        Printer printer = new Printer();
        while (true) {
            String expression = sc.next();
            if (expression.equals("end"))
                break;
            printer.print(parser.calc(expression));
        }
    }
}
