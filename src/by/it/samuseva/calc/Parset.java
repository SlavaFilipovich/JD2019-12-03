package by.it.samuseva.calc;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Parset {
    private static final Map<String,Integer> mapPriorety= new HashMap<>();
    static {
        mapPriorety.put("=", 0);
        mapPriorety.put("+", 1);
        mapPriorety.put("-", 1);
        mapPriorety.put("*", 2);
        mapPriorety.put("/", 2);
    }
    private static int getPriorety(List<String> operations){
        int index = -1;
        int countPriorety = -1;
        for (int i = 0; i < operations.size(); i++) {
            String operand = operations.get(i);
            if (mapPriorety.get(operand)>countPriorety) {
                index=i;
                countPriorety = mapPriorety.get(operand);
            }
        }
        return index;
    }

    private Var oneOperation(String strLeft, String operation, String strRight) throws CalcException {
        Var operandTwo = Var.createVar(strRight);
        if (operation.contains("=")){
            Var.saveVar(strLeft, operandTwo);
            return  operandTwo;
        }
        Var operandOne = Var.createVar(strLeft);
        if ((operandOne != null ) && (operandTwo != null)){
            switch (operation){
                case "+": return operandOne.add(operandTwo);
                case "-": return operandOne.sub(operandTwo);
                case "*": return operandOne.mul(operandTwo);
                case "/": return operandOne.div(operandTwo);
            }
        }
        throw new CalcException("What?");
    }




    Var calc (String expression) throws CalcException{
        expression = expression.replace("\\s+", "");




        while (expression.indexOf('(')>0){
            String str1;
            if (expression.indexOf("((")>0) {
                str1 = expression.substring(expression.indexOf("(")+1, expression.indexOf(')')+1);
            } else{
                str1 = expression.substring(expression.indexOf("("), expression.indexOf(')')+1);
            }
            String str = str1.replace("(", "").replace(")", "");
            String[] strs = str.split(Patterns.OPERATION);
            String oper = "";
            if (str.indexOf('+')>0) oper = "+";
            if (str.indexOf('-')>0) oper = "-";
            if (str.indexOf('/')>0) oper = "/";
            if (str.indexOf('*')>0) oper = "*";
            Var rez = oneOperation(strs[0],oper,strs[1]);
            expression = expression.replace(str1,rez.toString());
        }

        String[] parts = expression.split(Patterns.OPERATION);
        List<String> operands=new ArrayList<>(Arrays.asList(parts));

        List<String> operations = new ArrayList<>();
        Pattern pat = Pattern.compile(Patterns.OPERATION);
        Matcher mat = pat.matcher(expression);

        while (mat.find()){
            String operation = mat.group();
            operations.add(operation);
        }
        while (operations.size()>0){
            int i = getPriorety(operations);
            String operation = operations.remove(i);
            String left = operands.remove(i);
            String right = operands.remove(i);
            Var result = oneOperation(left, operation, right);
            operands.add(i,result.toString());
        }
        return Var.createVar(operands.get(0));
    }
}