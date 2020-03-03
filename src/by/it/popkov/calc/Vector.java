package by.it.popkov.calc;


class Vector extends Var {
    private double[] value;

    Vector(double[] value) {
        this.value = new double[value.length];
        System.arraycopy(value, 0, this.value, 0, value.length);
    }

    Vector(Vector vector) {
        this.value = new double[vector.value.length];
        System.arraycopy(vector.value, 0, this.value, 0, vector.value.length);
    }

    Vector(String strVector) {
        strVector = strVector.replaceAll(" ", "");
        strVector = strVector.replaceAll("[{}]", "");
        strVector = strVector.replaceAll(",", " ");
        String[] doubNums = strVector.split(" ");
        value = new double[doubNums.length];
        for (int i = 0; i < doubNums.length; i++) {
            value[i] = Double.parseDouble(doubNums[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder toStr = new StringBuilder("{");
        for (int i = 0; i < value.length; i++) {
            if (i != value.length - 1) {
                toStr.append(value[i]).append(", ");
            } else {
                toStr.append(value[i]).append("}");
            }
        }
        return toStr.toString();
    }

    public double[] getValue() {
        return value;
    }

    /************************************ Operations ****************************/

    @Override
    public Var add(Var other) throws CalcException {
        return other.preAdd(this);
    }
    @Override
    Var preAdd(Var other) throws CalcException {
        return other.add(this);
    }

    @Override
    public Var add(Scalar other) {
        double[] out = new double[this.value.length];
        for (int i = 0; i < out.length; i++) {
            out[i] = this.value[i] + other.getValue();
        }
        return new Vector(out);
    }

    @Override
    public Var add(Vector other) throws CalcException {
        if (other.value.length != this.value.length) throw new CalcException(Error.UNCORRECT_VECTOR_FORMAT);
        double[] out = new double[this.value.length];
        for (int i = 0; i < out.length; i++) {
            out[i] = this.value[i] + other.value[i];
        }
        return new Vector(out);
    }

    @Override
    public Var sub(Var other) throws CalcException {
        return other.preSub(this);
    }

    @Override
    Var preSub(Var other) throws CalcException {
        return other.sub(this);
    }

    @Override
    public Var sub(Scalar other) throws CalcException {
        double[] out = new double[this.value.length];
        for (int i = 0; i < out.length; i++) {
            out[i] = this.value[i] - other.getValue();
        }
        return new Vector(out);
    }

    @Override
    public Var sub(Vector other) throws CalcException {
        if (other.value.length != this.value.length) throw new CalcException(Error.UNCORRECT_VECTOR_FORMAT);
        double[] out = new double[this.value.length];
        for (int i = 0; i < out.length; i++) {
            out[i] = this.value[i] - other.value[i];
        }
        return new Vector(out);
    }

    @Override
    public Var mul(Var other) throws CalcException {
        return other.preMul(this);
    }

    @Override
    Var preMul(Var other) throws CalcException {
        return other.mul(this);
    }

    @Override
    public Var mul(Scalar other) throws CalcException {
        double[] out = new double[this.value.length];
        for (int i = 0; i < out.length; i++) {
            out[i] = this.value[i] * other.getValue();
        }
        return new Vector(out);
    }

    @Override
    public Var mul(Vector other) throws CalcException {
        if (other.value.length != this.value.length) throw new CalcException(Error.UNCORRECT_VECTOR_FORMAT);
        double out = 0;
        for (int i = 0; i < this.value.length; i++) {
            out += this.value[i] * other.value[i];
        }
        return new Scalar(out);
    }

    @Override
    public Var mul(Matrix other) throws CalcException {
        double[][] otherValue = other.getValue();
        if (this.value.length != otherValue.length && this.value.length != otherValue[0].length) {
            throw new CalcException(Error.UNCORRECT_MATRIX_FORMAT);
        }
        double[] vector = this.value;
        double[] outPut = new double[this.value.length];
        for (int i = 0; i < this.value.length; i++) {
            for (int j = 0; j < vector.length; j++) {
                outPut[i] = outPut[i] + otherValue[i][j] * vector[j];
            }
        }
        return new Vector(outPut);
    }

    @Override
    public Var div(Var other) throws CalcException {
        return other.preDiv(this);
    }

    @Override
    public Var div(Scalar other) throws CalcException {
        if (other.getValue() == 0) {
            throw new CalcException(Error.DIVISION_BY_ZERO);
        }
        double[] out = new double[this.value.length];
        for (int i = 0; i < out.length; i++) {
            out[i] = this.value[i] / other.getValue();
        }
        return new Vector(out);
    }


}
