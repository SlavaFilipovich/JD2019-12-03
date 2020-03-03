package by.it.cherkas.calc;

import java.util.Arrays;

class Vector extends Var {

        private double[] value;



         Vector(double[] value) {
                this.value = new double[value.length];
                System.arraycopy(value, 0, this.value,0,value.length);

        }
        Vector(Vector vector){
                 this.value = new double[vector.value.length];
                 System.arraycopy(vector.value, 0, this.value, 0, vector.value.length);
        }

        Vector(String strVector){
                 strVector = strVector.replaceAll(" ", "");
                 strVector = strVector.replaceAll("[{}]","");
                 strVector = strVector.replaceAll(",", " ");
                 String[] stringValue = strVector.split(" ");
                 value=new double[stringValue.length];
            for (int i = 0; i <stringValue.length ; i++) {
                value[i]=Double.parseDouble(stringValue[i]);

            }
                }
                public double[] getValue(){
             return value;

        }


        @Override
        public String toString() {
        StringBuilder sb=new StringBuilder("{");
            for (int i = 0; i <value.length ; i++) {
                if (i !=value.length -1) {
                    sb.append(value[i]).append(", ");
                }
                else {
                    sb.append(value[i]).append("}");
                }
            }
            return sb.toString();

        }

        @Override
    public Var add(Var other) throws CalcException {
             return other.add(this);
        }



    @Override
    public Var add(Scalar other) {
             double[] out = new  double[this.value.length];
        for (int i = 0; i < out.length ; i++) {
            out[i] = this.value[i] + other.getValue();
        }
        return new Vector(out);
    }


    @Override
    public Var add(Vector other) throws CalcException {
             if (other.value.length != this.value.length) throw new CalcException("Разная длина векторов");
             double[] out = new double[this.value.length];
        for (int i = 0; i < out.length ; i++) {
            out[i] = this.value[i] + other.value[i];
        }
        return new Vector(out);
    }

    @Override
    public Var sub(Var other) throws CalcException {
             return other.sub(this);
    }


    @Override
    public Var sub(Scalar other) {
        double[] out = new  double[this.value.length];
        for (int i = 0; i < out.length ; i++) {
            out[i] = this.value[i] - other.getValue();
        }
        return new Vector(out);
    }


    @Override
    public Var sub(Vector other) throws CalcException {
        if (other.value.length != this.value.length) throw new CalcException("Разная длина векторов");
        double[] out = new double[this.value.length];
        for (int i = 0; i < out.length ; i++) {
            out[i] = this.value[i] - other.value[i];
        }
        return new Vector(out);
    }


    @Override
    public Var mul(Var other) throws CalcException {
        return other.mul(this);
    }

    @Override
    public Var mul(Scalar other) throws CalcException{
        double[] out = new  double[this.value.length];
        for (int i = 0; i < out.length ; i++) {
            out[i] = this.value[i] * other.getValue();
        }
        return new Vector(out);
    }

    @Override
    public Var mul(Vector other) throws CalcException {
             if (other.value.length != this.value.length) throw new CalcException("Разная длин авекторов");
             double out = 0;
        for (int i = 0; i < this.value.length ; i++) {
            out += this.value[i] * other.value[i];
        }
        return new Scalar(out);
    }

    @Override
    public Var div(Var other) throws CalcException {
        return other.div(this);
    }


    @Override
    public Var div(Scalar other) throws CalcException {
         if (other.getValue()==0){
             throw new CalcException("Деление на 0");
         }
         double[] out = new double[this.value.length];
        for (int i = 0; i < out.length ; i++) {
            out[i] = this.value[i] / other.getValue();
        }
        return new Vector(out);
         }
}











