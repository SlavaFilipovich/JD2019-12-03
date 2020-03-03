package by.it.samuseva.calc;

import java.util.Arrays;

class Vector extends Var {
    private double[] value;

    Vector(double[] value) {
        this.value = Arrays.copyOf(value, value.length);
    }
    Vector(Vector vector) {
        this(vector.value);
    }
    Vector(String str) {
        String[] strings = str.replaceAll(" ", "")
                .replaceAll("[{]", "")
                .replaceAll("[}]", "")
                .split("[,]");
        double[] array = new double[strings.length];
        for (int i = 0; i < array.length; i++) {
                array[i] = Double.parseDouble(strings[i]);
        }
        this.value = array;
    }

    public Vector(Var calc) {
        super();
    }

    //////////   methods for add operation
    @Override
    public Var add(Var rigth) throws CalcException{
         return rigth.add(this);
    }

    @Override
    public Var add(Scalar left) throws CalcException {
        double[] sum = Arrays.copyOf(value,value.length);
        for (int i = 0; i < sum.length; i++) {
            sum[i] += left.getValue();
        }
        return new Vector(sum);

    }

    @Override
    public Var add(Vector left) throws CalcException {
        if (this.value.length!= left.value.length)
            throw new CalcException("Операция сложения не возможна: длина векторов разная!");
        double[] vector = Arrays.copyOf(this.value,this.value.length);
        for (int i = 0; i < vector.length; i++) {
            vector[i] += left.value[i];
        }
        return new Vector(vector);
    }

    //////////   methods for sub operation
    @Override
    public Var sub(Var rigth) throws CalcException{
        return rigth.sub(this);
    }

    @Override
    public Var sub(Scalar left) throws CalcException {
        double[] sub = new double[this.value.length];
        for (int i = 0; i < sub.length; i++) {
            sub[i] = this.value[i]-left.getValue();
        }
        return new Vector(sub);
    }
    @Override
    public Var sub(Vector left) throws CalcException {
        if (this.value.length!= left.value.length)
            throw new CalcException("Операция вычеания не возможна: длина векторов разная!");
        double[] vector = new double[this.value.length];
        for (int i = 0; i < vector.length; i++) {
            vector[i] = this.value[i] - left.value[i];
        }
        return new Vector(vector);

    }
    //////////   methods for mul operation
    @Override
    public Var mul(Var rigth) throws CalcException{
        return rigth.mul(this);
    }
    @Override
    public Var mul(Scalar left) throws CalcException {
        double[] mul = new double[this.value.length];
        for (int i = 0; i < mul.length; i++) {
            mul[i] = this.value[i] * left.getValue();
        }
        return new Vector(mul);
    }
    @Override
    public Var mul(Vector left) throws CalcException {
        double[] vector = new double[this.value.length];
        double mul = 0;
        for (int i = 0; i < vector.length; i++) {
            vector[i] = this.value[i] * left.value[i];
            mul += vector[i];
        }
        return new Scalar(mul);
    }

    //////////   methods for div operation
    @Override
    public Var div(Var rigth) throws CalcException{
        return rigth.div(this);
    }


    public double[] getValue() {
        return value;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("{");
        String delimiter ="";
        for (double element : value) {
            string.append(delimiter).append(element);
            delimiter = ", ";

        }
        string.append("}");
        return string.toString();
    }
}
