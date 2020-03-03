package by.it.busel.calc02_05;


public interface Message {
    String CALC_EXCEPTION_MESSAGE_PART = "CalcException.constructor";

    String ERROR_ONLY_MATH_OPERATOR = "Parser.m.ifOneOrMoreOperandsExistOtherwiseThrowCalcException";
    String ERROR_NO_MATH_OPERATOR_FOUND = "Parser.m.calculateVar";
    String ERROR_WRONG_OR_UNSAVED_EXP = "Var.m.createVar";
    String ERROR_DIVISION_BY_ZERO = "Scalar.m.div_Scalar_Vector_Matrix";
    String ERROR_ADDITION = "Var.add_all";
    String ERROR_SUBTRACTION = "Var.sub_all";
    String ERROR_MULTIPLICATION = "Var.mul_all";
    String ERROR_DIVISION = "Var.div_all";

    String PRINTER_INPUT = "Printer.m.formLog_input";
    String PRINTER_OUTPUT = "Printer.m.formLog_output";

    String CURRENT_LANGUAGE = "ResourceManager.m.informAboutCurrentLocale";
}
