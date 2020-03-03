package by.it.samuseva.calc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

abstract class Var implements Operation {

    private static Map<String, Var> vars = new HashMap<>();
    static void saveVar (String name, Var var) throws CalcException {
        vars.put(name, var);
        VarFile.writer(vars);

    }

    static void sortvar (){
        Set<Map.Entry<String, Var>> entries = vars.entrySet();
        for (Map.Entry<String, Var> entry : entries) {
            System.out.println(entry.getKey()+ " = " + entry.getValue());
        }
    }

    static Var createVar (String operand) throws CalcException {
        operand = operand.trim().replaceAll("\\s+", "");
        if (operand.matches(Patterns.SCALAR)){
            return new Scalar(operand);
        } else
        if (operand.matches(Patterns.VECTOR)){
            return new Vector(operand);
        } else
        if (operand.matches(Patterns.MATRIX)){
            return new Matrix(operand);
        } else{
            if (vars.containsKey(operand)) {
                return vars.get(operand);
            }
            else
                throw new CalcException("Переменная отсутствует в списке " +
                        "или невозможно создать такую переменную: " + operand);
        }
    }

    @Override
    public String toString() {
        return "это обстроктный класс";
    }

    //////////   methods for add operation
    @Override
    public Var add(Var right) throws CalcException {
        throw new CalcException("Операция сожения " + this + "+" + right + " невозможна");
    }
    public Var add(Scalar left) throws CalcException {
        throw new CalcException("Операция сожения " + this + "+" + left + " невозможна");
    }
    public Var add(Vector left) throws CalcException {
        throw new CalcException("Операция сожения " + this + "+" + left + " невозможна");

    }
    public Var add(Matrix left) throws CalcException {
        throw new CalcException("Операция сожения " + this + "+" + left + " невозможна");
    }

    //////////   methods for sub operation
    @Override
    public Var sub(Var right) throws CalcException {
        throw new CalcException("Операция вычетания " + this + "-" + right + " невозможна");
    }
    public Var sub(Scalar left) throws CalcException {
        throw new CalcException("Операция вычетания " + this + "-" + left + " невозможна");
    }
    public Var sub(Vector left) throws CalcException {
        throw new CalcException("Операция вычетания " + this + "-" + left + " невозможна");
    }
    public Var sub(Matrix left) throws CalcException {
        throw new CalcException("Операция вычетания " + this + "-" + left + " невозможна");
    }

    //////////   methods for mul operation
    @Override
    public Var mul(Var right) throws CalcException {
        throw new CalcException("Операция умножения " + this + "*" + right + " невозможна");
    }
    public Var mul(Scalar left) throws CalcException {
        throw new CalcException("Операция умножения " + this + "*" + left + " невозможна");
    }
    public Var mul(Vector left) throws CalcException {
        throw new CalcException("Операция умножения " + this + "*" + left + " невозможна");
    }
    public Var mul(Matrix left) throws CalcException {
        throw new CalcException("Операция умножения " + this + "*" + left + " невозможна");
    }

    //////////   methods for div operation
    @Override
    public Var div(Var other) throws CalcException {
        throw new CalcException("Операция деления " + this + "/" + other + " невозможна");
    }
    public Var div(Scalar left) throws CalcException {
        throw new CalcException("Операция деления " + this + "/" + left + " невозможна");
    }
    public Var div(Vector left) throws CalcException {
        throw new CalcException("Операция деления " + this + "/" + left + " невозможна");
    }
    public Var div(Matrix left) throws CalcException {
        throw new CalcException("Операция деления " + this + "/" + left + " невозможна");
    }
}
