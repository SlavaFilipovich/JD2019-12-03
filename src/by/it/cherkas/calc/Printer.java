package by.it.cherkas.calc;

public class Printer {
   public void print(Var var) throws CalcException {
       if (var != null) {
           Log.writeLog(var.toString());
           System.out.println(var.toString());
       } else {
           throw new CalcException("Результат null");
       }
   }

    }

