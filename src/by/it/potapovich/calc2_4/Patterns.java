package by.it.potapovich.calc2_4;

public class Patterns {

    static final String OPERATION="(?<=[^-+*/={,])[-+*/=]";
    static final String SCALAR="-?[0-9]+(\\.[0-9]+)?";
    static final String VECTOR = "\\{((-?)([0-9\\.[ ?]])+,?)+}";
   // static final String VECTOR="\\{"+SCALAR+"(,"+SCALAR+")*}";




    private Patterns() {
    }
}
