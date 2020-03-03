package by.it.tarasevich.calc;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final Map<String,Integer> map=new HashMap<>();
    static {
        map.put("=",0);
        map.put("+",1);
        map.put("-",1);
        map.put("*",2);
        map.put("/",2);
    }


    private int getIndex(List<String> operations) {
        int index=-1;
        int currentPriority=-1;
        for (int i = 0; i < operations.size(); i++) {
            String op = operations.get(i);
            if (map.get(op)>currentPriority){
                index=i;
                currentPriority=map.get(op);
            }
        }
        return index;
    }

    private Var oneOperation(String strLeft, String operation, String strRight) throws CalcException {
        Var right = Var.createVar(strRight);
        if (operation.equals("=")){
            Var.save(strLeft,right);
            return right;
        }

        Var left = Var.createVar(strLeft);
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
        throw new CalcException("What?");


    }

    Var calc(String expression) throws CalcException {
        expression = expression.replace("\\s+", "");
        String[] parts = expression.split(Patterns.OPERATION);
        List<String> operands=new ArrayList<>(Arrays.asList(parts));

        List<String> operations=new ArrayList<>();
        Matcher matcher = Pattern.compile(Patterns.OPERATION).matcher(expression);
        while (matcher.find()) {
            String operation = matcher.group();
            operations.add(operation);
        }

        while (operations.size()>0){
            int i=getIndex(operations);
            String operation = operations.remove(i);
            String left = operands.remove(i);
            String right = operands.remove(i);
            Var result = oneOperation(left, operation, right);
            operands.add(i,result.toString());

        }
        return Var.createVar(operands.get(0));
    }


}
