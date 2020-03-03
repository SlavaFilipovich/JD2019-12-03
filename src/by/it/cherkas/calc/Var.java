package by.it.cherkas.calc;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

abstract class Var implements Operation {
    private static Map<String, Var> valueMap = new HashMap<>();



    @Override
    public Var add(Var other) throws CalcException {
        throw new CalcException(other + " + " + this + " impossible");
    }


    @Override
    public Var add(Scalar other) throws CalcException {
        throw new CalcException(other + " + " + this + " impossible");
    }

    @Override
    public Var add(Vector other) throws CalcException {
        throw new CalcException(other + " + " + this + " impossible");
    }

    @Override
    public Var sub(Var other) throws CalcException {
        throw new CalcException(other + " - " + this + " impossible");
    }

    @Override
    public Var sub(Scalar other) throws CalcException {
        throw new CalcException(other + " - " + this + " impossible");
    }

    @Override
    public Var sub(Vector other) throws CalcException {
        throw new CalcException(other + " - " + this + " impossible");
    }

    @Override
    public Var mul(Var other) throws CalcException {
        throw new CalcException(other + " * " + this + " impossible");
    }

    @Override
    public Var mul(Scalar other) throws CalcException {
        throw new CalcException(other + " * " + this + " impossible");
    }

    @Override
    public Var mul(Vector other) throws CalcException {
        throw new CalcException(other + " *" + this + " impossible");
    }

    @Override
    public Var div(Var other) throws CalcException {
        throw new CalcException(other + " / " + this + " impossible");
    }

    @Override
    public Var div(Scalar other) throws CalcException {
        throw new CalcException(other + " / " + this + " impossible");
    }

    @Override
    public Var div(Vector other) throws CalcException {
        throw new CalcException(other + " / " + this + " impossible");
    }


    static Var newVar(String strVar) throws CalcException {
        if (strVar.matches(Patterns.SCALAR))
            return new Scalar(strVar);
        else if (strVar.matches(Patterns.VECTOR))
            return new Vector(strVar);
        else throw new CalcException("неправильный ввод");
    }

    static void save(String value, Var value1) throws CalcException {
        valueMap.put(value, value1);
        CalcFile.writeValue(valueMap);
    }

    static void printVar() {
        String values = valueMap.toString();
        System.out.println(values);
    }

    static void sortVar() {
        final String sortedValue = new TreeMap<>(valueMap).toString();
        System.out.println(sortedValue);
    }
}
