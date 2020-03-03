package by.it.tarasevich.calc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest {
    @Before
    public void start() throws Exception{
        System.out.println("Start");
    }

    @After
    public void stop() throws Exception{
        System.out.println("stop");
    }

    @Test
    public void testSimpleExpressionForScalar() throws Exception {
        Parser parser=new Parser();
        String expression = "2+2*2";
        double excected=6.0;
        Scalar scalar = (Scalar) parser.calc(expression);
        double actuial = Double.parseDouble(scalar.toString());
        assertEquals("Incorrect "+expression,excected,actuial,1e-5);

    }
    @Test
    public void testSimpleExpressionAEq1() throws Exception {
        Parser parser=new Parser();
        String expression = "A=1";
        double excected=1.0;
        Scalar scalar = (Scalar) parser.calc(expression);
        double actuial = Double.parseDouble(scalar.toString());
        assertEquals("Incorrect "+expression,excected,actuial,1e-5);

    }


}
