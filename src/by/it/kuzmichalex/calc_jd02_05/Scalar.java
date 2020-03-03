package by.it.kuzmichalex.calc_jd02_05;

class Scalar extends Var {
    private double value;

    /*******************
     * constructors
     *******************/
    Scalar(double value) {
        this.value = value;
    }

    Scalar(Scalar scalar) {
        this.value = scalar.value;
    }

    Scalar(String strScalar) {
        this.value = Double.parseDouble(strScalar);
    }

    public double getValue() {
        return value;
    }

    /*****************************
     * Override Var.toString method
     *****************************/
    @Override
    public String toString() {
        return Double.toString(this.value);
    }

    //////////////////////// Сложения со скаляром
    @Override
    public Var add(Var rightOperand) throws CalcException {
        return rightOperand.add(this);
    }

    //Scalar+Scalar
    @Override
    public Var add(Scalar leftOperand) {
        return new Scalar(leftOperand.value + this.value);
    }

    //Vector+Scalar
    @Override
    public Var add(Vector leftOperand) {
        return leftOperand.add(this);
    }

    //Matrix+Scalar
    @Override
    public Var add(Matrix leftOperand) {
        return leftOperand.add(this);
    }

    //////////////////////// Отнимание скаляра
    @Override
    public Var sub(Var rightOperand) throws CalcException {
        return rightOperand.sub(this);
    }

    //Scalar-Scalar
    @Override
    public Var sub(Scalar leftOperand) {
        return new Scalar(leftOperand.value - this.value);
    }

    //Vector-Scalar
    @Override
    public Var sub(Vector leftOperand) throws CalcException {
        Var minusScalar = new Scalar(this.value * -1.0);
        return leftOperand.add(minusScalar);
    }

    //Matrix - Scalar
    @Override
    public Var sub(Matrix leftOperand) {
        double[][] leftOperandValue = leftOperand.getValue();

        double[][] returnMatrix = new double[leftOperandValue.length][leftOperandValue[0].length];
        for (int y = 0; y < leftOperandValue.length; y++) {
            for (int x = 0; x < leftOperandValue.length; x++) {
                returnMatrix[x][y] = leftOperandValue[x][y] - this.value;
            }
        }
        return new Matrix(returnMatrix);
    }

    //////////////////////// Умножения на скаляр
    @Override
    public Var mul(Var rightOperand) throws CalcException {
        return rightOperand.mul(this);
    }

    //Scalar*Scalar
    @Override
    public Var mul(Scalar leftOperand) {
        return new Scalar(leftOperand.value * this.value);
    }

    //Vector*Scalar
    @Override
    public Var mul(Vector leftOperand) {
        return leftOperand.mul(this);
    }

    //Matrix*Scalar
    @Override
    public Var mul(Matrix leftOperand) {
        return leftOperand.mul(this);
    }

    //////////////////////// деления на скаляр
    @Override
    public Var div(Var rightOperand) throws CalcException {
        return rightOperand.div(this);
    }

    //Scalar/Scalar
    @Override
    public Var div(Scalar leftOperand) throws CalcException {
        if (this.value == 0) {
            throw new CalcException(LanguageManager.get(Messages.division_by_zero));
        }
        return new Scalar(leftOperand.value / this.value);
    }

    //Vector/Scalar
    @Override
    public Var div(Vector leftOperand) throws CalcException {
        if (this.value == 0) {
            throw new CalcException(LanguageManager.get(Messages.division_by_zero));
        }
        Var hyperScalar = new Scalar(1.0 / this.value);
        return leftOperand.mul(hyperScalar);
    }

    //Matrix/Scalar
    @Override
    public Var div(Matrix leftOperand) throws CalcException {
        if (this.value == 0) {
            throw new CalcException(LanguageManager.get(Messages.division_by_zero));
        }
        Var hyperScalar = new Scalar(1.0 / this.value);
        return leftOperand.mul(hyperScalar);
    }
}

