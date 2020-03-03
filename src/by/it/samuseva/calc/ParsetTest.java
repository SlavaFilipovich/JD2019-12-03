package by.it.samuseva.calc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParsetTest {
    @Before
    public void start() throws Exception{
        System.out.println("-------------------------------------------");
        System.out.println("Start test.");
    }

    @After
    public void stop() throws Exception{
        System.out.println("Stop test.");
        System.out.println("-------------------------------------------");
    }

    //------------Scalar
    @Test
    public void calcAddScalar() throws CalcException {
        Parset parser=new Parset();
        String expression = "A=2+5.3";
        System.out.println("Expression "+ expression);
        double expected = 7.3;
        Scalar scalar = (Scalar) parser.calc(expression);
        double actual = Double.parseDouble(scalar.toString());
        assertEquals("Incorrect "+expression,expected,actual,1e-5);
        System.out.printf("Expected : %3s \nActual   : %3s \n", expected, actual);
    }

    @Test
    public void calcMulScalar() throws CalcException {
        Parset parser=new Parset();
        String expression = "B=A*3.5";
        System.out.println("Expression "+ expression);
        double expected = 25.55;
        Scalar scalar = (Scalar) parser.calc(expression);
        double actual = Double.parseDouble(scalar.toString());
        assertEquals("Incorrect "+expression,expected,actual,1e-5);
        System.out.printf("Expected : %s \nActual   : %s \n", expected, actual);
    }

    @Test
    public void calcAddMulScalar() throws CalcException {
        Parset parser=new Parset();
        VarFile.load();
        String expression = "B=B+0.11*-5";
        System.out.println("Expression "+ expression);
        double expected = 25;
        Scalar scalar = (Scalar) parser.calc(expression);
        double actual = Double.parseDouble(scalar.toString());
        assertEquals("Incorrect "+expression,expected,actual,1e-5);
        System.out.printf("Expected : %s \nActual   : %s \n", expected, actual);
    }

    @Test
    public void calcSubDivScalar() throws CalcException {
        VarFile.load();
        Parset parser=new Parset();
        String expression = "B2=A/2-1";
        System.out.println("Expression "+ expression);
        double expected = 2.65;
        Scalar scalar = (Scalar) parser.calc(expression);
        double actual = Double.parseDouble(scalar.toString());
        assertEquals("Incorrect "+expression,expected,actual,1e-5);
        System.out.printf("Expected : %s \nActual   : %s \n", expected, actual);
    }
    //------------Скобки
    @Test
    public void calcParenthesesScalar() throws CalcException {
        Parset parser=new Parset();
        VarFile.load();
        String expression = "C=B+(A*2)";
        System.out.println("Expression "+ expression);
        double expected = 40.15;
        Scalar scalar = (Scalar) parser.calc(expression);
        double actual = Double.parseDouble(scalar.toString());
        assertEquals("Incorrect "+expression,expected,actual,1e-5);
        System.out.printf("Expected : %s \nActual   : %s \n", expected, actual);
    }
    @Test
    public void calcParentheses() throws CalcException {
        Parset parser=new Parset();
        VarFile.load();
        String expression = "D=((C-0.15)-20)/(7-5)";
        System.out.println("Expression "+ expression);
        double expected = 10.0;
        Scalar scalar = (Scalar) parser.calc(expression);
        double actual = Double.parseDouble(scalar.toString());
        assertEquals("Incorrect "+expression,expected,actual,1e-5);
        System.out.printf("Expected : %s \nActual   : %s \n", expected, actual);
    }

    //------------Vector
    @Test
    public void calcAddVectorToScalar() throws CalcException {
        Parset parser=new Parset();
        String expression = "V=2+{1,-6}";
        System.out.println("Expression "+ expression);
        double[] expected = new double[]{3,-4};
        Vector vector = (Vector) parser.calc(expression);
        double[] actual = vector.getValue();
        assertArrayEquals(expected, actual, 1e-5);
        System.out.printf("Expected : %s \nActual   : %s \n", new Vector(expected).toString(), vector.toString());
    }
    @Test
    public void calcAddVectorToVector() throws CalcException {
        Parset parser=new Parset();
        VarFile.load();
        String expression = "V1={3,4}+{1,6}";
        System.out.println("Expression "+ expression);
        double[] expected = new double[]{4,10};
        Vector vector = (Vector) parser.calc(expression);
        double[] actual = vector.getValue();
        assertArrayEquals(expected, actual, 1e-5);
        System.out.printf("Expected : %s \nActual   : %s \n", new Vector(expected).toString(), vector.toString());
    }

    @Test
    public void calcMulVectorToScalar() throws CalcException {
        Parset parser=new Parset();
        String expression = "V={1,-6}*2";
        System.out.println("Expression "+ expression);
        double[] expected = new double[]{2,-12};
        Vector vector = (Vector) parser.calc(expression);
        double[] actual = vector.getValue();
        assertArrayEquals(expected, actual, 1e-5);
        System.out.printf("Expected : %s \nActual   : %s \n", new Vector(expected).toString(), vector.toString());
    }


}