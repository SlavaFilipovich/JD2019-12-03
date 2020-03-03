package by.it.busel.calc02_05;

/**
 * a class which contains pattern in order to find the most common expression in a Calc-prototype
 */
class Patterns {
    /**
     * A pattern (String-object) containing basic arithmetical operators corresponding to subtraction, addition,
     * multiplication, division
     */
    static final String OPERATION = "(?<![ -+=*/{,(])[-+*/=]";
    /**
     * A pattern (String-object) containing a general definition of a Scalar expression
     */
    static final String SCALAR = "(-?[0-9]+)([\\.][0-9Ee]+)?";
    /**
     * A pattern (String-object) containing a general definition of a Vector expression
     */
    static final String VECTOR = "\\{" + SCALAR + "(, *" + SCALAR + ")*\\}";
    /**
     * A pattern (String-object) containing a general definition of a Matrix expression
     */
    static final String MATRIX = "\\{" + VECTOR + "(, *" + VECTOR + ")*\\}";
}
