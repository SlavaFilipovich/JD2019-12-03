package by.it.samuseva.calc;


import static java.lang.System.*;

class Matrix extends Var {
    private double[][] value;

    Matrix(double[][] value) {
        this.value = new double[value.length][value[0].length];
        for (int i = 0; i < value.length; i++) {
            arraycopy(value[i], 0, this.value[i], 0, value[0].length);
        }
    }
    Matrix(Matrix matrix) {
        this.value = new double[matrix.value.length][matrix.value[0].length];
        for (int i = 0; i < matrix.value.length; i++) {
            arraycopy(matrix.value[i], 0, this.value[i], 0, matrix.value[i].length);
        }
    }

    Matrix(String str) {
        String[] strRow = str.replaceAll(" ", "")
                .replaceAll("[{][{]", "")
                .replaceAll("[}][}]", "")
                .split("[}][,][{]");
        int size=0;
        for (String s : strRow) {
            String[] strCol = s.split(",");
            if (strCol.length>size) size = strCol.length;
        }
        double[][] array = new double[strRow.length][size];
        for (int i = 0; i < array.length; i++) {
            String[] strCol = strRow[i].split(",");
            for (int j = 0; j < strCol.length; j++) {
                array[i][j]=Double.parseDouble(strCol[j]);
            }
        }
        this.value = array;
    }
    //////////   methods for add operation
    @Override
    public Var add(Var rigth) throws CalcException{
        return rigth.add(this);
    }

    @Override
    public Var add(Scalar left) throws CalcException {
        double[][] array = new double[this.value.length][this.value[0].length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = this.value[i][j] + left.getValue();
            }
        }
        return new Matrix(array);
    }
    @Override
    public Var add(Matrix left) throws CalcException {
        if (this.value.length!=left.value.length || this.value[0].length!=left.value[0].length) {
            throw new CalcException("Операция сложения не возможна: размеры матриц разные!");
        }
        double[][] array = new double[this.value.length][this.value[0].length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = this.value[i][j] + left.value[i][j];
            }
        }
        return new Matrix(array);
    }

    //////////   methods for sub operation
    @Override
    public Var sub(Var rigth) throws CalcException{
        return rigth.sub(this);
    }

    @Override
    public Var sub(Scalar left) throws CalcException {
        double[][] array = new double[this.value.length][this.value[0].length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = this.value[i][j] - left.getValue();
            }
        }
        return new Matrix(array);
    }

    @Override
    public Var sub(Matrix left) throws CalcException {
        if (this.value.length!=left.value.length || this.value[0].length!=left.value[0].length) {
            throw new CalcException("Операция вычетания не возможна: размеры матриц разные!");
        }
        double[][] array = new double[this.value.length][this.value[0].length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = this.value[i][j]-left.value[i][j];
            }
        }
        return new Matrix(array);
    }

    //////////   methods for mul operation
    @Override
    public Var mul(Var rigth) throws CalcException{
        return rigth.mul(this);
    }

    @Override
    public Var mul(Scalar left) throws CalcException {
        double[][] array = new double[this.value.length][this.value[0].length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = this.value[i][j] * left.getValue();
            }
        }
        return new Matrix(array);
    }
    @Override
    public Var mul(Vector left) throws CalcException {
        if (this.value[0].length != left.getValue().length)
            throw new CalcException("Операция умножения не возможна: количесво столбцов матрицы не равно количеству строк вектора!");
        double[][] array = new double[this.value.length][this.value[0].length];
        double[] mul = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = this.value[i][j] * left.getValue()[j];
                mul[i] += array[i][j];
            }
        }
        return new Vector(mul);
    }
    @Override
    public Var mul(Matrix left) throws CalcException {
        if (this.value.length!=left.value.length || this.value[0].length!=left.value[0].length)
        throw new CalcException("Операция умножения не возможна: размеры матриц разные!");
        double[][] mul = new double[this.value.length][this.value[0].length];
        for (int i = 0; i < left.value.length; i++) {
            for (int j = 0; j < this.value[0].length; j++) {
                for (int k = 0; k < left.value[0].length; k++) {
                    mul[i][j] += this.value[i][k] * left.value[k][j];
                }
            }
        }
        return new Matrix(mul);
    }

    //////////   methods for div operation
    @Override
    public Var div(Var rigth) throws CalcException{
        return rigth.div(this);
    }
    @Override
    public Var div(Scalar left) throws CalcException {
        if (left.getValue() == 0 ) throw new CalcException("Делить на ноль нельзя!");
        double[][] array = new double[this.value.length][this.value[0].length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = array[i][j] / left.getValue();
            }
        }
        return new Matrix(array);

    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("{");
        String delimiter = "";
        for (int i = 0; i < this.value.length; i++) {
            string.append("{");
            delimiter = "";
            for (int j = 0; j < this.value[0].length; j++) {
                string.append(delimiter).append(this.value[i][j]);
                delimiter = ", ";
            }
            if (i<this.value.length-1) string.append("}, ");
            if (i==this.value.length-1) string.append("}");
        }
        string.append("}");
        return string.toString();
    }

}
