package by.it.cherkas.calc;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static Map<String, Integer> priorityMap = new HashMap<>();

    static {
        priorityMap.put("=", 0);
        priorityMap.put("+", 1);
        priorityMap.put("-", 1);
        priorityMap.put("*", 2);
        priorityMap.put("/", 2);
    }

    private int findHighesPriority(List<String> listMathSigns) {
        int posHighesPriority = -1;
        int highesPriority = -1;
        for (int i = 0; i < listMathSigns.size(); i++) {
            Integer thisPriority = priorityMap.get(listMathSigns.get(i));
            if (thisPriority > highesPriority) {
                posHighesPriority = i;
                highesPriority = thisPriority;
            }
        }
        return posHighesPriority;
    }

    private Var singleOperation( String left, String sing, String right) throws CalcException{
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
                    first.sub(second);
                case "*":
                    first.mul(second);
                case "/":
                    first.div(second);
            }
        }
        throw new CalcException("Ошибка ввода");
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
            int posHighesPriority = findHighesPriority(mathSigns);
            String sign = mathSigns.remove(posHighesPriority);
            String left = numbers.remove(posHighesPriority);
            String right = numbers.remove(posHighesPriority);
            Var var = singleOperation(left, sign, right);
            numbers.add(posHighesPriority, var.toString());
        }
        return Var.newVar(numbers.get(0));
    }



}
