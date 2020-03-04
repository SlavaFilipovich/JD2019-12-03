package by.it.filipovich.calc;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Parser {

    private static final Map<String, Integer> mapOfPriority = new HashMap<>();
    static {
        mapOfPriority.put("=",0);
        mapOfPriority.put("+",1);
        mapOfPriority.put("-",1);
        mapOfPriority.put("*",2);
        mapOfPriority.put("/",2);
    }

    private int getIndex(List<String> operations) {
        int index=-1;
        int currentPriority=-1;
        for (int i = 0; i < operations.size(); i++) {
            String operate = operations.get(i);
            if (mapOfPriority.get(operate)>currentPriority){
                index=i;
                currentPriority=mapOfPriority.get(operate);
            }
        }
        return index;
    }

    private Var oneOperation(String leftPart, String operation, String rightPart) throws CalcException {
        Var right = Var.createVar(rightPart);
        if (operation.equals("=")) {
            Var.save(leftPart, right);
            return right;
        }
        Var left = Var.createVar(leftPart);
        if (left != null && right != null) {
            switch (operation) {
                case "+":
                    return left.add(right);
                case "-":
                    return left.sub(right);
                case "*":
                    return left.mul(right);
                case "/":
                    return left.div(right);
            }
        }
        Logger.getInstance().logWrite(Messages.NULL_OBJECT);
        throw new CalcException("What's going on???");
    }


    Var calc(String expression) throws CalcException {
        Var result = null;
        expression = expression.replaceAll("\\s","");
        String[] operand = expression.split(Patterns.OPERATIONS);

        List<String> operands = new ArrayList<>(Arrays.asList(operand));
        List<String> operators = new ArrayList<>();
        Matcher matcher = Pattern.compile(Patterns.OPERATIONS).matcher(expression);
       while (matcher.find()){
           String operator = matcher.group();
           operators.add(operator);
       }

        while (operators.size()>0){
            int i=getIndex(operators);
            String operation = operators.remove(i);
            String left = operands.remove(i);
            String right = operands.remove(i);
            result = oneOperation(left, operation, right);
            operands.add(i,result.toString());
        }
        return Var.createVar(operands.get(0));
    }
}
