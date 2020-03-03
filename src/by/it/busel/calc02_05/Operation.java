package by.it.busel.calc02_05;

/**
 * an interface which provided a contract of methods, which are to be implemented
 */
public interface Operation {
    /**
     * a contract of a mathematical operation "addition"
     *
     * @param other an instance of Var-class or its subclasses, which is to be added
     * @return a result of addition
     */
    Var add(Var other) throws CalcException;

    Var add(Scalar other) throws CalcException;

    Var add(Vector other) throws CalcException;

    Var add(Matrix other) throws CalcException;

    /**
     * a contract of a mathematical operation "subtraction"
     *
     * @param other an instance of Var-class or its subclasses, which is to be subtracted
     * @return a result of subtraction
     */
    Var sub(Var other) throws CalcException;

    Var sub(Scalar other) throws CalcException;

    Var sub(Vector other) throws CalcException;

    Var sub(Matrix other) throws CalcException;

    /**
     * a contract of a result of subtraction "multiplication"
     *
     * @param other an instance of Var-class or its subclasses, which is a multiplier
     * @return a result of a multiplication
     */
    Var mul(Var other) throws CalcException;

    Var mul(Scalar other) throws CalcException;

    Var mul(Vector other) throws CalcException;

    Var mul(Matrix other) throws CalcException;

    /**
     * a contract of a result of subtraction "division"
     *
     * @param other an instance of Var-class or its subclasses, which is divisor
     * @return a result of division
     */
    Var div(Var other) throws CalcException;

    Var div(Scalar other) throws CalcException;

    Var div(Vector other) throws CalcException;

    Var div(Matrix other) throws CalcException;
}