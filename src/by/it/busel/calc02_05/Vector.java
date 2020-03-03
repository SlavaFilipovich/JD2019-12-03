package by.it.busel.calc02_05;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * a vector expression
 */
class Vector extends Var {
    /**
     * a value of "this" vector expression
     */
    private double[] value;

    /**
     * a method, which is a getter of a value of "this" vector expression
     *
     * @return a value of "this" vector expression
     */
    public double[] getValue() {
        return value;
    }

    /**
     * a constructor, which argument sets a value of "this" vector expression
     *
     * @param value a value of "this" vector expression
     */
    Vector(double[] value) {
        this.value = value;
    }

    /**
     * a constructor, which argument is another vector expression the value of which is to be assigned as a value of
     * "this" vector expression
     *
     * @param vector another vector expression the value of which is to be assigned as a value of
     *               "this" vector expression
     */
    Vector(Vector vector) {
        this.value = vector.value;
    }

    /**
     * a constructor that takes a String object, parses it into a value of "this" vector expression
     *
     * @param strVector a String object that contains a vector expression
     */
    Vector(String strVector) {
        Pattern pattern = Pattern.compile(Patterns.SCALAR);
        Matcher matcher = pattern.matcher(strVector);
        int counter = counterOfValueLength(matcher);
        if (counter > 0) {
            valueFiller(matcher, counter);
        }
    }

    /**
     * an overridden method of the Object. toString() method
     *
     * @return a value of "this" vector expression, which is to be printer when "this" object is called to be printed
     */

    @Override
    public String toString() {
        String sep = ", ";
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < value.length; i++) {
            sb.append(value[i]);
            if (i != value.length - 1) {
                sb.append(sep);
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * an overridden from a base class "Var" method that determines a type of a class from its argument
     * and depending on the type performs a mathematical operation of "addition"
     *
     * @param other an instance of Var-class or its subclasses, which is to be added
     * @return a result of addition
     */
    @Override
    public Var add(Var other) throws CalcException {
        return other.add(this);
    }

    /**
     * an overloaded method that performs a mathematical operation of "addition" between scalar expressions
     * and a vector expression
     *
     * @param other a scalar expression, which is to be added to "this" vector expression
     * @return a redirect to a method of a scalar expression with the same nature
     */
    public Var add(Scalar other) {
        return other.add(this);
    }

    /**
     * an overloaded method that performs a mathematical operation of "addition" between two vector expression
     *
     * @param other a vector expression, which is to be added to "this" vector expression
     * @return a result of addition, i.e. a new vector expression
     */
    public Var add(Vector other) throws CalcException {
        if (this.value.length == other.value.length) {
            double[] result = new double[this.value.length];
            for (int i = 0; i < value.length; i++) {
                result[i] = this.value[i] + other.value[i];
            }
            return new Vector(result);
        }
        return super.add(other);
    }

    /*
     * a stab-method, because "Matrix + Vector" is impossible
     * @param other an instance of Var-class or its subclasses, which is to be added
     * @return a redirect to an information statement
     */
    public Var add(Matrix other) throws CalcException {
        return super.add(other);
    }

    /**
     * an overridden from a base class "Var" method that determines a type of a class from its argument
     * and depending on the type performs a mathematical operation of "subtraction"
     *
     * @param other an instance of Var-class or its subclasses, which is to be subtracted
     * @return a result of subtraction
     */
    @Override
    public Var sub(Var other) throws CalcException {
        return other.sub(this);
    }

    /**
     * an overloaded method that performs a mathematical operation of "subtraction" between a scalar expression and
     * a vector expression
     *
     * @param other a scalar expression that is a minuend, where "this" vector is a deduction
     * @return a result of subtraction, i.e. a vector expression
     */
    public Var sub(Scalar other) throws CalcException {
        return new Scalar(-1).mul(this).add(other);
    }

    /**
     * an overloaded method that performs a mathematical operation of "subtraction" between two vector expressions
     *
     * @param other a vector expression that is a minuend, where "this" vector is a deduction
     * @return a result of subtraction, i.e. a new vector expression
     */
    public Var sub(Vector other) throws CalcException {
        if (this.value.length == other.value.length) {
            double[] result = new double[this.value.length];
            for (int i = 0; i < value.length; i++) {
                result[i] = other.value[i] - this.value[i];
            }
            return new Vector(result);
        }
        return super.sub(other);
    }

    /*
     * a stab method, because "Matrix - Vector" is impossible
     * @param other an instance of Var-class or its subclasses, which is to be subtracted
     * @return a redirect to a an information statement
     */
    public Var sub(Matrix other) throws CalcException {
        return super.sub(other);
    }

    /**
     * an overridden from a base class "Var" method that determines a type of a class from its argument
     * and depending on the type performs a mathematical operation of "multiplication"
     *
     * @param other an instance of Var-class or its subclasses, which is a multiplier
     * @return a result of multiplication
     */
    @Override
    public Var mul(Var other) throws CalcException {
        return other.mul(this);
    }

    /**
     * an overloaded method that performs a mathematical operation of "subtraction" between "this" vector expression
     * and a scalar expression
     *
     * @param other a scalar expression that is a multiplicand, where "this" vector is a multiplier;
     * @return a redirect to a method of a vector expression with the same nature
     */
    public Var mul(Scalar other) {
        return other.mul(this);
    }

    /**
     * an overloaded method that performs a mathematical operation of "multiplication" between two vector expressions
     *
     * @param other a vector expression that is a multiplicand, where "this" vector is a multiplier;
     * @return a result of multiplication, i.e. a new scalar expression
     */
    public Var mul(Vector other) throws CalcException {
        if (this.value.length == other.value.length) {
            double result = 0;
            for (int i = 0; i < this.value.length; i++) {
                result = result + this.value[i] * other.value[i];
            }
            return new Scalar(result);
        }
        return super.mul(other);
    }

    /**
     * an overloaded method that performs a mathematical operation of "multiplication" between a matrix expression
     * and a vector expression
     *
     * @param other a matrix expression that is a multiplicand, where this "vector" is a multiplier
     * @return a result of multiplication, i.e. a new vector expression
     */
    public Var mul(Matrix other) throws CalcException {
        double[] vector = this.value;
        double[][] matrix = other.getValue();
        if (matrix[0].length == vector.length) {
            double[] result = new double[matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    result[i] = result[i] + matrix[i][j] * vector[j];
                }
            }
            return new Vector(result);
        }
        return super.mul(other);
    }

    /**
     * an overridden from a base class "Var" method that determines a type of a class from its argument
     * and depending on the type performs a mathematical operation of "division"
     *
     * @param other an instance of Var-class or its subclasses, which is divisor
     * @return a result of division
     */
    @Override
    public Var div(Var other) throws CalcException {
        return other.div(this);
    }

    /*
    a stab-method
     */
    public Var div(Scalar other) throws CalcException {
        return super.div(other);
    }

    /*
    a stab-method
     */
    public Var div(Vector other) throws CalcException {
        return super.div(other);
    }

    /*
    a stab-method
     */
    public Var div(Matrix other) throws CalcException {
        return super.div(other);
    }

    /**
     * a method that fills a value of "this" vector expression from a String object entered in a constructor
     * Vector(String strVector)
     *
     * @param matcher a matcher, which finds coincidences assigned to a value of "this" vector expression
     * @param counter a dimension of a vector
     */
    private void valueFiller(Matcher matcher, int counter) {
        value = new double[counter];
        int indexValueArray = 0;
        while (matcher.find()) {
            value[indexValueArray++] = Double.parseDouble(matcher.group());
        }
        matcher.reset();
    }

    /**
     * a method that counts a length of "this" vector expression
     *
     * @param matcher a matcher that finds coincidences assigned to a value of "this" vector expressions
     * @return a length of "this" vector expression
     */
    private int counterOfValueLength(Matcher matcher) {
        if (!matcher.find()) {
            return 0;
        }
        matcher.reset();
        int counter = 0;
        while (matcher.find()) {
            counter++;
        }
        matcher.reset();
        return counter;

    }

    public static void main(String[] args) {
        Vector vectorConArr = new Vector(new double[]{1, 2, 4});
        System.out.println(vectorConArr.toString());
        Vector vectorConVec = new Vector(vectorConArr);
        System.out.println(vectorConVec.toString());
        Vector vectorConStr = new Vector("{1, 2, 4}");
        System.out.println(vectorConStr.toString());
    }
}
