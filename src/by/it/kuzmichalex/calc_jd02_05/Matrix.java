package by.it.kuzmichalex.calc_jd02_05;

class Matrix extends Var {
    private double[][] value;

    /**
     * construct from double[][]
     */
    Matrix(double[][] value) {
        this.value = new double[value.length][value[0].length];
        for (int y = 0; y < value.length; y++) {
            System.arraycopy(value[y], 0, this.value[y], 0, value[y].length);
        }
    }

    /**
     * Conctruct from Matrix
     */
    Matrix(Matrix inMatrix) {
        this.value = new double[inMatrix.value.length][inMatrix.value[0].length];
        for (int y = 0; y < inMatrix.value.length; y++) {
            System.arraycopy(inMatrix.value[y], 0, this.value[y], 0, inMatrix.value[y].length);
        }
    }

    /**
     * construct from string {{1,2},{3,4},{5,6}}
     */
    Matrix(String strMatrix) {
        int ySize;
        int xSize = 0;
        String[] stringOfMatrix = strMatrix.replace(" ", "").split("[\\}]{1}[\\,]{1}[\\{]{1}");
        ySize = stringOfMatrix.length;
        for (int i = 0; i < ySize; i++) {
            String[] elementOfString = stringOfMatrix[i].split(",");
            if (elementOfString.length > xSize) xSize = elementOfString.length;
        }
        value = new double[ySize][xSize];
        for (int y = 0; y < ySize; y++) {
            String[] elementOfString = stringOfMatrix[y].split(",");
            for (int x = 0; x < elementOfString.length; x++) {
                this.value[y][x] = Double.parseDouble(elementOfString[x].replaceAll("[\\{|\\}]{1}", ""));
            }
        }
    }


    /**
     * Converts Matrix value to string {{1, 2, 3}, {4, 5, 6}}
     */
    @Override
    public String toString() {
        StringBuilder retStrBuffer = new StringBuilder("{");
        for (int y = 0; y < this.value.length; y++) {
            retStrBuffer.append("{");
            for (int x = 0; x < this.value[y].length; x++) {
                retStrBuffer.append(this.value[y][x]);
                if (x < (this.value[y].length - 1)) retStrBuffer.append(", ");
            }
            retStrBuffer.append("}");
            if (y < (this.value.length - 1)) retStrBuffer.append(", ");
        }
        retStrBuffer.append("}");
        return retStrBuffer.toString();
    }

    public double[][] getValue() {
        return value;
    }

    //////////////////////Сложения с матрицей
    @Override
    public Var add(Var rightOperand) throws CalcException {
        return rightOperand.add(this);
    }

    //Scalar + Matrix
    @Override
    public Var add(Scalar leftOperand) {
        double[][] returnMatrix = new double[this.value.length][this.value[0].length];
        for (int y = 0; y < this.value.length; y++) {
            for (int x = 0; x < this.value[0].length; x++) {
                returnMatrix[y][x] = this.value[y][x] + leftOperand.getValue();
            }
        }
        return new Matrix(returnMatrix);
    }

    //Vector + Matrix не существует
    //Matrix + Matrix
    @Override
    public Var add(Matrix leftOperand) throws CalcException {
        if (this.value.length != leftOperand.value.length) {
            throw new CalcException(LanguageManager.get(Messages.matrix_addition_error_rows));
        }
        if (this.value[0].length != leftOperand.value[0].length) {
            throw new CalcException(LanguageManager.get(Messages.matrix_addition_error_columns));
        }
        double[][] returnMatrix = new double[this.value.length][this.value[0].length];
        for (int y = 0; y < this.value.length; y++) {
            for (int x = 0; x < this.value[0].length; x++) {
                returnMatrix[y][x] = this.value[y][x] + leftOperand.value[y][x];
            }
        }
        return new Matrix(returnMatrix);
    }

    //Умножения на матрицу
    @Override
    public Var mul(Var rightOperand) throws CalcException {
        return rightOperand.mul(this);
    }

    //Scalar*Matrix
    @Override
    public Var mul(Scalar leftOperand) {
        double[][] returmMatrix = new double[this.value.length][this.value[0].length];
        for (int y = 0; y < this.value.length; y++) {
            for (int x = 0; x < this.value[0].length; x++) {
                returmMatrix[y][x] = this.value[y][x] * leftOperand.getValue();
            }
        }
        return new Matrix(returmMatrix);
    }

    //Vectror*Matrix не существует

    //Matrix*Matrix
    @Override
    public Var mul(Matrix leftOperand) throws CalcException {
        //Умножение матриц. Кол-во столбцов левой матрицы должно соответствовать кол-ву строк правой.
        if (leftOperand.value[0].length != this.value.length) {
            throw new CalcException(LanguageManager.get(Messages.matrix_multiplication_impissible));
        }
        int resultRows = leftOperand.value.length;
        int resultColumns = this.value[0].length;
        double[][] returnMatrix = new double[resultRows][resultColumns];
        for (int y = 0; y < resultRows; y++) {
            for (int x = 0; x < resultColumns; x++) {
                for (int s = 0; s < leftOperand.value[0].length; s++)
                    returnMatrix[y][x] += leftOperand.value[y][s] * this.value[s][x];
            }
        }
        return new Matrix(returnMatrix);
    }

    //Вычитание матрицы
    @Override
    public Var sub(Var rightOperand) throws CalcException {
        return rightOperand.sub(this);
    }

    //Scalar - Matrix не существует

    //Vector - Matrix не существует

    //Matrix - Matrix
    @Override
    public Var sub(Matrix leftOperand) throws CalcException {
        Var minusScalar = new Scalar(-1.0);
        Var minusMatrix = this.mul(minusScalar);
        return leftOperand.add(minusMatrix);
    }

    //Деления на матрицу. не существуют
    @Override
    public Var div(Var rightOperand) throws CalcException {
        return rightOperand.div(this);
    }
}
