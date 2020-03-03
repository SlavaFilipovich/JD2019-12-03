package by.it.potapovich.calc2_4;

import org.junit.Test;

import static org.junit.Assert.*;

public class VectorTest {

    @Test
    public void add() throws CalcException {
        Parser parser=new Parser();
        String resultAdd = parser.calc("A={1.0,2.0,3.0}+{2.0,3.0,4.0}").toString();
        assertEquals("{3.0, 5.0, 7.0}",resultAdd);
    }
    @Test
    public void VectorAddScalar() throws CalcException {
        Parser parser=new Parser();
        String VectorAddScalar = parser.calc("A={1.0,2.0,3.0}+15").toString();
        assertEquals("{16.0, 17.0, 18.0}",VectorAddScalar);

    }

    @Test
    public void sub() throws CalcException {
        Parser parser=new Parser();
        String resultSub = parser.calc("A={1.0,2.0,3.0}-{2.0,3.0,4.0}").toString();
        assertEquals("{-1.0, -1.0, -1.0}",resultSub);
    }
    @Test
    public void VectorSubScalar() throws CalcException {
        Parser parser=new Parser();
        String VectorSubScalar = parser.calc("A={1.0,2.0,3.0}-15").toString();
        assertEquals("{-14.0, -13.0, -12.0}",VectorSubScalar);
    }

    @Test
    public void mul() throws CalcException {
        Parser parser=new Parser();
        String mul = parser.calc("A={1.0,2.0,3.0}*{2.0,3.0,4.0}").toString();
        assertEquals("20.0",mul);
    }
    @Test
    public void VectorMulScalar() throws CalcException {
        Parser parser=new Parser();
        String VectorMulScalar = parser.calc("A={1.0,2.0,3.0}*15").toString();
        assertEquals("{15.0, 30.0, 45.0}",VectorMulScalar);
    }

    @Test(expected = CalcException.class)
    public void div() throws CalcException {
        Parser parser=new Parser();
        String div = parser.calc("A={1.0,2.0,3.0}/{2.0,3.0,4.0}").toString();

    }
    @Test
    public void VectorDivScalar() throws CalcException {
        Parser parser=new Parser();
        String VectorDivScalar = parser.calc("A={1.0,2.0,3.0}/15").toString();
        assertEquals("{0.06666666666666667, 0.13333333333333333, 0.2}",VectorDivScalar);
    }
}