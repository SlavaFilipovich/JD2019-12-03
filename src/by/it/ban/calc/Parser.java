package by.it.ban.calc;

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
        throw new CalcException("What?");
    }

    private static boolean check(String s) {
        Deque<Character> deque = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') deque.addFirst(c);
            else if (c == ')' || c == ']' || c == '}') {
                Character cha;
                cha = deque.pollFirst();
                if (cha != null) {
                    if (!(cha == '(' && c == ')' || cha == '{' && c == '}' || cha == '[' && c == ']')) {
                        return false;
                    }
                } else return false;
            }
        }
        return deque.pollFirst() == null;
    }

    Var calc(String expression) throws CalcException {
        expression = expression.replaceAll("\\s+", "");
        if (check(expression)) {
            if (expression.contains("(")) {
                expression=calcComplex(expression);
            }else
                expression=calcSimple(expression);
            return Var.createVar(expression);
        }
        return null;
    }

    private String calcComplex(String expression) throws CalcException {
        Matcher matcher = Pattern.compile(Patterns.BRACKETS).matcher(expression);
        while (matcher.find()) {
            String subString = new String(matcher.group());
            expression=matcher.replaceFirst(calcComplex(subString.substring(1, subString.length()-1)));
            matcher.reset(expression);
        }
        //return calcSimple(expression);
        return calcSimple(expression);
    }

    String calcSimple(String expression) throws CalcException {
        expression = expression.replaceAll("\\s+", "");

        List<String> operands = new ArrayList<>(Arrays.asList(expression.split(Patterns.OPERATION)));
        List<String> operations = new ArrayList<>();
        Matcher matcher = Pattern.compile(Patterns.OPERATION).matcher(expression);
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
            operands.add(i, result.toString().replaceAll("\\s+", ""));
        }
        return operands.get(0);
    }
}

