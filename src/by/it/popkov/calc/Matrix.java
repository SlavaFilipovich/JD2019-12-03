package by.it.popkov.calc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Matrix extends Var {

    private double[][] value;

    Matrix(double[][] value) {
        this.value = new double[value.length][value[0].length];
        System.arraycopy(value, 0, this.value, 0, value.length);
    }

    Matrix(Matrix matrix) {
        this.value = new double[matrix.value.length][matrix.value[0].length];
        System.arraycopy(matrix.value, 0, this.value, 0, matrix.value.length);
    }

    Matrix(String strMatrix) {
        strMatrix = strMatrix.replaceAll(" ", "");
        strMatrix = strMatrix.replaceAll("[{]+", "");
        strMatrix = strMatrix.replaceAll("[}]{2,}", "");
        strMatrix = strMatrix.replaceAll(",", " ");
        strMatrix = strMatrix.replaceAll("}", " }");

        Pattern p = Pattern.compile("}");
        Matcher m = p.matcher(strMatrix);
        int columnCounter = 1;
        while (m.find()) {
            columnCounter++;
        }
        Pattern p2 = Pattern.compile("[[0-9]/.?]+");
        Matcher m2 = p2.matcher(strMatrix);
        int numCounter = 0;
        while (m2.find()) {
            numCounter++;
        }
        strMatrix = strMatrix.replaceAll(" }", "");
        String[] nums = strMatrix.split(" ");
        String[] newNums = new String[nums.length];
        for (int i = 0, j = 0; i < newNums.length; i++) {
            if (!nums[i].equals("")) {
                newNums[j] = nums[i];
                j++;
            }
        }

        value = new double[columnCounter][numCounter / columnCounter];
        for (int i = 0; i < value.length; i++) {
            for (int j = 0; j < value[0].length; j++) {
                value[i][j] = Double.parseDouble(newNums[i * value[0].length + j]);
            }
        }

    }

    @Override
    public String toString() {
        StringBuilder toStr = new StringBuilder("{");
        for (int i = 0; i < value.length; i++) {
            toStr.append("{");
            for (int j = 0; j < value[0].length; j++) {
                if (j != value[0].length - 1) {
                    toStr.append(value[i][j]).append(", ");
                } else if (j == value[0].length - 1 && i != value.length - 1) {
                    toStr.append(value[i][j]).append("}, ");
                } else if (j == value[0].length - 1 && i == value.length - 1) {
                    toStr.append(value[i][j]).append("}");
                }
            }

        }
        toStr.append("}");
        return toStr.toString();
    }

    public double[][] getValue() {
        return value;
    }

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
        double[][] outPut = new double[this.value.length][this.value[0].length];
        for (int i = 0; i < this.value.length; i++) {
            for (int j = 0; j < this.value[0].length; j++) {
                outPut[i][j] = this.value[i][j] + other.getValue();
            }
        }
        return new Matrix(outPut);
    }

    @Override
    public Var add(Matrix other) throws CalcException {
        if (other.value.length != this.value.length || other.value[0].length != this.value[0].length) {
            throw new CalcException("Некоректрый формат матрич");
        }
        double[][] outPut = new double[this.value.length][this.value[0].length];
        for (int i = 0; i < this.value.length; i++) {
            for (int j = 0; j < this.value[0].length; j++) {
                outPut[i][j] = this.value[i][j] + other.value[i][j];
            }
        }
        return new Matrix(outPut);
    }

    public Var sub(Var other) throws CalcException {
        return other.preSub(this);
    }

    @Override
    Var preSub(Var other) throws CalcException {
        return other.sub(this);
    }

    @Override
    public Var sub(Scalar other) throws CalcException {
        double[][] outPut = new double[this.value.length][this.value[0].length];
        for (int i = 0; i < this.value.length; i++) {
            for (int j = 0; j < this.value[0].length; j++) {
                outPut[i][j] = this.value[i][j] - other.getValue();
            }
        }
        return new Matrix(outPut);
    }

    @Override
    public Var sub(Matrix other) throws CalcException {
        if (other.value.length != this.value.length || other.value[0].length != this.value[0].length) {
            throw new CalcException(Error.UNCORRECT_MATRIX_FORMAT);
        }

        double[][] outPut = new double[this.value.length][this.value[0].length];
        for (int i = 0; i < this.value.length; i++) {
            for (int j = 0; j < this.value[0].length; j++) {
                outPut[i][j] = this.value[i][j] - other.value[i][j];
            }
        }
        return new Matrix(outPut);
    }

    @Override
    public Var mul(Var other) throws CalcException {
        return other.preMul(this);
    }

    /**Очень важно помнить, что здесь не работает «правило перестановки мест слагаемых» так как почти всегда MN ≠ NM.
     *  Поэтому, производя операцию умножения матриц их ни в коем случае нельзя менять местами.**/
    @Override
    Var preMul(Var other) throws CalcException {
        return other.mul(this);
    }

    @Override
    public Var mul(Scalar other) throws CalcException {
        double[][] outPut = new double[this.value.length][this.value[0].length];
        for (int i = 0; i < this.value.length; i++) {
            for (int j = 0; j < this.value[0].length; j++) {
                outPut[i][j] = this.value[i][j] * other.getValue();
            }
        }
        return new Matrix(outPut);
    }

    @Override
    public Var mul(Vector other) throws CalcException {
        if (other.getValue().length != this.value.length && other.getValue().length != this.value[0].length) {
            throw new CalcException(Error.UNCORRECT_MATRIX_FORMAT);
        }
        double[] vector = other.getValue();
        double[] outPut = new double[this.value.length];
        for (int i = 0; i < this.value.length; i++) {
            for (int j = 0; j < vector.length; j++) {
                outPut[i] = outPut[i] + this.value[i][j] * vector[j];
            }
        }
        return new Vector(outPut);
    }

    @Override
    public Var mul(Matrix other) throws CalcException {
        if (other.value.length != this.value.length || other.value[0].length != this.value[0].length) {
            throw new CalcException(Error.UNCORRECT_MATRIX_FORMAT);
        }
        double[][] outPut = new double[this.value.length][other.value[0].length];
        for (int i = 0; i < this.value.length; i++) {
            for (int j = 0; j < other.value[0].length; j++) {
                for (int y = 0; y < other.value.length; y++) {
                    outPut[i][j] = outPut[i][j] + this.value[i][y] * other.value[y][j];
                }
            }
        }
        return new Matrix(outPut);
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

        double[][] outPut = new double[this.value.length][this.value[0].length];
        for (int i = 0; i < this.value.length; i++) {
            for (int j = 0; j < this.value[0].length; j++) {
                outPut[i][j] = this.value[i][j] / other.getValue();
            }
        }
        return new Matrix(outPut);

    }
}
