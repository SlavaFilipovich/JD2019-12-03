package by.it.popkov.calc;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Parser {

    private static Map<String, Integer> priorityMap = new HashMap<>();

    static {
        priorityMap.put("=", 0);
        priorityMap.put("+", 1);
        priorityMap.put("-", 1);
        priorityMap.put("*", 2);
        priorityMap.put("/", 2);
    }

    private int findHighestPriority(List<String> listMathSigns) {
        int posHighestPriority = -1;
        int highestPriority = -1;
        for (int i = 0; i < listMathSigns.size(); i++) {
            Integer thisPriority = priorityMap.get(listMathSigns.get(i));
            if (thisPriority > highestPriority) {
                posHighestPriority = i;
                highestPriority = thisPriority;
            }
        }
        return posHighestPriority;
    }

    private Var singleOperation(String left, String sing, String right) throws CalcException {
        Var second = Var.newVar(right);
        if (sing.equals("=")) {
            Var.save(left, second);
            return second;
        }
        Var first = Var.newVar(left);
        if (first != null && second != null) {
            switch (sing) {
                case "+":
                    return first.add(second);
                case "-":
                    return first.sub(second);
                case "*":
                    return first.mul(second);
                case "/":
                    return first.div(second);
            }
        }
        throw new CalcException(Error.INPUT_ERROR);
    }

    Var calc(String expression) throws CalcException {
        Log.writeLog(expression);
        expression = expression.replaceAll("\\s+", "");
        List<String> numbers = new ArrayList<>(Arrays.asList(expression.split(Patterns.MATH_SIGN)));
        List<String> mathSigns = new ArrayList<>();
        Matcher m = Pattern.compile(Patterns.MATH_SIGN).matcher(expression);
        while (m.find()) {
            mathSigns.add(m.group());
        }
        while (mathSigns.size() > 0) {
            int posHighestPriority = findHighestPriority(mathSigns);
            String sign = mathSigns.remove(posHighestPriority);
            String left = numbers.remove(posHighestPriority);
            String right = numbers.remove(posHighestPriority);
            Var var = singleOperation(left, sign, right);
            numbers.add(posHighestPriority, var.toString());
        }
        return Var.newVar(numbers.get(0));
    }
}