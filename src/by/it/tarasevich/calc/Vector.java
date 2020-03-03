package by.it.tarasevich.calc;

import java.util.Arrays;

class Vector extends Var {
    private double[] value;

    Vector(double[] value) {
        this.value = new double[value.length];
        System.arraycopy(value, 0, this.value, 0, value.length);
    }

    public Vector(Vector vector) {
        this(vector.value);
    }

    @Override
    public Var div(Var other) throws CalcException {
        return super.div(other);
    }

    public Vector(String strVector) {
        //1,2, 3, 4.0
        String[] part = strVector
                .trim()
                .replace(" ", "")
                .replace("{", "")
                .replace("}", "")
                .split(",\\s*");
        value=new double[part.length];
        for (int i = 0; i < part.length; i++) {
            value[i]=Double.parseDouble(part[i]);
        }
    }

    @Override
    public Var add(Var other) throws CalcException {
        if (other instanceof Scalar) {
            Scalar op2 = (Scalar) other;
            double[] op1 = Arrays.copyOf(value, value.length);
            for (int i = 0; i < op1.length; i++) {
                op1[i] += op2.getValue();
            }
            return new Vector(op1);
        }
        if (other instanceof Vector) {
            Vector op2 = (Vector) other;
            double[] op1 = Arrays.copyOf(value, value.length);
            for (int i = 0; i < op1.length; i++) {
                op1[i] += op2.value[i];
            }
            return new Vector(op1);
        }
        return super.add(other);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("{");
        String delimiter = "";
        for (double element : value) {
            res.append(delimiter).append(element);
            delimiter = ", ";
        }
        res.append("}");
        return res.toString();
    }
}

