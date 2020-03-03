package by.it.ban.calc;

import java.io.IOException;

class Printer {

    void print(Var var) throws CalcException, IOException {
        if (var != null){
            System.out.println(var);
            LogFile.print(var.toString());
        }
        else
            throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.UNKNOWN));
    }


}
