package by.it.busel.calc02_05;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * a class that parses an expression, which is a String object,
 * whether it contains (a) var expression(s), if contains this class
 * transform the expression into a Var object or its sub classes
 */
class Parser {

    /**
     * a map that contains entries of mathematical operators and their priority,
     * when "0" is the lowest priority value
     */
    private static final Map<String, Integer> operatorPriority = new HashMap<>();

    /*
      A static block which is created in order to fill the "operatorPriority" map with priority values
     */
    static {
        operatorPriorityMapInitializer();
    }

    /**
     * a method which fills the "operatorPriority" map with priority values while class initialization
     */
    private static void operatorPriorityMapInitializer() {
        operatorPriority.put("=", 0);
        operatorPriority.put("+", 1);
        operatorPriority.put("-", 1);
        operatorPriority.put("*", 2);
        operatorPriority.put("/", 2);
    }

    /**
     * a method, which calculates the result of mathematical operations
     * on the basis of the argument "String varExpression",
     * which contains mathematical operators and operands
     * received from console input by the means of a Scanner-object
     *
     * @param varExpression a String-object, which contains
     *                      mathematical operators and operands
     *                      received from console input by the means
     *                      of a Scanner-object
     * @return a result of mathematical operations,
     * i.e. a Scalar / Vector / Matrix expression
     * or assigning of a Var expression
     */
    Var calc(String varExpression) throws CalcException {
        Pattern parenthesesPattern = Pattern.compile("\\(([-+*/0-9A-Za-zА-Яа-яЁё.,{}]+)\\)");
        Matcher parenthesesMatcher = parenthesesPattern.matcher(varExpression);
        while (parenthesesMatcher.find()) {
            String group = parenthesesMatcher.group();
            String strResult = group.substring(1, group.length() - 1);
            Var result = processWithoutParentheses(strResult);
            varExpression = parenthesesMatcher.replaceFirst(result.toString());
//            varExpression = parenthesesMatcher.replaceAll(result.toString());
            parenthesesMatcher.reset(varExpression);
        }
        return processWithoutParentheses(varExpression);
    }

    /**
     * a method that performs mathematical operations
     *
     * @param varExpression a String object on which mathematical operations and other operations
     *                      are performed
     * @return a result of of mathematical operations
     * i.e. a Scalar / Vector / Matrix expression
     * or assigning of a Var expression
     * @throws CalcException in the following cases:
     *                       1) if only a mathematical operator is inputted;
     *                       2) if a wrong Var expression is inputted
     */
    private Var processWithoutParentheses(String varExpression) throws CalcException {
        List<String> operands = new ArrayList<>(Arrays.asList(varExpression.split(Patterns.OPERATION)));
        if (verifyIfFirstCharIsMinusOrPlus(varExpression)
                & ifOneOrMoreOperandsExistOtherwiseThrowCalcException(operands)) operands.set(0, "0");
        List<String> operators = new ArrayList<>();
        fillOperatorsIfTheyExist(varExpression, operators);
        performMathOperationsAccordingToTheirPriority(operands, operators);
        return Var.createVar(operands.get(0));
    }

    /**
     * a method that verifies if a first char of an argument "String varExpression"
     * is a char '-' or '+'
     *
     * @param varExpression a String object
     * @return true if a first char of an argument "String varExpression"
     * is a char '-' or '+'
     */
    private boolean verifyIfFirstCharIsMinusOrPlus(String varExpression) {
        if (varExpression.length() > 1)
            return varExpression.charAt(0) == '-' || varExpression.charAt(0) == '+';
        return false;
    }

    /**
     * a method that checks  one of conditions whether a mathematical operation can be performed, i.e.
     * presence of two mathematical operands
     *
     * @param operands a List which should contain operands
     * @return true if "operands" contains one or more operands
     * @throws CalcException if three are no operands
     */
    private boolean ifOneOrMoreOperandsExistOtherwiseThrowCalcException(List<String> operands) throws CalcException {
        if (operands.size() < 1) {
            throw new CalcException(ResourcesManager.get(Message.ERROR_ONLY_MATH_OPERATOR));
        }
        return true;
    }

    /**
     * a method that finds mathematical operators and adds them to a List "operators"
     *
     * @param varExpression a String that can contain mathematical operators
     * @param operators     a List to which the found mathematical operators are added
     */
    private void fillOperatorsIfTheyExist(String varExpression, List<String> operators) {
        Matcher matcher = Pattern.compile(Patterns.OPERATION).matcher(varExpression);
        while (matcher.find()) {
            String operator = matcher.group();
            operators.add(operator);
        }
    }

    /**
     * a method that find mathematical operator with the highest priority
     * and performs a mathematical operation, repeats this until only one operand is remaining
     *
     * @param operands  a mathematical operands
     * @param operators a mathematical operators
     * @throws CalcException if a mathematical operation can be performed
     */
    private void performMathOperationsAccordingToTheirPriority
    (List<String> operands, List<String> operators) throws CalcException {
        while (operators.size() > 0) {
            int i = getIndex(operators);
            String operator = operators.remove(i);
            String left = operands.remove(i);
            String right = operands.remove(i);
            Var result = oneSeparateOperation(left, operator, right);
            operands.add(i, result.toString());
        }
    }

    /**
     * a method that returns an index of an operator with the highest priority value
     * from "List<String> operators", which contains unknown operators from a console
     *
     * @param operators a List<String>, which contains unknown operators from a console
     * @return an index of an mathematical operator from "operators"
     * with the highest priority value
     */
    private int getIndex(List<String> operators) {
        int index = -1;
        int currentPriority = -1;
        for (int i = 0; i < operators.size(); i++) {
            String operator = operators.get(i);
            if (operatorPriority.get(operator) > currentPriority) {
                index = i;
                currentPriority = operatorPriority.get(operator);
            }
        }
        return index;
    }

    /**
     * a method which calculates either one of supported mathematical operations
     * or assign a value and saves it to "vars.txt"
     *
     * @param strLeft  either the first Var expression (scalar) or a name of a Var expression,
     *                 which is to be assigned
     * @param operator either an mathematical operator or a "=" operator which is to assign
     *                 a Var expression
     * @param strRight a Var expression which is either one to be assigned or the second Var expression
     * @return a Var expression which has been assigned or a result of a mathematical operation
     * @throws CalcException if impossible has come true while calculating
     */
    private Var oneSeparateOperation(String strLeft, String operator, String strRight) throws CalcException {
        if (operator.equals("=")) {
            return assignVar(strLeft, strRight);
        } else {
            return calculateVar(strLeft, operator, strRight);
        }
    }

    /**
     * a method that assigns an inputted Var expression to a name inputted at the same time
     *
     * @param strLeft  a name of a var expression
     * @param strRight a String representation of a Var expression
     * @return a Var object that containing its value
     * @throws CalcException if an incorrect Var expression is inputted
     */
    private Var assignVar(String strLeft, String strRight) throws CalcException {
        Var right = Var.createVar(strRight);
        Storage.putVarToStorage(strLeft, right);
        return right;
    }

    /**
     * a method that returns a result of calculation of two Var expressions
     *
     * @param strLeft  the first mathematical operand
     * @param operator a mathematical operator that determines
     *                 which mathematical operation will be performed
     * @param strRight the second mathematical operand
     * @return a result of calculation
     * @throws CalcException if a mathematical operation can not be performed
     */
    private Var calculateVar(String strLeft, String operator, String strRight) throws CalcException {
        Var right = Var.createVar(strRight);
        Var left = Var.createVar(strLeft);
        switch (operator) {
            case "+":
                return left.add(right);
            case "-":
                return left.sub(right);
            case "*":
                return left.mul(right);
            case "/":
                return left.div(right);
            default:
                throw new CalcException(ResourcesManager.get(Message.ERROR_NO_MATH_OPERATOR_FOUND));
        }
    }
}
