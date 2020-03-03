package by.it.cherkas.calc;

interface Operation {
    Var add(Var other) throws CalcException;

    Var add(Scalar other) throws CalcException;

    Var add(Vector other) throws CalcException;

    Var sub(Var other) throws CalcException;

    Var sub(Scalar other) throws CalcException;

    Var sub(Vector other) throws CalcException;

    Var mul(Var other) throws CalcException;

    Var mul(Scalar other) throws CalcException;

    Var mul(Vector other) throws CalcException;

    Var div(Var other) throws CalcException;

    Var div(Scalar other) throws CalcException;

    Var div(Vector other) throws CalcException;
}