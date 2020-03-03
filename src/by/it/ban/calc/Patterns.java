package by.it.ban.calc;

import java.util.regex.Pattern;

public class Patterns {

    static final String BRACKETS = "\\([-0-9+*/a-zA-Z.{},_ ]+\\)";
    static final String OPERATION="(?<=[^-+*/={,])[-+*/=]";
    static final String SCALAR="-?[0-9]+(\\.[0-9]+)?";
    static final String VECTOR="\\{"+SCALAR+"(,"+SCALAR+")*}";
    static final String MATRIX="\\{"+VECTOR+"(,"+VECTOR+")*}";

    private Patterns() {
    }
}
