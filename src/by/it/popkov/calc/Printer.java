package by.it.popkov.calc;

class Printer  {
    public void print (Var calc) throws CalcException {

        if (calc != null){
            Log.writeLog(calc.toString());
            System.out.println(calc.toString());
        }else {
            throw new CalcException(Error.NULL_RESULT);
        }

    }
}
