package by.it.filipovich.calc;


import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ConsoleRunner {
    public static void main(String[] args) {
        Logger.getInstance().logWrite(Messages.CALC_STARTED);
        Logger.getInstance().logWrite(Messages.CREATE_OBJECTS);
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        Printer pr = new Printer();
        Logger.getInstance().logWrite(Messages.LOAD_FROM_FILES);
        VarToFile.load();
        while (true){
            Logger.getInstance().logWrite(Messages.ENTER_EXPRESSION);
            String expression = scanner.nextLine();
            if(expression.equals("end")) {
                Logger.getInstance().logWrite(Messages.ENTER_END);
                Logger.getInstance().logWrite(Messages.CALC_FINISHED);
                break;
            }
            if(expression.equals("printvar")){
                Logger.getInstance().logWrite(Messages.ENTER_PRINT);
                for (Map.Entry<String, Var> pair : Var.vars.entrySet()) {
                    System.out.println(pair);
                }
                Logger.getInstance().logWrite(Messages.CALC_FINISHED);
                break;
            }
            if(expression.equals("sortvar")){
                Logger.getInstance().logWrite(Messages.ENTER_SORT);
                for (Map.Entry<String, Var> varEntry : new TreeMap<>(Var.vars).entrySet()) {
                    System.out.println(varEntry);
                }
                Logger.getInstance().logWrite(Messages.CALC_FINISHED);
                break;
            }
            try {
                Var result = parser.calc(expression);
                pr.print(result);
            } catch (CalcException e) {
                System.out.println(e.getMessage());
                Logger.getInstance().logWrite(e.getMessage());
            }
        }
    }
}
