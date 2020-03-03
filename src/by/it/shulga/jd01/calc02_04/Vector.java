package by.it.shulga.jd01.calc02_04;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Vector extends Var {
    private double[] value;

    Vector(double[] value) {
        this.value = new double[value.length];
        System.arraycopy(value, 0, this.value, 0, value.length);
    }

    Vector(Vector vector) {
        this(vector.value);
    }

    Vector(String strVector) {

        Matcher mch = Pattern.compile("\\{(.*)}").matcher(strVector);
        if (mch.find()) {
            String[] sArr = mch.group(1).replace(" ", "").split(",");
            value = new double[sArr.length];
            for (int i = 0; i < sArr.length; i++) {
                value[i] = Double.parseDouble(sArr[i]);
            }
        }
    }

    public double[] getValue() {
        return value;
    }

    public void setValue(double[] value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");
        String delimiter = "";
        for (double element : value) {
            stringBuilder.append(delimiter).append(element);
            delimiter = ", ";
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    @Override
    public Var add(Var other) throws CalcException {
        return other.add(this);
    }

    @Override
    public Var add(Scalar other) {
        double[] result = Arrays.copyOf(value, value.length);
        for (int i = 0; i < result.length; i++) {
            result[i] += other.getValue();
        }
        return new Vector(result);
    }

    @Override
    public Var add(Vector other) throws CalcException {
        if (this.value.length == other.value.length) {
            double[] result = Arrays.copyOf(value, value.length);
            for (int i = 0; i < result.length; i++) {
                result[i] += other.value[i];
            }
            return new Vector(result);
        }
        return super.mul(other);
    }

    @Override
    public Var sub(Var other) throws CalcException {
        return other.sub(this);
    }

    @Override
    public Var sub(Scalar other) {
        double[] result = Arrays.copyOf(value, value.length);
        for (int i = 0; i < result.length; i++) {
            result[i] = other.getValue() - result[i];
        }
        return new Vector(result);
    }

    @Override
    public Var sub(Vector other) throws CalcException {
        if (this.value.length == other.value.length) {
            double[] result = Arrays.copyOf(other.value, other.value.length);
            for (int i = 0; i < result.length; i++) {
                result[i] -= value[i];
            }
            return new Vector(result);
        }
        return super.sub(other);
    }

    @Override
    public Var mul(Var other) throws CalcException {
        return other.mul(this);
    }

    @Override
    public Var mul(Scalar other) {
        double[] result = Arrays.copyOf(value, value.length);
        for (int i = 0; i < result.length; i++) {
            result[i] *= other.getValue();
        }
        return new Vector(result);
    }

    @Override
    public Var mul(Vector other) throws CalcException {
        if (this.value.length == other.value.length) {
            double result = 0;
            for (int i = 0; i < value.length; i++) {
                result += value[i] * other.value[i];
            }
            return new Scalar(result);
        }
        return super.mul(other);
    }

    @Override
    public Var div(Var other) throws CalcException {
        return other.div(this);
    }
}