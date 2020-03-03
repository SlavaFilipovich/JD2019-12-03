package by.it.borodachev.Calc;

public class Patterns {

    static final String OPERATION="(?<=[^-+*/={,])[-+*/=]";
    static final String SCALAR="-?[0-9]+(\\.[0-9]+)?";
    static final String VECTOR="\\{"+SCALAR+"(,"+SCALAR+")*}";
    static final String MATRIX="\\{"+VECTOR+"(,"+VECTOR+")*}";
    static final String BRACKETS = "\\([-0-9+*/a-zA-Z.{},_ ]+\\)";

    private Patterns() {
    }
}
