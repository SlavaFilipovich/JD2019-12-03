package by.it.kuzmichalex.calc_jd02_05;

class Vector extends Var {
    private double[] value;

    /**
     * construct from double[]
     */
    Vector(double[] value) {
        this.value = new double[value.length];
        System.arraycopy(value, 0, this.value, 0, value.length);
    }

    /**
     * construct from Vector
     */
    Vector(Vector vector) {
        this.value = new double[vector.value.length];
        System.arraycopy(vector.value, 0, this.value, 0, vector.value.length);
    }

    /**
     * construct from string like {1.234, 5.678}
     */
    Vector(String strValue) {
        String[] strElements = strValue.replaceAll("[\\\\{|\\\\}| ]", " ").split(",");
        this.value = new double[strElements.length];
        for (int i = 0; i < strElements.length; i++) {
            this.value[i] = Double.parseDouble(strElements[i]);
        }
    }

    /**
     * converts Vector to String
     */
    @Override
    public String toString() {
        StringBuilder returnStrBuilder = new StringBuilder("{");
        for (int i = 0; i < this.value.length; i++) {
            returnStrBuilder.append(this.value[i]);
            if (i < (this.value.length - 1)) returnStrBuilder.append(", ");
        }
        returnStrBuilder.append("}");
        return returnStrBuilder.toString();
    }

    public double[] getValue() {
        return value;
    }

    /////////////////////////////// add
    @Override
    public Var add(Var rightOperand) throws CalcException {
        return rightOperand.add(this);
    }

    //Scalar+Vector
    @Override
    public Var add(Scalar leftOperand) {
        double[] returnVector = new double[this.value.length];
        for (int i = 0; i < this.value.length; i++) {
            returnVector[i] = this.value[i] + leftOperand.getValue();
        }
        return new Vector(returnVector);
    }

    //Vector+Vectior
    @Override
    public Var add(Vector leftOperand) throws CalcException {
        if (this.value.length != leftOperand.value.length) {
            throw new CalcException(LanguageManager.get(Messages.vector_addind_impossible));
        }
        double[] returnVector = new double[leftOperand.value.length];
        for (int i = 0; i < leftOperand.value.length; i++) {
            returnVector[i] = leftOperand.value[i] + this.value[i];
        }
        return new Vector(returnVector);
    }

    /////////////////////////////// Вычитания вектора
    @Override
    public Var sub(Var rightOperand) throws CalcException {
        return rightOperand.sub(this);
    }

    //Scalar-vector не существует

    //Vector-Vector
    @Override
    public Var sub(Vector leftOperand) throws CalcException {
        Var minusScalar = new Scalar(-1);
        Var minusVector = this.mul(minusScalar);
        return leftOperand.add(minusVector);
    }

    //Matrix - Vector не существует

    ////////////////////////////// Умножения на вектор
    @Override
    public Var mul(Var rightOperand) throws CalcException {
        return rightOperand.mul(this);
    }

    //Scalar*Vector
    @Override
    public Var mul(Scalar leftOperand) {
        double[] returnVector = new double[this.value.length];
        for (int i = 0; i < this.value.length; i++) {
            returnVector[i] = this.value[i] * leftOperand.getValue();
        }
        return new Vector(returnVector);
    }

    //Vector*Vector
    @Override
    public Var mul(Vector leftOperand) throws CalcException {
        if (this.value.length != leftOperand.value.length) {
            throw new CalcException(LanguageManager.get(Messages.vector_multipilication_impossible));
        }
        double scalarResult = 0;
        for (int i = 0; i < this.value.length; i++) {
            scalarResult += (leftOperand.value[i] * this.value[i]);
        }
        return new Scalar(scalarResult);
    }

    //Matrix*Vector
    @Override
    public Var mul(Matrix leftOperand) throws CalcException {
        double[][] leftOperandValue = leftOperand.getValue();

        if (leftOperandValue[0].length != this.value.length) {
            throw new CalcException(LanguageManager.get(Messages.matrix_mul_vector_impossible));
        }
        double[] returnVector = new double[leftOperandValue.length];
        for (int y = 0; y < leftOperandValue.length; y++) {
            for (int x = 0; x < leftOperandValue[0].length; x++) {
                returnVector[y] += leftOperandValue[y][x] * this.value[x];
            }
        }
        return new Vector(returnVector);
    }

    //Делений на вектор не существует
    @Override
    public Var div(Var rightOperand) throws CalcException {
        return rightOperand.div(this);
    }
}
