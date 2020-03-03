package by.it.lozouski.calc;
import static by.it.lozouski.calc.ConsoleRunner.langService;


class CalcException extends Exception {
    private static final long serialVersionUID = -2730840095611934086L;

    public CalcException() {
    }

    public CalcException(String message) {
        super(String.format("%s ",langService.get(Error.ERR)) + message);
    }

    public CalcException(String message, Throwable cause) {
        super(String.format("%s ",langService.get(Error.ERR)) + message, cause);
    }

    public CalcException(Throwable cause) {
        super(cause);
    }
}
