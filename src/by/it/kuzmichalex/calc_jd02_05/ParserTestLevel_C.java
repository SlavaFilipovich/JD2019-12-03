package by.it.kuzmichalex.calc_jd02_05;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParserTestLevel_C {

    @Test
    public void testMatrixDefineVar() throws CalcException {
        Parser parser = new Parser();
        String expression = "a={{1,2,3},{4,5,6},{7,8,9}}";
        String expected = "{{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}, {7.0, 8.0, 9.0}}";
        Matrix matrix = (Matrix) parser.calc(expression);
        String actual = matrix.toString();
        assertEquals("Incorrect " + expression, expected, actual);

        expression = "a";
        expected = "{{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}, {7.0, 8.0, 9.0}}";
        Var result =  parser.calc(expression);
        actual=result.toString();
        assertEquals("Incorrect " + expression, expected, actual);
    }

    @Test
    public void testMatrixAdd() throws CalcException {
        Parser parser = new Parser();
        String expression = "a={{1,2,3},{4,5,6},{7,8,9}} + 10";
        String expected = "{{11.0, 12.0, 13.0}, {14.0, 15.0, 16.0}, {17.0, 18.0, 19.0}}";
        Matrix matrix = (Matrix) parser.calc(expression);
        String actual = matrix.toString();
        assertEquals("Incorrect " + expression, expected, actual);

        expression = "a=a+a";
        expected = "{{22.0, 24.0, 26.0}, {28.0, 30.0, 32.0}, {34.0, 36.0, 38.0}}";
        matrix = (Matrix)parser.calc(expression);
        actual = matrix.toString();
        assertEquals("Incorrect " + expression, expected, actual);
    }

    @Test
    public void testMatrixSub() throws CalcException {
        Parser parser = new Parser();
        String expression = "a={{1,2,3},{4,5,6},{7,8,9}}";
        parser.calc(expression);
        expression = "b=a-10";
        String expected = "{{-9.0, -8.0, -7.0}, {-6.0, -5.0, -4.0}, {-3.0, -2.0, -1.0}}";
        parser.calc(expression);
        Matrix matrix = (Matrix) parser.calc(expression);
        String actual = matrix.toString();
        assertEquals("Incorrect " + expression, expected, actual);

        expression="a-b";
        expected = "{{10.0, 10.0, 10.0}, {10.0, 10.0, 10.0}, {10.0, 10.0, 10.0}}";
        parser.calc(expression);
        matrix = (Matrix) parser.calc(expression);
        actual = matrix.toString();
        assertEquals("Incorrect " + expression, expected, actual);

    }

    @Test
    public void testMatrixMul() throws CalcException {
        Parser parser = new Parser();
        String expression = "a={{1,2,3},{4,5,6},{7,8,9}}";
        parser.calc(expression);
        expression = "b=a*10";
        String expected = "{{10.0, 20.0, 30.0}, {40.0, 50.0, 60.0}, {70.0, 80.0, 90.0}}";
        parser.calc(expression);
        Matrix matrix = (Matrix) parser.calc(expression);
        String actual = matrix.toString();
        assertEquals("Incorrect " + expression, expected, actual);

        expression="a*b";
        expected = "{{300.0, 360.0, 420.0}, {660.0, 810.0, 960.0}, {1020.0, 1260.0, 1500.0}}";
        parser.calc(expression);
        matrix = (Matrix) parser.calc(expression);
        actual = matrix.toString();
        assertEquals("Incorrect " + expression, expected, actual);

        expression="{{1,2,3},{4,5,6},{7,8,9}} * {11,22,33}";
        expected = "{154.0, 352.0, 550.0}";
        parser.calc(expression);
        Vector vector = (Vector) parser.calc(expression);
        actual = vector.toString();
        assertEquals("Incorrect " + expression, expected, actual);


    }

    @Test
    public void testMatrixDiv() throws CalcException {
        Parser parser = new Parser();
        String expression = "a={{10.0, 20.0, 30.0}, {40.0, 50.0, 60.0}, {70.0, 80.0, 90.0}} / 2";
        String expected = "{{5.0, 10.0, 15.0}, {20.0, 25.0, 30.0}, {35.0, 40.0, 45.0}}";
        Matrix matrix = (Matrix) parser.calc(expression);
        String actual = matrix.toString();
        assertEquals("Incorrect " + expression, expected, actual);
    }



}