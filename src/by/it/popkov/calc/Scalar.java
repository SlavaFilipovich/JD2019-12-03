package by.it.popkov.calc;

class Scalar extends Var {
    private double value;

    Scalar(double value) {
        this.value = value;
    }

    Scalar(Scalar scalar) {
        this.value = scalar.value;
    }

    Scalar(String strScalar) {
        this.value = Double.parseDouble(strScalar);
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    public double getValue() {
        return value;
    }

    /******************************** Operations ****************************/

    @Override
    public Var add(Var other) throws CalcException {
        return other.preAdd(this);
    }

    @Override
    Var preAdd(Var other) throws CalcException {
        return other.add(this);
    }

    @Override
    public Var add(Scalar other) throws CalcException {
        return new Scalar(this.value + other.value);
    }

    @Override
    public Var add(Vector other) throws CalcException {
        return other.add(this);
    }

    @Override
    public Var add(Matrix other) throws CalcException {
        return other.add(this);
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
        return new Scalar(this.value - other.value);
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
        return other.preDiv(this);
    }

    @Override
    Var preDiv(Var other) throws CalcException {
        return other.div(this);
    }

    @Override
    public Var div(Scalar other) throws CalcException {
        if (other.value == 0) throw new CalcException(Error.DIVISION_BY_ZERO);
        return new Scalar(this.value / other.value);
    }
}
