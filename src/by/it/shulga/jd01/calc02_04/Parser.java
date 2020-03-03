package by.it.shulga.jd01.calc02_04;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Parser {
    private static final Map<String, Integer> SIGN = new HashMap<>();

    static {
        SIGN.put("=", 0);
        SIGN.put("+", 1);
        SIGN.put("-", 1);
        SIGN.put("*", 2);
        SIGN.put("/", 2);
    }

    Var calc(String expression) throws CalcException {
        Pattern pattern = Pattern.compile(Patterns.PARENTHESES);
        Matcher m = pattern.matcher(expression);
        while (m.find()) {
            String res = calc(m.group(1)).toString();
            StringBuilder sb = new StringBuilder(expression);
            expression = sb.replace(m.start(), m.end(), res).toString();
            expression = expression.replaceAll(" ", "").replace("\\s+", "");
            m = pattern.matcher(expression);
        }

        List<String> listOperands = new ArrayList<>(Arrays.asList(expression.split(Patterns.OPERATION)));
        List<String> listOperations = new ArrayList<>();
        Matcher matcher = Pattern.compile(Patterns.OPERATION).matcher(expression);
        while (matcher.find()) {
            listOperations.add(matcher.group());
        }
        if (listOperations.size() == 0) return Var.createVar(expression);
        while (listOperations.size() > 0) {
            int index = getIndex(listOperations);
            String operation = listOperations.remove(index);
            String strLeft = listOperands.remove(index);
            String strRight = listOperands.remove(index);
            Var result = operation(strLeft, operation, strRight);
            listOperands.add(index, result.toString());
        }
        return Var.createVar(listOperands.get(0));
    }

    private Var operation(String strLeft, String operation, String strRight) throws CalcException {
        Var right = Var.createVar(strRight);
        if (operation.equals("=")) {
            Var.saveVar(strLeft, right);
            return right;
        }
        Var left = Var.createVar(strLeft);
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
        throw new CalcException("What are you doing? Write the operation correctly!");
    }

    private int getIndex(List<String> operations) {
        int index = -1;
        int currentPriority = -1;
        for (int i = 0; i < operations.size(); i++) {
            String op = operations.get(i);
            if (SIGN.get(op) > currentPriority) {
                index = i;
                currentPriority = SIGN.get(op);
            }
        }
        return index;
    }
}
