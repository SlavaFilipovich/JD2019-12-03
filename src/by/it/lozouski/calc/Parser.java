package by.it.lozouski.calc;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static by.it.lozouski.calc.ConsoleRunner.langService;
class Parser {

    private static final Map<String, Integer> mapOperationPriority = new HashMap<>();
    private static final HashMap<Character, Character> bracketsMap = new HashMap<>();
    private static final Stack<Character> stackBrackets = new Stack<>();

    static {
        bracketsMap.put('(', ')');
        bracketsMap.put('[', ']');
        bracketsMap.put('{', '}');
    }

    static {
        mapOperationPriority.put("=", 0);
        mapOperationPriority.put("+", 1);
        mapOperationPriority.put("-", 1);
        mapOperationPriority.put("*", 2);
        mapOperationPriority.put("/", 2);
    }

    private static boolean bracketsCheck(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);
            if (bracketsMap.containsKey(currentChar)) {
                stackBrackets.push(currentChar);
            } else if (bracketsMap.containsValue(currentChar)) {
                if (!stackBrackets.empty() && bracketsMap.get(stackBrackets.pop()) != currentChar) {
                    break;
                }
            }
        }
        return stackBrackets.empty();
    }

    private int getIndexOperation(List<String> listOperations) {
        int indexOperation = -1;
        int currentPriorityOperation = -1;
        for (int i = 0; i < listOperations.size(); i++) {
            String operation = listOperations.get(i);
            if (mapOperationPriority.get(operation) > currentPriorityOperation) {
                indexOperation = i;
                currentPriorityOperation = mapOperationPriority.get(operation);
            }
        }
        return indexOperation;
    }

    Var calculate(String expression) throws CalcException {
        expression = expression.replaceAll("\\s+", "");
        if (bracketsCheck(expression)) {
            if (expression.contains("(")) {
                expression = calcMultipleExpression(expression);
            } else {
                expression = calcElementaryExpression(expression);
            }
        }
        return Var.createVar(expression);
    }

    private Var calculateOneOperation(String leftOperand, String operationType, String rightOperand) throws CalcException {
        Var rightOper = Var.createVar(rightOperand);
        if (operationType.equals("=")) {
            Var.saveVar(leftOperand, rightOper);
            return rightOper;
        }

        Var leftOper = Var.createVar(leftOperand);
        if (leftOper != null && rightOper != null) {
            switch (operationType) {
                case "+":
                    return leftOper.add(rightOper);
                case "-":
                    return leftOper.sub(rightOper);
                case "*":
                    return leftOper.mul(rightOper);
                case "/":
                    return leftOper.div(rightOper);
            }
        }
        throw new CalcException(langService.get(Error.ERR_INCORRECT_VAR_CREATE));
    }

    private String calcMultipleExpression(String expression) throws CalcException {
        Matcher matcher = Pattern.compile(Patterns.BRACKETS).matcher(expression);
        while (matcher.find()) {
            String oneExp;
            oneExp = matcher.group();
            expression = matcher.replaceFirst(calcMultipleExpression(oneExp.substring(1, oneExp.length() - 1)));
            matcher.reset(expression);
        }
        return calcElementaryExpression(expression);
    }

    String calcElementaryExpression(String expression) throws CalcException {
        expression = expression.replaceAll("\\s+", "");
        List<String> operandsList = new ArrayList<>(Arrays.asList(expression.split(Patterns.OPERATION)));
        List<String> operationsList = new ArrayList<>();

        Matcher matcher = Pattern.compile(Patterns.OPERATION).matcher(expression);

        while (matcher.find()) {
            String operation = matcher.group();
            operationsList.add(operation);
        }

        while (operationsList.size() > 0) {
            try {
                int i = getIndexOperation(operationsList);
                String operation = operationsList.remove(i);
                String leftOperand = operandsList.remove(i);
                String rightOperand = operandsList.remove(i);
                Var resultOperations = calculateOneOperation(leftOperand, operation, rightOperand);
                operandsList.add(i, resultOperations.toString().replaceAll("\\s+", ""));
            }catch (IndexOutOfBoundsException e){
                throw new CalcException(String.format("%s ",langService.get(Error.ERR_INCORRECT_VAR_CREATE)));
            }
        }
        return operandsList.get(0);
    }
}
