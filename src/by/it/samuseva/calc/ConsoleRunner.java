package by.it.samuseva.calc;

import java.util.Scanner;

public class ConsoleRunner {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Parset parset = new Parset();
        Printer printer = new Printer();
        VarFile.load();
        while (true){
            String line = scan.next();
            if (line.equals("end")|| line.equals("END") ){
                break;
            }
            Var rezult = null;
            try {
                rezult = parset.calc(line);
                printer.print(rezult);
            } catch (CalcException e) {
                System.out.println(e.getMessage());
            }
        }
        Var.sortvar();
    }
}
