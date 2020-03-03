package by.it.borodachev.Calc;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static by.it.borodachev.Calc.Var.*;

 public class Parser {
    private static final Map<String, Integer> unoCalcOper = new HashMap<>();

    static {
        unoCalcOper.put("=", 0);
        unoCalcOper.put("+", 1);
        unoCalcOper.put("-", 1);
        unoCalcOper.put("*", 2);
        unoCalcOper.put("/", 2);
    }
    private int getIndex(List<String> operations) {
        int index = -1;
        int currentPriority = -1;
        for (int i = 0; i < operations.size(); i++) {
            String op = operations.get(i);
            if (unoCalcOper.get(op) > currentPriority) {
                index = i;
                currentPriority = unoCalcOper.get(op);
            }
        }
        return index;
    }
    private Var UnoOperation(String strLeft, String operation, String strRight) throws CalcException {
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
        throw new CalcException(LanguageManager.get(ErrorMessage.unknown_operation));
    }
    public static boolean check(String s) {
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
    public Var calc(String expression) throws CalcException {
        expression = expression.replaceAll("\\s+", "");
        if (expression.equals("printvar")) {
            printvar();
        } else if (expression.equals("sortvar")) {
            sortvar();
        } else if (expression.equals("ru")) {
            LanguageManager.setLanguage(expression);
            System.out.println(LanguageManager.get("message.calculator_running"));
        } else if (expression.equals("en")) {
            LanguageManager.setLanguage(expression);
            System.out.println(LanguageManager.get("message.calculator_running"));
        } else if (expression.equals("by")) {
            LanguageManager.setLanguage(expression);
            System.out.println(LanguageManager.get("message.calculator_running"));
        }
        else {
                if (check(expression)) {
                    if (expression.contains("(")) {
                        expression = calcComplex(expression);
                    } else
                        expression = calcPrimitive(expression);
                    return Var.createVar(expression);
                }
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
         return calcPrimitive(expression);
    }
    private String calcPrimitive(String expression) throws CalcException {
         expression = expression.replace("\\s+", "");
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
                Var result = UnoOperation(left, operation, right);
                operands.add(i, result.toString().replaceAll("\\s+", ""));
            }
            return operands.get(0);
        }


}
