package by.it.shulga.jd01.calc02_04;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class VectorTest {
    @Test
    public void testCreateVector() throws CalcException {
        Parser parser = new Parser();
        parser.calc("V={-1,2,4.5, -5.6, 7.00}");
        String res = parser.calc("V").toString();
        assertEquals("{-1.0, 2.0, 4.5, -5.6, 7.0}", res);
    }

    @Test
    public void testVectorAddScalar() throws CalcException {
        Parser parser = new Parser();
        String res = parser.calc("A={1,1,4.1,4}+5.1").toString();
        assertEquals("{6.1, 6.1, 9.2, 9.1}", res);
    }

    @Test
    public void testVectorAddVector() throws CalcException {
        Parser parser = new Parser();
        String res = parser.calc("A={1,2,3}+{4,5,6}").toString();
        assertEquals("{5.0, 7.0, 9.0}", res);
    }

    @Test
    public void testVectorSubScalar() throws CalcException {
        Parser parser = new Parser();
        String res = parser.calc("A={1,2,3}-2").toString();
        assertEquals("{-1.0, 0.0, 1.0}", res);
    }

    @Test
    public void testVectorSubVector() throws CalcException {
        Parser parser = new Parser();
        String res = parser.calc("A={1, 2, 3.2}-{4.7, 5.5, 6.2}").toString();
        assertEquals("{-3.7, -3.5, -3.0}", res);
    }

    @Test
    public void testVectorMulScalar() throws CalcException {
        Parser parser = new Parser();
        String res = parser.calc("A={1, 2, 3.2}*1.2").toString();
        assertEquals("{1.2, 2.4, 3.84}", res);
    }

    @Test
    public void testVectorMulVector() throws CalcException {
        Parser parser = new Parser();
        String res = parser.calc("A={2,3,4}*{1,2,3}").toString();
        assertEquals("20.0", res);
    }

    @Test(expected = CalcException.class)
    public void testVectorMulVectorWithNullResult() throws CalcException {
        Parser parser = new Parser();
        parser.calc("A={1,1,1}*{33,33}");
        fail();
    }

    @Test
    public void testVectorDivScalar() throws CalcException {
        Parser parser = new Parser();
        String res = parser.calc("A={2.4,-2,3}/-2").toString();
        assertEquals("{-1.2, 1.0, -1.5}", res);
    }

    @Test(expected = CalcException.class)
    public void testVectorDivVector() throws CalcException {
        Parser parser = new Parser();
        parser.calc("A={1,1,1}/{2,2,2}");
        fail();
    }

    @Test
    public void testBigTask() throws CalcException {
        Parser parser = new Parser();
        String res = parser.calc("A=({10,-3,5.5}+{1,1,-1}*2)*{0.5, -1.0, 1}").toString();
        assertEquals("10.5", res);
    }
}