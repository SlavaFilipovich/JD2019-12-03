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
    public Var add(Var other) {
        if (other instanceof Scalar) {
            double[][] outPut = new double[this.value.length][this.value[0].length];
            for (int i = 0; i < this.value.length; i++) {
                for (int j = 0; j < this.value[0].length; j++) {
                    outPut[i][j] = this.value[i][j] + ((Scalar) other).getValue();
                }
            }
            return new Matrix(outPut);
        } else if (other instanceof Matrix) {
            double[][] outPut = new double[this.value.length][this.value[0].length];
            for (int i = 0; i < this.value.length; i++) {
                for (int j = 0; j < this.value[0].length; j++) {
                    outPut[i][j] = this.value[i][j] + ((Matrix) other).value[i][j];
                }
            }
            return new Matrix(outPut);
        }
        return super.add(other);
    }


    @Override
    public Var sub(Var other) {
        if (other instanceof Scalar) {
            double[][] outPut = new double[this.value.length][this.value[0].length];
            for (int i = 0; i < this.value.length; i++) {
                for (int j = 0; j < this.value[0].length; j++) {
                    outPut[i][j] = this.value[i][j] - ((Scalar) other).getValue();
                }
            }
            return new Matrix(outPut);
        } else if (other instanceof Matrix) {
            double[][] outPut = new double[this.value.length][this.value[0].length];
            for (int i = 0; i < this.value.length; i++) {
                for (int j = 0; j < this.value[0].length; j++) {
                    outPut[i][j] = this.value[i][j] - ((Matrix) other).value[i][j];
                }
            }
            return new Matrix(outPut);
        }
        return super.sub(other);
    }

    @Override
    public Var mul(Var other) {
        if (other instanceof Matrix) {
            double[][] outPut = new double[this.value.length][((Matrix) other).value[0].length];
            for (int i = 0; i < this.value.length; i++) {
                for (int j = 0; j < ((Matrix) other).value[0].length; j++) {
                    for (int y = 0; y < ((Matrix) other).value.length; y++) {
                        outPut[i][j] = outPut[i][j] + this.value[i][y] * ((Matrix) other).value[y][j];
                    }
                }
            }
            return new Matrix(outPut);
        } else if (other instanceof Vector) {
            double[] vector = ((Vector) other).getValue();
            double[] outPut = new double[this.value.length];
            for (int i = 0; i < this.value.length; i++) {
                for (int j = 0; j < vector.length; j++) {
                    outPut[i] = outPut[i] + this.value[i][j] * vector[j];
                }
            }
            return new Vector(outPut);
        } else if (other instanceof Scalar) {
            double[][] outPut = new double[this.value.length][this.value[0].length];
            for (int i = 0; i < this.value.length; i++) {
                for (int j = 0; j < this.value[0].length; j++) {
                    outPut[i][j] = this.value[i][j] * ((Scalar) other).getValue();
                }
            }
            return new Matrix(outPut);
        }
        return super.mul(other);
    }

    @Override
    public Var div(Var other) {
        if (other instanceof Scalar) {
            if (((Scalar) other).getValue() == 0) {
                System.out.println("Error");
            }
            double[][] outPut = new double[this.value.length][this.value[0].length];
            for (int i = 0; i < this.value.length; i++) {
                for (int j = 0; j < this.value[0].length; j++) {
                    outPut[i][j] = this.value[i][j] / ((Scalar) other).getValue();
                }
            }
            return new Matrix(outPut);
        }
        return super.div(other);
    }
}
