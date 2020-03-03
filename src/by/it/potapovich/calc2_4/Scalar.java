package by.it.potapovich.calc2_4;

class Scalar extends Var {
    protected double value;

    public double getValue() {
        return value;
    }

    Scalar(double value) {
        this.value = value;
    }

    Scalar(Scalar otherScalar) {
        this.value = otherScalar.value;
    }

    Scalar(String strValue) {
        this.value = Double.parseDouble(strValue);
    }

    @Override
    public Var add(Var other) throws CalcException {
        if (other instanceof Scalar) {
            return new Scalar(this.value + ((Scalar) other).value);
        }
        return other.add(this);
    }



    @Override
    public Var sub(Var other) throws CalcException {
        if (other instanceof Scalar) {
            return new Scalar(this.value - ((Scalar) other).value);
        }
        return new Scalar(-1).mul(other.sub(this));
    }

    @Override
    public Var mul(Var other) throws CalcException {
        if (other instanceof Scalar) {
            return new Scalar(this.value * ((Scalar) other).value);
        }
        return other.mul(this);
    }

    @Override
    public Var div(Var other) throws CalcException {
        if (other instanceof Scalar) {
            double op2=((Scalar) other).value;
            if (op2==0){
                CalcException exception = new CalcException("Omg!! Division by zero");
                throw exception;
            }
            return new Scalar(this.value / ((Scalar) other).value);
        }
        return super.div(other);
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
