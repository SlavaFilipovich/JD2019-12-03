package by.it.lozouski.calc;
import static by.it.lozouski.calc.ConsoleRunner.langService;

class Printer {
    void print(Var var) throws CalcException {
        if (var != null) {
            System.out.println(var);
        } else throw new CalcException(langService.get(Error.ERR_VAR_IS_NULL));
    }
}
