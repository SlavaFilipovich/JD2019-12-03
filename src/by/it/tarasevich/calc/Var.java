package by.it.tarasevich.calc;


import java.util.HashMap;
import java.util.Map;

abstract class Var implements Operation {

    private static Map<String, Var> vars=new HashMap<>();


    @Override
    public Var add(Var other) throws CalcException {
        throw new CalcException("Operation " + this + "+" + other + " impossible");
    }

    @Override
    public Var sub(Var other) throws CalcException {
        throw new CalcException("Operation " + this + "-" + other + " impossible");
    }

    @Override
    public Var mul(Var other) throws CalcException {
        throw new CalcException("Operation " + this + "*" + other + " impossible");
    }

    @Override
    public Var div(Var other) throws CalcException {
        throw new CalcException("Operation " + this + "/" + other + " impossible");
    }

    @Override
    public String toString() {
        return "abstract Var";
    }

    static Var createVar(String strVar) throws CalcException {
        if (strVar.matches(Patterns.SCALAR))
            return new Scalar(strVar);
        else if (strVar.matches(Patterns.VECTOR))
            return new Vector(strVar);
   //     else if (strVar.matches(Patterns.MATRIX))
  //          return new Matrix(strVar);
        else
        {
            Var var = vars.get(strVar);
            if (var != null) {
                return var;
            } else {
                throw new CalcException("Unknown expression: "+strVar);
            }
        }
    }

    static void save(String key, Var value) throws CalcException {
        vars.put(key,value);
        VarFile.save(vars);

    }

}
