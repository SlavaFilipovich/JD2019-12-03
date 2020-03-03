package by.it.lozouski.calc;

class Patterns {
    static final String SCALAR = "-?[0-9]+\\.?[0-9]*";
    static final String VECTOR = "\\{" + SCALAR + "(, ?" + SCALAR + ")*}";
    static final String MATRIX = "\\{" + VECTOR + "(, ?" + VECTOR + ")*}";
    static final String OPERATION = "(?<=[^-+*/={,])[-+*/=]";
    static final String BRACKETS = "\\([-0-9+*/a-zA-Z.{},_ ]+\\)";
    static final String VARNAME="^[a-zA-Z]{1,}[a-zA-Z0-9]{0,}";

    private Patterns(){

    }
}
