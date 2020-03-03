package by.it.borodachev.jd02_04;

import by.it.borodachev.Calc.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test_02_04 {
    @Test
    public void TestA() throws Exception {
        Parser parser = new Parser();
        String expression;
        double actual;
        double expected;
        Scalar scalar;

        expression = "A=2+5.3";

        scalar = (Scalar) parser.calc(expression);
        actual = Double.parseDouble(scalar.toString());
        expected = 7.3;
        assertEquals("Ошибка " + expression, expected, actual, 1e-5);
        expression = "B=A*3.5";
        expected = 25.55;
        scalar = (Scalar) parser.calc(expression);
        actual = Double.parseDouble(scalar.toString());
        assertEquals("Ошибка " + expression, expected, actual, 1e-5);
        expression = "B1=B+0.11*-5";
        expected = 25;
        scalar = (Scalar) parser.calc(expression);
        actual = Double.parseDouble(scalar.toString());
        assertEquals("Ошибка " + expression, expected, actual, 1e-5);
        expression = "B2=A/2-1";
        expected = 2.65;
        scalar = (Scalar) parser.calc(expression);
        actual = Double.parseDouble(scalar.toString());
        assertEquals("Ошибка " + expression, expected, actual, 1e-5);
    }

    @Test
    public void TestB() throws Exception {
        Parser parser = new Parser();
        String expression = "C=B+(A*2)";
        double expected = 40.15;
        Scalar scalar = (Scalar) parser.calc(expression);
        double actual = Double.parseDouble(scalar.toString());
        assertEquals("Ошибка " + expression, expected, actual, 1e-5);

        expression = "D=((C-0.15)-20)/(7-5)";
        expected = 10;
        scalar = (Scalar) parser.calc(expression);
        actual = Double.parseDouble(scalar.toString());
        assertEquals("Ошибка " + expression, expected, actual, 1e-5);

        String strExp;
        Var var;
        String strAct;

        expression = "E={2,3}*(D/2)";
        strExp = "{10.0, 15.0}";
        var = parser.calc(expression);
        strAct = var.toString();
        assertArrayEquals("Ошибка " + expression, strExp.toCharArray(), strAct.toCharArray());

        //для векторов
        expression = "{1,2}+5";
        strExp = "{6.0, 7.0}";
        var = parser.calc(expression);
        strAct = var.toString();
        assertArrayEquals("Ошибка " + expression, strExp.toCharArray(), strAct.toCharArray());

        expression = "{1,2}-1";
        strExp = "{0.0, 1.0}";
        var = parser.calc(expression);
        strAct = var.toString();
        assertArrayEquals("Ошибка " + expression, strExp.toCharArray(), strAct.toCharArray());

        expression = "{1,2}*6";
        strExp = "{6.0, 12.0}";
        var = parser.calc(expression);
        strAct = var.toString();
        assertArrayEquals("Ошибка " + expression, strExp.toCharArray(), strAct.toCharArray());

        expression = "{20,30}/10";
        strExp = "{2.0, 3.0}";
        var = parser.calc(expression);
        strAct = var.toString();
        assertArrayEquals("Ошибка " + expression, strExp.toCharArray(), strAct.toCharArray());

        expression = "{1,2}+{2,3}";
        strExp = "{3.0, 5.0}";
        var = parser.calc(expression);
        strAct = var.toString();
        assertArrayEquals("Ошибка " + expression, strExp.toCharArray(), strAct.toCharArray());

        expression = "{1,2}-{2,3}";
        strExp = "{-1.0, -1.0}";
        var = parser.calc(expression);
        strAct = var.toString();
        assertArrayEquals("Ошибка " + expression, strExp.toCharArray(), strAct.toCharArray());

        expression = "{1,2}*{2,3}";
        strExp = "8.0";
        var = parser.calc(expression);
        strAct = var.toString();
        assertArrayEquals("Ошибка " + expression, strExp.toCharArray(), strAct.toCharArray());
    }

    @Test
    public void TestC() throws Exception {
        Parser parser = new Parser();
        assertEquals("Ошибка: ", "{{3.0, 3.0}, {4.0, 4.0}}", parser.calc("{{1,1},{2,2}}+2").toString());
        assertEquals("Ошибка: ", "{{-1.0, -1.0}, {0.0, 0.0}}", parser.calc("{{1,1},{2,2}}-2").toString());
        assertEquals("Ошибка: ", "{{2.0, 2.0}, {4.0, 4.0}}", parser.calc("{{1,1},{2,2}}*2").toString());
        assertEquals("Ошибка: ", "{{0.5, 0.5}, {1.0, 1.0}}", parser.calc("{{1,1},{2,2}}/2").toString());
        assertEquals("Ошибка: ", "{{2.0, 2.0}, {4.0, 4.0}}", parser.calc("{{1,1},{2,2}}+{{1,1},{2,2}}").toString());
        assertEquals("Ошибка: ", "{4.0, 8.0}", parser.calc("{{1,1},{2,2}}*{2,2}").toString());


        Scalar testVarS = new Scalar("44");
        assertEquals("Ошибка: ", "44.0", testVarS.toString());
        Vector testVarV = new Vector("{2,4,1}");
        assertEquals("Ошибка: ", "{2.0, 4.0, 1.0}", testVarV.toString());
        Matrix testVarM = new Matrix("{{2,4,1},{3,1,1}}");
        assertEquals("Ошибка: ", "{{2.0, 4.0, 1.0}, {3.0, 1.0, 1.0}}", testVarM.toString());
    }
}
