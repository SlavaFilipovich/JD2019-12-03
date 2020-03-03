package by.it.lozouski.calc;

import org.junit.Test;

import static org.junit.Assert.*;

public class My_Test_Calc_Parser_en_US_Lang {
    private Parser testParser = new Parser();


    @Test(timeout = 5000)
    public void testCreateScalar() {
        try {
            Var resultActual1 = testParser.calculate("s=5.25");
            Var resultActual2 = testParser.calculate("D=2.0");
            Var resultActual3 = testParser.calculate("s2=-10.4");
            String expected1 = "5.25";
            String expected2 = "2.0";
            String expected3 = "-10.4";
            assertEquals("Wrong result !", expected1, resultActual1.toString());
            System.out.println("Variable creation: " + "s=5.25" + " is successful.\n" + "Result:" + resultActual1.toString());
            assertEquals("Wrong result !", expected2, resultActual2.toString());
            System.out.println("Variable creation: " + "s1=2.0" + " is successful.\n" + "Result:" + resultActual2.toString());
            assertEquals("Wrong result !", expected3, resultActual3.toString());
            System.out.println("Variable creation: " + "s2=-10.4" + " is successful.\n" + "Result:" + resultActual3.toString());
        } catch (CalcException e) {
            System.out.println("Test FAILED!");
            e.printStackTrace();
        }
    }

    @Test(timeout = 5000)
    public void testCreateVector() {
        try {
            Var resultActual4 = testParser.calculate("v={1, 2, 3}");
            Var resultActual5 = testParser.calculate("v1={4, -5, 6, 0}");
            Var resultActual6 = testParser.calculate("v2={-2, 10, 4, 225, -5}");
            String expected4 = "{1.0, 2.0, 3.0}";
            String expected5 = "{4.0, -5.0, 6.0, 0.0}";
            String expected6 = "{-2.0, 10.0, 4.0, 225.0, -5.0}";
            assertEquals("Wrong result !", expected4, resultActual4.toString());
            System.out.println("Variable creation: " + "v={1, 2, 3}" + " is successful.\n" + "Result:" + resultActual4.toString());
            assertEquals("Wrong result !", expected5, resultActual5.toString());
            System.out.println("Variable creation: " + "v1={4, -5, 6, 0}" + " is successful.\n" + "Result:" + resultActual5.toString());
            assertEquals("Wrong result !", expected6, resultActual6.toString());
            System.out.println("Variable creation: " + "v2={-2, 10, 4, 225, -5}" + " is successful.\n" + "Result:" + resultActual6.toString());
        } catch (CalcException e) {
            System.out.println("Test FAILED!");
            e.printStackTrace();
        }
    }

    @Test(timeout = 5000)
    public void testCreateMatrix() {
        try {
            Var resultActual7 = testParser.calculate("m={{1, 2, 3}, {4, 5, 6}}");
            Var resultActual8 = testParser.calculate("m1={{4, -5, 6, 0}, {2, 3, -4, 5}}");
            Var resultActual9 = testParser.calculate("m2={{-2, 10, 4, 225, -5}, {5, 4, 325, 1, -9}}");
            String expected7 = "{{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}}";
            String expected8 = "{{4.0, -5.0, 6.0, 0.0}, {2.0, 3.0, -4.0, 5.0}}";
            String expected9 = "{{-2.0, 10.0, 4.0, 225.0, -5.0}, {5.0, 4.0, 325.0, 1.0, -9.0}}";
            assertEquals("Wrong result !", expected7, resultActual7.toString());
            System.out.println("Variable creation: " + "m={{1, 2, 3}, {4, 5, 6}}" + " is successful.\n" + "Result:" + resultActual7.toString());
            assertEquals("Wrong result !", expected8, resultActual8.toString());
            System.out.println("Variable creation: " + "m1={{4, -5, 6, 0}, {2, 3, -4, 5}}" + " is successful.\n" + "Result:" + resultActual8.toString());
            assertEquals("Wrong result !", expected9, resultActual9.toString());
            System.out.println("Variable creation: " + "m2={{-2, 10, 4, 225, -5}, {5, 4, 325, 1, -9}}" + " is successful.\n" + "Result:" + resultActual9.toString());
        } catch (CalcException e) {
            System.out.println("Test FAILED!");
            e.printStackTrace();
        }
    }

    @Test(timeout = 5000)
    public void testScalarAddScalar() throws CalcException {
        String expression = "22.45+77.55";
        double expected = 100.00;
        double actual = Double.parseDouble(testParser.calculate(expression).toString());
        assertEquals("Incorrect calculation " + expression, expected, actual, 0.005);
        System.out.println("TEST: " + expression + "   Result: " + actual + "   OK!");
    }

    @Test(timeout = 5000)
    public void testScalarSubScalar() throws CalcException {
        String expression = "77.55-22.45";
        double expected = 55.099;
        double actual = Double.parseDouble(testParser.calculate(expression).toString());
        assertEquals("Incorrect calculation " + expression, expected, actual, 0.005);
        System.out.println("TEST: " + expression + "   Result: " + actual + "   OK!");
    }

    @Test(timeout = 5000)
    public void testScalarMulScalar() throws CalcException {
        String expression = "77.55*22.45";
        double expected = 1740.9975;
        double actual = Double.parseDouble(testParser.calculate(expression).toString());
        assertEquals("Incorrect calculation " + expression, expected, actual, 0.005);
        System.out.println("TEST: " + expression + "   Result: " + actual + "   OK!");
    }

    @Test(timeout = 5000)
    public void testScalarDivScalar() throws CalcException {
        String expression = "77.55/22.45";
        double expected = 3.4543;
        double actual = Double.parseDouble(testParser.calculate(expression).toString());
        assertEquals("Incorrect calculation " + expression, expected, actual, 0.005);
        System.out.println("TEST: " + expression + "   Result: " + actual + "   OK!");
    }

    @Test(expected = CalcException.class, timeout = 5000)
    public void testErrorDivByZeroScalar() throws CalcException {
        String expression = "77.55/0";
        Var scalar = testParser.calculate(expression);
        System.out.println("Test FAILED! Division by zero did not happen.\n"
                + "actual value: " + expression + " Result: " + scalar);
    }

    @Test(timeout = 5000)
    public void testVectorAddVector() throws CalcException {
        String expression = "{-1,2,3}+{4,5,-6}";
        String expected = "{3.0, 7.0, -3.0}";
        String vectorActual = testParser.calculate(expression).toString();
        assertEquals("Incorrect calculation " + expression, expected, vectorActual);
        System.out.println("TEST: " + expression + "   Result: " + vectorActual + "   OK!");
    }

    @Test(timeout = 5000)
    public void testVectorSubVector() throws CalcException {
        String expression = "{-1,2,3}-{4,5,-6}";
        String expected = "{-5.0, -3.0, 9.0}";
        String vectorActual = testParser.calculate(expression).toString();
        assertEquals("Incorrect calculation " + expression, expected, vectorActual);
        System.out.println("TEST: " + expression + "   Result: " + vectorActual + "   OK!");
    }

    @Test(timeout = 5000)
    public void testVectorMulVector() throws CalcException {
        String expression = "{-1,2,3}*{4,5,-6}";
        String expected = "-12.0";
        String vectorActual = testParser.calculate(expression).toString();
        assertEquals("Incorrect calculation " + expression, expected, vectorActual);
        System.out.println("TEST: " + expression + "   Result: " + vectorActual + "   OK!");
    }

    @Test(expected = CalcException.class, timeout = 5000)
    public void testErrorVectorDivVector() throws CalcException {
        String expression = "{-1,2,3}/{4,5,-6}";
        testParser.calculate(expression);
        System.out.println("Test FAILED! Division vector by vector isn't possible.");
    }

    @Test(expected = CalcException.class, timeout = 5000)
    public void testErrorDivByZeroVector() throws CalcException {
        String expression = "{1,2,3}/0";
        Var scalar = testParser.calculate(expression);
        System.out.println("Test FAILED! Division by zero did not happen.\n"
                + "actual value: " + expression + " Result: " + scalar);
    }

    @Test(timeout = 5000)
    public void testMatrixAddMatrix() throws CalcException {
        String expression = "{{-1,2,3},{-5,2,5}}+{{4,5,-6},{10,4,20}}";
        String expected = "{{3.0, 7.0, -3.0}, {5.0, 6.0, 25.0}}";
        String vectorActual = testParser.calculate(expression).toString();
        assertEquals("Incorrect calculation " + expression, expected, vectorActual);
        System.out.println("TEST: " + expression + "   Result: " + vectorActual + "   OK!");
    }

    @Test(timeout = 5000)
    public void testMatrixSubMatrix() throws CalcException {
        String expression = "{{-1,2,3},{-5,2,5}}-{{4,5,-6},{10,4,20}}";
        String expected = "{{-5.0, -3.0, 3.0}, {-15.0, -2.0, 5.0}}";
        String vectorActual = testParser.calculate(expression).toString();
        assertEquals("Incorrect calculation " + expression, expected, vectorActual);
        System.out.println("TEST: " + expression + "   Result: " + vectorActual + "   OK!");
    }

    @Test(expected = CalcException.class, timeout = 5000)
    public void testErrorMatrixMulMatrix() throws CalcException {
        String expression = "{{-1,2,3},{-5,2,5}}*{{4,5,4},{10,4,4}}";
        testParser.calculate(expression);
        System.out.println("Test FAILED! This operation is possible.");
    }

    @Test(expected = CalcException.class, timeout = 5000)
    public void testErrorMatrixDivMatrix() throws CalcException {
        String expression = "{{-1,2,3},{-5,2,5}}/{{4,5,-6},{10,4,20}}";
        testParser.calculate(expression);
        System.out.println("Test FAILED! This operation isn't possible.");
    }

    @Test(expected = CalcException.class, timeout = 5000)
    public void testErrorDivByZeroMatrix() throws CalcException {
        String expression = "{{1,2,3},{4,5,6}}/0";
        Var scalar = testParser.calculate(expression);
        System.out.println("Test FAILED! Division by zero did not happen.\n"
                + "actual value: " + expression + " Result: " + scalar);
    }

    @Test(timeout = 5000)
    public void testTasks_A_B_C() throws CalcException {
        //*********--taskA test--**********
        String expression;
        double expected;
        double actual;
        expression = "A=2+5.3";
        expected = 7.3;
        actual = Double.parseDouble(testParser.calculate(expression).toString());
        assertEquals("Wrong " + expression, expected, actual, 0.005);
        System.out.println("TEST: " + expression + "   RESULT: " + actual + "  OK!");

        expression = "B=A*3.5";
        expected = 25.55;
        actual = Double.parseDouble(testParser.calculate(expression).toString());
        assertEquals("Wrong " + expression, expected, actual, 0.005);
        System.out.println("TEST: " + expression + "   RESULT: " + actual + "  OK!");

        expression = "B1=B+0.11*-5";
        expected = 25.00;
        actual = Double.parseDouble(testParser.calculate(expression).toString());
        assertEquals("Wrong " + expression, expected, actual, 0.005);
        System.out.println("TEST: " + expression + "   RESULT: " + actual + "  OK!");

        expression = "B2=A/2-1";
        expected = 2.65;
        actual = Double.parseDouble(testParser.calculate(expression).toString());
        assertEquals("Wrong " + expression, expected, actual, 0.005);
        System.out.println("TEST: " + expression + "   RESULT: " + actual + "  OK!");

        //*********--taskB test--**********
        expression = "C=B+(A*2)";
        expected = 40.15;
        actual = Double.parseDouble(testParser.calculate(expression).toString());
        assertEquals("Wrong " + expression, expected, actual, 0.005);
        System.out.println("TEST: " + expression + "   RESULT: " + actual + "  OK!");

        expression = "D=((C-0.15)-20)/(7-5)";
        expected = 10.0;
        actual = Double.parseDouble(testParser.calculate(expression).toString());
        assertEquals("Wrong " + expression, expected, actual, 0.005);
        System.out.println("TEST: " + expression + "   RESULT: " + actual + "  OK!");

        String expressionS;
        String expectedString;

        expressionS = "E={2,3}*(D/2)";
        expectedString = "{10.0, 15.0}";
        Var resultS = testParser.calculate(expressionS);
        assertEquals("Wrong ", expectedString, resultS.toString());
        System.out.println("TEST: " + expressionS + "   RESULT: " + resultS.toString() + "  OK!");

        expressionS = "F=(E*D)+({20,43}-({11,23})+{3,3})";
        expectedString = "{112.0, 173.0}";
        Var resultV = testParser.calculate(expressionS);
        assertEquals("Wrong ", expectedString, resultV.toString());
        System.out.println("TEST: " + expressionS + "   RESULT: " + resultV.toString() + "  OK!");
        //*********--taskC test--**********

        expressionS = "G=(({{2,3,4},{2,3,4}}+{{5,5,5},{6,3,2}})-{{12,13,15},{21,14,22}})+(m-m)";
        expectedString = "{{-5.0, -5.0, 12.0}, {-13.0, -8.0, 12.0}}";
        Var resultM = testParser.calculate(expressionS);
        assertEquals("Wrong ", expectedString, resultM.toString());
        System.out.println("TEST: " + expressionS + "   RESULT: " + resultM.toString() + "  OK!");

        expressionS = "H=G*(A+D)";
        expectedString = "{{-86.5, -86.5, 0.0}, {-224.9, -138.4, 0.0}}";
        Var resultM2 = testParser.calculate(expressionS);
        assertEquals("Wrong ", expectedString, resultM2.toString());
        System.out.println("TEST: " + expressionS + "   RESULT: " + resultM2.toString() + "  OK!");

        expressionS = "I={{1,2,3},{4,5,6},{7,8,9}}*{{10.0, 20.0, 30.0}, {40.0, 50.0, 60.0}, {70.0, 80.0, 90.0}}";
        expectedString = "{{300.0, 360.0, 420.0}, {660.0, 810.0, 960.0}, {1020.0, 1260.0, 1500.0}}";
        Var resultM3 = testParser.calculate(expressionS);
        assertEquals("Wrong ", expectedString, resultM3.toString());
        System.out.println("TEST: " + expressionS + "   RESULT: " + resultM3.toString() + "  OK!");

    }



}