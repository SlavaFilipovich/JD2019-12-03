package by.it.shulga.jd01.calc02_04;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAll {
    @Test
    public void testTask() throws CalcException {
        Parser parser = new Parser();
        parser.calc("A=2+5.3");
        parser.calc("B=A*3.5");
        parser.calc("C=B+(A*2)");
        parser.calc("D=((C-0.15)-20)/(7-5)");
        String res = parser.calc("E={2,3}*(D/2)").toString();
        assertEquals("{10.0, 15.0}", res);
    }
}
