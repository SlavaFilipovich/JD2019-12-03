package by.it.potapovich.calc2_4;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Parser {

    private static final Map<String, Integer> map = new HashMap<>();

    static {
        map.put("=", 0);
        map.put("+", 1);
        map.put("-", 1);
        map.put("*", 2);
        map.put("/", 2);
    }

    private int getIndex(List<String> operations) {
        int index = -1;
        int currentPriority = -1;
        for (int i = 0; i < operations.size(); i++) {
            String op = operations.get(i);
            if (map.get(op) > currentPriority) {
                index = i;
                currentPriority = map.get(op);
            }
        }
        return index;
    }

    private Var oneOperation(String strLeft, String operation, String strRight) throws CalcException {
        Var right = Var.createVar(strRight);
        if (operation.equals("=")) {
            Var.save(strLeft, right);
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
        throw new CalcException("Код некорректный");


    }

    Var calc(String expression) throws CalcException {
      Pattern pattern = Pattern.compile("[(]([^()]+)[)]");
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
           String brackets = calc(matcher.group(1)).toString();
            StringBuilder sb = new StringBuilder(expression);
           expression = sb.replace(matcher.start(), matcher.end(), brackets).toString();
            expression = expression.replaceAll("\\s+","");
           matcher = pattern.matcher(expression);

   }


            // String[] vector = expression.split("\\(\\)");

            // boolean isBeforeBracket = true;

            // for (int i = 0; i < vector.length; i++) {

            //    if (isBeforeBracket){


            //      }
            // }
            //String newExpression =

            // List<String> vectorOperands=new ArrayList<>(Arrays.asList(vector));
          //  expression = expression.replace("\\s+", "");

             expression = expression.replaceAll("\\s+","");
            String[] parts = expression.split(Patterns.OPERATION);
            List<String> operands = new ArrayList<>(Arrays.asList(parts));
            List<String> operations = new ArrayList<>();
            matcher = Pattern.compile(Patterns.OPERATION).matcher(expression);
            while (matcher.find()) {
                String operation = matcher.group();
                operations.add(operation);
            }

            while (operations.size() > 0) {
                int i = getIndex(operations);
                String operation = operations.remove(i);
                String left = operands.remove(i);
                String right = operands.remove(i);
                Var result = oneOperation(left, operation, right);
                operands.add(i, result.toString());


            }

        return Var.createVar(operands.get(0));
    }
}

