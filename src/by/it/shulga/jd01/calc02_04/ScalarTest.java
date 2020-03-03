package by.it.shulga.jd01.calc02_04;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ScalarTest {

    @Test
    public void testCreateScalar() throws Exception {
        Parser parser = new Parser();
        Scalar scalar = (Scalar) parser.calc("A=-5");
        assertEquals("-5.0", scalar.toString());
    }

    @Test
    public void testAdd() throws Exception {
        Parser parser = new Parser();
        String res = parser.calc("A=10.5+-4+1").toString();
        assertEquals("7.5", res);
        res = parser.calc("A=-3+0+6").toString();
        assertEquals("3.0", res);
        res = parser.calc("A=22.2+11.11+-1").toString();
        assertEquals("32.31", res);
    }

    @Test
    public void testSub() throws Exception {
        Parser parser = new Parser();
        assertEquals("7.0", parser.calc("A=11.5-3.5-2--1").toString());
    }

    @Test
    public void testMul() throws Exception {
        Parser parser = new Parser();
        assertEquals("69.53", parser.calc("A=20.45*3.4").toString());
    }

    @Test
    public void testDiv() throws Exception {
        Parser parser = new Parser();
        assertEquals("1.25", parser.calc("A=38.25/15.3/2").toString());
    }

    @Test(expected = CalcException.class)
    public void testDivByZero() throws CalcException {
        new Parser().calc("5/0");
        fail();
    }

    @Test
    public void testBigTask() throws CalcException {
        assertEquals("27.0", new Parser().calc("A=3*(1+2*(8-4))").toString());
    }
}