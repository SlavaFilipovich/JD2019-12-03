package by.it.ban.calc;

import java.util.Arrays;

class Scalar extends Var {
    private double value;

    Scalar(double value) {
        this.value = value;
    }

    Scalar(Scalar scalar) {
        this.value = scalar.value;
    }

    public Scalar(String strScalar) {
        this.value = Double.parseDouble(strScalar);
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Double.toString(this.value);
    }

    @Override
    public Var add(Var other) throws CalcException {
        return other.add(this);
    }

    @Override
    public Var add(Scalar other) throws CalcException{
        return new Scalar(this.value + other.value);
    }

    @Override
    public Var add(Vector other) throws CalcException{
        return other.add(this);
    }

    @Override
    public Var add(Matrix other) throws CalcException {
        return other.add(this);
    }

    @Override
    public Var sub(Var other) throws CalcException {
        return other.sub(this);
    }

    @Override
    public Var sub(Scalar other) throws CalcException {
        return new Scalar(other.value - this.value);
    }

    @Override
    public Var sub(Vector other) throws CalcException {
        double[] op1 = Arrays.copyOf(other.getValue(), other.getValue().length);
        for (int i = 0; i < op1.length; i++) {
            op1[i] = op1[i] - value;
        }
        return new Vector(op1);
    }

    @Override
    public Var sub(Matrix other) throws CalcException {
        double[][] op1 = Arrays.copyOf(other.getValue(), other.getValue().length);
        for (int i = 0; i < op1.length; i++) {
            op1[i] = Arrays.copyOf(other.getValue()[i], other.getValue()[i].length);
        }
        for (int i = 0; i < op1.length; i++) {
            for (int j = 0; j < op1[i].length; j++) {
                op1[i][j] = op1[i][j] - value;
            }

        }
        return new Matrix(op1);
    }

    @Override
    public Var mul(Var other) throws CalcException {
        return other.mul(this);
    }

    @Override
    public Var mul(Scalar other) throws CalcException {
        return new Scalar(this.value * other.value);
    }

    @Override
    public Var mul(Vector other) throws CalcException {
        return other.mul(this);
    }

    @Override
    public Var mul(Matrix other) throws CalcException {
        return other.mul(this);
    }

    @Override
    public Var div(Var other) throws CalcException {
        return other.div(this);
    }

    @Override
    public Var div(Scalar other) throws CalcException {
        if (this.value != 0) {
            return new Scalar(other.value / this.value);
        }
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.ZERO));
    }

    @Override
    public Var div(Vector other) throws CalcException {
        if (value != 0) {
            double[] op1 = Arrays.copyOf(other.getValue(), other.getValue().length);
            for (int i = 0; i < op1.length; i++) {
                op1[i] = op1[i] / value;
            }
            return new Vector(op1);
        }
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.ZERO));
    }

    @Override
    public Var div(Matrix other) throws CalcException {
        if (value!=0){
            double[][] op1 = Arrays.copyOf(other.getValue(), other.getValue().length);
            for (int i = 0; i < op1.length; i++) {
                op1[i] = Arrays.copyOf(other.getValue()[i], other.getValue()[i].length);
            }
            for (int i = 0; i < op1.length; i++) {
                for (int j = 0; j < op1[i].length; j++) {
                    op1[i][j] = op1[i][j]/value;
                }

            }
            return new Matrix(op1);
        }
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.ZERO));
    }
}
