package by.it.ban.calc;


import java.util.*;

public abstract class Var implements Operation {

    private static Map<String, Var> vars=new HashMap<>();

    @Override
    public String toString() {
        return "Var{}";
    }

    @Override
    public Var add(Var other) throws CalcException {
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.ADDITION));
    }

    @Override
    public Var sub(Var other) throws CalcException {
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.SUBTRACTION));
    }

    @Override
    public Var mul(Var other) throws CalcException {
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.MULTIPLICATION));
    }

    @Override
    public Var div(Var other) throws CalcException {
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.DIVISION));
    }

    @Override
    public Var add(Scalar other) throws CalcException {
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.ADDITION));
    }

    @Override
    public Var sub(Scalar other) throws CalcException {
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.SUBTRACTION));
    }

    @Override
    public Var mul(Scalar other) throws CalcException {
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.MULTIPLICATION));
    }

    @Override
    public Var div(Scalar other) throws CalcException {
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.DIVISION));
    }

    @Override
    public Var add(Vector other) throws CalcException {
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.ADDITION));
    }

    @Override
    public Var sub(Vector other) throws CalcException {
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.SUBTRACTION));
    }

    @Override
    public Var mul(Vector other) throws CalcException {
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.MULTIPLICATION));
    }

    @Override
    public Var div(Vector other) throws CalcException {
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.DIVISION));
    }

    @Override
    public Var add(Matrix other) throws CalcException {
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.ADDITION));
    }

    @Override
    public Var sub(Matrix other) throws CalcException {
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.SUBTRACTION));
    }

    @Override
    public Var mul(Matrix other) throws CalcException {
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.MULTIPLICATION));
    }

    @Override
    public Var div(Matrix other) throws CalcException {
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.DIVISION));
    }

     static Var createVar(String strVar) throws CalcException {
        if (strVar.matches(Patterns.SCALAR))
            return new Scalar(strVar);
        else if (strVar.matches(Patterns.VECTOR))
            return new Vector(strVar);
        else if (strVar.matches(Patterns.MATRIX))
            return new Matrix(strVar);
        else
        {
            Var var = vars.get(strVar);
            if (var != null) {
                return var;
            } else {
                throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.INIT)+" "+strVar);
            }
        }
    }
    static void save(String key, Var value) throws CalcException {
        vars.put(key,value);
        VarFile.save(vars);
    }

    static void printvar() {
        for (Map.Entry<String, Var> stringVarEntry : vars.entrySet()) {
            System.out.println(stringVarEntry.getKey()+"="+stringVarEntry.getValue());
        }
    }
    static void sortvar() {
        TreeMap<String, Var> varTreeMap = new TreeMap<>(vars);
        for (Map.Entry<String, Var> entry : varTreeMap.entrySet()) {
            System.out.println(entry.getKey()+"="+entry.getValue());
        }
    }
}
