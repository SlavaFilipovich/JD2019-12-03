package by.it.kuzmichalex.calc_jd02_05;

import org.junit.Test;

public class ParserTestWrongExpressions {

    @Test(expected = CalcException.class)
    public void testWrongNewVarName() throws CalcException {
        Parser parser = new Parser();
        String expression = "end=5";
        parser.calc(expression);
    }

    @Test(expected = CalcException.class)
    public void testUndefinedVar() throws CalcException {
        Parser parser = new Parser();
        String expression = "5+some_very_very_ver_undefined_var_";
        parser.calc(expression);
    }

    @Test(expected = CalcException.class)
    public void testWrongOperation() throws CalcException {
        Parser parser = new Parser();
        String expression = "привет";
        parser.calc(expression);
    }

    @Test(expected = CalcException.class)
    public void testMissingOperand() throws CalcException {
        Parser parser = new Parser();
        String expression = "5+";
        parser.calc(expression);
    }

    @Test(expected = CalcException.class)
    public void testMissingOpenBracket() throws CalcException {
        Parser parser = new Parser();
        String expression = "(2+3)/3)";
        parser.calc(expression);
    }

    @Test(expected = CalcException.class)
    public void testMissingCloseBracket() throws CalcException {
        Parser parser = new Parser();
        String expression = "((2+3)/3";
        parser.calc(expression);
    }

    @Test(expected = CalcException.class)
    public void testEmptyExpression() throws CalcException {
        Parser parser = new Parser();
        String expression = "()";
        parser.calc(expression);
    }
    @Test(expected = CalcException.class)
    public void testWrongVectorDefine() throws CalcException {
        Parser parser = new Parser();
        String expression = "{234324, 34443 ";
        parser.calc(expression);
    }

    @Test(expected = CalcException.class)
    public void testWrongVectorSizeAdd() throws CalcException {
        Parser parser = new Parser();
        String expression = "{1,2,3,4,5}+{1,2,3}";
        parser.calc(expression);
    }

    @Test(expected = CalcException.class)
    public void testWrongVectorSizeMul() throws CalcException {
        Parser parser = new Parser();
        String expression = "{1,2,3,4,5}*{1,2,3}";
        parser.calc(expression);
    }

    @Test(expected = CalcException.class)
    public void testDivisionByZero() throws CalcException {
        Parser parser = new Parser();
        String expression = "123/0";
        parser.calc(expression);
    }

    @Test(expected = CalcException.class)
    public void testDivisionByVector() throws CalcException {
        Parser parser = new Parser();
        String expression = "{1,2,3,4,5}/{1,2,3}";
        parser.calc(expression);
    }

    @Test(expected = CalcException.class)
    public void testDivisionByMatrix() throws CalcException {
        Parser parser = new Parser();
        String expression = "{1,2,3,4,5}/{{1,2,3},{1,2,3}}";
        parser.calc(expression);
    }

    @Test(expected = CalcException.class)
    public void testWrongEquationUsage() throws CalcException {
        Parser parser = new Parser();
        String expression = "1=2+3=5";
        parser.calc(expression);
    }

}