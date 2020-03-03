package by.it.busel.calc02_05;

/**
 * a scalar expression
 */
class Scalar extends Var {
    /**
     * a value of "this" scalar expression
     */
    private double value;

    /**
     * a method, which is a getter of a value of "this" scalar expression
     *
     * @return a value of a scalar expression
     */
    public double getValue() {
        return value;
    }

    /**
     * a constructor, which argument sets a value of "this" scalar expression
     *
     * @param value a value of a scalar expression
     */
    Scalar(double value) {
        this.value = value;
    }

    /**
     * a constructor, which argument is another scalar expression value of which is to be assigned as a value of "this"
     * scalar expression
     *
     * @param scalar another scalar expression value of which is to be assigned as a value of "this" scalar expression
     */
    Scalar(Scalar scalar) {
        this.value = scalar.value;
    }

    /**
     * a constructor that takes a String object, parses it into a value of "this" scalar expression
     *
     * @param strScalar a String object that contains a scalar expression
     */
    Scalar(String strScalar) {
        this.value = Double.parseDouble(strScalar);
    }

    /**
     * a default constructor
     */
    Scalar() {

    }

    /**
     * an overridden method of the Object. toString() method
     *
     * @return a value of "this" scalar expression, which is to be printer when "this" object is called to be printed
     */
    @Override
    public String toString() {
        return Double.toString(value);
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
     * an overloaded method that performs a mathematical operation of "addition" between two scalar expressions
     *
     * @param other a scalar expression, which is to be added to "this" scalar expression
     * @return a result of addition, i.e. a new scalar expression
     */
    public Var add(Scalar other) {
        double sum = other.value + this.value;
        return new Scalar(sum);
    }

    /**
     * an overloaded method that performs a mathematical operation of "addition" between a vector expression and
     * a scalar expression
     *
     * @param other a vector expression, which is to be added to "this" scalar expression
     * @return a result of addition, i.e. a new vector expression
     */
    public Var add(Vector other) {
        double[] result = other.getValue();
        double filler = this.value;
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i] + filler;
        }
        return new Vector(result);
    }

    /**
     * an overloaded method that performs a mathematical operation of "addition" between a matrix expression and
     * a scalar expression
     *
     * @param other a matrix expression, to which a scalar expression is added
     * @return a result of addition, i.e. a new matrix expression
     */
    public Var add(Matrix other) {
        double[][] result = other.getValue();
        double filler = this.value;
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = result[i][j] + filler;
            }
        }
        return new Matrix(result);
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
     * an overloaded method that performs a mathematical operation of "subtraction" between two scalar expressions
     *
     * @param other a scalar expression that is a minuend, where "this" expression is a deduction;
     * @return a result of subtraction, i.e. a new scalar expression
     */
    public Var sub(Scalar other) {
        double rem = other.value - this.value;
        return new Scalar(rem);
    }

    /**
     * an overloaded method that performs a mathematical operation of "subtraction" between a vector expression
     * and a scalar expression
     *
     * @param other a vector expression that is a minuend, where "this" scalar is a deduction;
     * @return a result of subtraction, i.e. a new vector expression
     */
    public Var sub(Vector other) {
        double[] result = other.getValue();
        double filler = this.value;
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i] - filler;
        }
        return new Vector(result);
    }

    /**
     * an overloaded method that performs a mathematical operation of "subtraction" between a matrix expression
     * and a scalar expression
     *
     * @param other a Matrix expression that is a minuend, where "this" scalar is a deduction
     * @return a result of subtraction, i.e. a new matrix expression
     */
    public Var sub(Matrix other) {
        double[][] result = other.getValue();
        double filler = this.value;
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = result[i][j] - filler;
            }
        }
        return new Matrix(result);
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
     * an overloaded method that performs a mathematical operation of "subtraction" between two scalar expressions
     *
     * @param other a scalar expression that is a multiplicand, where "this" scalar is a multiplier;
     * @return a result of multiplication, i.e. a new scalar expression
     */
    public Var mul(Scalar other) {
        double mul = other.value * this.value;
        return new Scalar(mul);
    }

    /**
     * an overloaded method that performs a mathematical operation of "multiplication" between a vector expression
     * and a scalar expression
     *
     * @param other a vector expression that is a multiplicand, where "this" scalar is a multiplier
     * @return a result of multiplication, i.e. a new vector expression
     */
    public Var mul(Vector other) {
        double[] result = other.getValue();
        double filler = this.value;
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i] * filler;
        }
        return new Vector(result);
    }

    /**
     * an overloaded method that performs a mathematical operation of "multiplication" between "this" matrix expression
     * and a scalar expression
     *
     * @param other a matrix expression that is a multiplicand, where this scalar is a multiplier;
     * @return a result of multiplication, i.e. a new matrix expression
     */
    public Var mul(Matrix other) {
        double[][] result = other.getValue();
        double filler = this.value;
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = result[i][j] * filler;
            }
        }
        return new Matrix(result);
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

    /**
     * an overloaded method that performs a mathematical operation of "division" between two scalar expression
     *
     * @param other a scalar expression that is a dividend, where "this" scalar is a divisor;
     * @return a result of division, i.e. a new scalar expression
     */
    public Var div(Scalar other) throws CalcException {
        if (this.value != 0) {
            double div = other.value / this.value;
            return new Scalar(div);
        } else {
            throw new CalcException(ResourcesManager.get(Message.ERROR_DIVISION_BY_ZERO));
        }
    }

    /**
     * an overloaded method that performs a mathematical operation of "division" between "this" vector expression
     * and a scalar expression
     *
     * @param other a vector expression that is a dividend, where "this" scalar is a divisor;
     * @return a result of division, i.e. a new vector expression
     */
    public Var div(Vector other) throws CalcException {
        double filler = this.value;
        if (filler != 0) {
            double[] result = other.getValue();
            for (int i = 0; i < result.length; i++) {
                result[i] = result[i] / filler;
            }
            return new Vector(result);
        } else {
            throw new CalcException(ResourcesManager.get(Message.ERROR_DIVISION_BY_ZERO));
        }
    }

    /**
     * an overloaded method that performs a mathematical operation of "division" between a matrix expression
     * and a scalar expression
     *
     * @param other a matrix expression that is a dividend, where this scalar is a divisor;
     * @return a result of division, i.e. a new matrix expression
     */
    public Var div(Matrix other) throws CalcException {
        double filler = this.value;
        double[][] matrix = other.getValue();
        if (filler != 0) {
            double[][] result = new double[matrix.length][matrix[0].length];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    result[i][j] = matrix[i][j] / filler;
                }
            }
            return new Matrix(result);
        } else {
            throw new CalcException(ResourcesManager.get(Message.ERROR_DIVISION_BY_ZERO));
        }
    }


}
