package by.it.kuzmichalex.calc_jd02_05;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParserTestLevel_B {


    @Test
    public void testVectorDefineVar() throws CalcException {

        Parser parser = new Parser();
        String expression = "{1,2,-3}";
        String expected = "{1.0, 2.0, -3.0}";
        Vector vector = (Vector) parser.calc(expression);
        String actual = vector.toString();
        assertEquals("Incorrect " + expression, expected, actual);

        parser = new Parser();
        expression = "a={1,2,-3}";
        expected = "{1.0, 2.0, -3.0}";
        vector = (Vector) parser.calc(expression);
        actual = vector.toString();
        assertEquals("Incorrect " + expression, expected, actual);

        expression = "a";
        Var result =  parser.calc(expression);
        actual=result.toString();
        assertEquals("Incorrect " + expression, expected, actual);
    }


    @Test
    public void testVectorAdd() throws CalcException {
        Parser parser = new Parser();
        String expression = "{1,2,-3}+1";
        String expected = "{2.0, 3.0, -2.0}";
        Vector vector = (Vector) parser.calc(expression);
        String actual = vector.toString();
        assertEquals("Incorrect " + expression, expected, actual);

        parser = new Parser();
        expression = "a=1+{1,2,-3}";
        expected = "{2.0, 3.0, -2.0}";
        vector = (Vector) parser.calc(expression);
        actual = vector.toString();
        assertEquals("Incorrect " + expression, expected, actual);

        parser = new Parser();
        expression = "a={1,2,-3}+{10,20,30}";
        expected = "{11.0, 22.0, 27.0}";
        vector = (Vector) parser.calc(expression);
        actual = vector.toString();
        assertEquals("Incorrect " + expression, expected, actual);
    }

    @Test
    public void testVectorSub() throws CalcException {
        Parser parser = new Parser();
        String expression = "{1,2,-3}-1";
        String expected = "{0.0, 1.0, -4.0}";
        Vector vector = (Vector) parser.calc(expression);
        String actual = vector.toString();
        assertEquals("Incorrect " + expression, expected, actual);

        parser = new Parser();
        expression = "a={10,20,20}-{1,2,-3}";
        expected = "{9.0, 18.0, 23.0}";
        vector = (Vector) parser.calc(expression);
        actual = vector.toString();
        assertEquals("Incorrect " + expression, expected, actual);
    }

    @Test
    public void testVectorMul() throws CalcException {
        Parser parser = new Parser();
        String expression = "{1,2,-3}*3";
        String expected = "{3.0, 6.0, -9.0}";
        Vector vector = (Vector) parser.calc(expression);
        String actual = vector.toString();
        assertEquals("Incorrect " + expression, expected, actual);

        parser = new Parser();
        expression = "a={7,6,5}*{1,2,-3}";
        expected = "4.0";
        Scalar scalar = (Scalar) parser.calc(expression);
        actual = scalar.toString();
        assertEquals("Incorrect " + expression, expected, actual);
    }

    @Test
    public void testVectorDiv() throws CalcException {
        Parser parser = new Parser();
        String expression = "{3.0, 6.0, -9.0}/3";
        String expected = "{1.0, 2.0, -3.0}";
        Vector vector = (Vector) parser.calc(expression);
        String actual = vector.toString();
        assertEquals("Incorrect " + expression, expected, actual);
    }


    @Test
    public void testExpressionsAndBrackets() throws CalcException {
        Parser parser = new Parser();
        String expression = "((1+1/2)+-0.5)/0.1";
        double expected = 10;
        Scalar scalar = (Scalar) parser.calc(expression);
        double actual = Double.parseDouble(scalar.toString());
        assertEquals("Incorrect " + expression, expected, actual, 1e-5);

        expression = "A=2+5.3";
        parser.calc(expression);
        expression = "B=A*3.5";
        parser.calc(expression);

        /*C = B + (A * 2) (выведет на экран 40.15)*/
        expression = "C = B + (A * 2)";
        expected = 40.15;
        scalar = (Scalar) parser.calc(expression);
        actual = Double.parseDouble(scalar.toString());
        assertEquals("Incorrect " + expression, expected, actual, 1e-5);

        /*D = ((C - 0.15) - 20) / (7 - 5) (выведет на экран 10) */
        expression = "D = ((C - 0.15) - 20) / (7 - 5)";
        expected = 10;
        scalar = (Scalar) parser.calc(expression);
        actual = Double.parseDouble(scalar.toString());
        assertEquals("Incorrect " + expression, expected, actual, 1e-5);

        /*E = {2, 3} * (D / 2) (выведет на экран {10, 15 } )*/
        expression = "E = {2, 3} * (D / 2)";
        String strExpected = "{10.0, 15.0}";
        Vector vector = (Vector) parser.calc(expression);
        String strActual = vector.toString();
        assertEquals("Incorrect " + expression, strExpected, strActual);
    }

}