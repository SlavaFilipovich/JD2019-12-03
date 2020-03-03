package by.it.kuzmichalex.calc_jd02_05;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParserTestLevel_A {

    @Test
    public void testScalarParse() throws CalcException {
        Parser parser = new Parser();
        String expression = "-123.45";
        double expected = -123.45;
        Scalar scalar = (Scalar) parser.calc(expression);
        double actual = Double.parseDouble(scalar.toString());
        assertEquals("Incorrect " + expression, expected, actual, 1e-5);

        expression = "123.45";
        expected = 123.45;
        scalar = (Scalar) parser.calc(expression);
        actual = Double.parseDouble(scalar.toString());
        assertEquals("Incorrect " + expression, expected, actual, 1e-5);
    }
    @Test
    public void testScalarDefineVar() throws CalcException {
        Parser parser = new Parser();

        String expression = "testvar=1234.5";
        double expected = 1234.5;
        double actual;
        Scalar scalar = (Scalar) parser.calc(expression);
        actual = Double.parseDouble(scalar.toString());
        assertEquals("Incorrect " + expression, expected, actual, 1e-5);

        String strExpected="1234.5";
        Var result = parser.calc("testvar");
        String strActual =result.toString();
        assertEquals("Incorrect " + expression, strExpected, strActual);
    }

    @Test
    public void testScalarPlus() throws CalcException {
        Parser parser = new Parser();
        String expression = "123.45+111.11";
        double expected = 234.56;
        Scalar scalar = (Scalar) parser.calc(expression);
        double actual = Double.parseDouble(scalar.toString());
        assertEquals("Incorrect " + expression, expected, actual, 1e-5);
    }

    @Test
    public void testScalarMinus() throws CalcException {
        Parser parser = new Parser();
        String expression = "123.45-111.11";
        double expected = 12.34;
        Scalar scalar = (Scalar) parser.calc(expression);
        double actual = Double.parseDouble(scalar.toString());
        assertEquals("Incorrect " + expression, expected, actual, 1e-5);
    }
    @Test
    public void testScalarMul() throws CalcException {
        Parser parser = new Parser();
        String expression = "1234321*2";
        double expected = 2468642;
        Scalar scalar = (Scalar) parser.calc(expression);
        double actual = Double.parseDouble(scalar.toString());
        assertEquals("Incorrect " + expression, expected, actual, 1e-5);
    }
    @Test
    public void testScalarDiv() throws CalcException {
        Parser parser = new Parser();
        String expression = "144/12";
        double expected = 12;
        Scalar scalar = (Scalar) parser.calc(expression);
        double actual = Double.parseDouble(scalar.toString());
        assertEquals("Incorrect " + expression, expected, actual, 1e-5);
    }



    @Test
    public void testScalarLongExpression() throws CalcException {
        Parser parser = new Parser();
        String expression = "1+1/2+3";
        double expected = 4.5;
        Scalar scalar = (Scalar) parser.calc(expression);
        double actual = Double.parseDouble(scalar.toString());
        assertEquals("Incorrect " + expression, expected, actual, 1e-5);
    }

    @Test
    public void testScalarExpressionWitchVars() throws CalcException {
        Parser parser = new Parser();
        String expression = "a=2+5.3";
        double expected = 7.3;
        Scalar scalar = (Scalar) parser.calc(expression);
        double actual = Double.parseDouble(scalar.toString());
        assertEquals("Incorrect " + expression, expected, actual, 1e-5);

        expression = "b=a*3.5";
        expected = 25.55;
        scalar = (Scalar) parser.calc(expression);
        actual = Double.parseDouble(scalar.toString());
        assertEquals("Incorrect " + expression, expected, actual, 1e-5);

        expression = "b1=b+0.11*-5";
        expected = 25;
        scalar = (Scalar) parser.calc(expression);
        actual = Double.parseDouble(scalar.toString());
        assertEquals("Incorrect " + expression, expected, actual, 1e-5);

        expression = "b2=a/2-1";
        expected = 2.65;
        scalar = (Scalar) parser.calc(expression);
        actual = Double.parseDouble(scalar.toString());
        assertEquals("Incorrect " + expression, expected, actual, 1e-5);
    }
}