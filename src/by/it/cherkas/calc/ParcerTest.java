package by.it.cherkas.calc;

import org.junit.*;
import static org.junit.Assert.*;

public class ParcerTest {

    private Parser parser = new Parser();

    @Test
    public void a1() {
        System.out.println("Test A=2+5.3");
        try {
            Var calc = parser.calc("A=2+5.3");
            assertEquals("Wrong result for A=2+5.3", "7.3", calc.toString());
            System.out.println(calc.toString());
            System.out.println("Test is successful, so you see this message");
        }
        catch (CalcException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void a2() {
        System.out.println(" Test B=A*3.5");
        try {
            Var calc = parser.calc("B=A*3.5");
            assertEquals("Wrong result for B=A*3.5", "25.55", calc.toString());
            System.out.println(calc.toString());
            System.out.println("Test is successful, so you see this message");
        } catch (CalcException e) {
            e.printStackTrace();
        }
    }

        @Test
        public void a3() {
            System.out.println(" Test B1=B+0.11*-5");
            try {
                Var calc = parser.calc("B1=B+0.11*-5");
                assertEquals("Wrong result for B1=B+0.11*-5", "25.0", calc.toString());
                System.out.println(calc.toString());
                System.out.println("Test is successful, so you see this message");
            } catch (CalcException e) {
                e.printStackTrace();
            }
        }

            @Test
            public void a4() {
                System.out.println(" Test B2=A/2-1");
                try {
                    Var calc = parser.calc("B2=A/2-1");
                    assertEquals("Wrong result for B2=A/2-1", "2.65", calc.toString());
                    System.out.println(calc.toString());
                    System.out.println("Test is successful, so you see this message");
                }
                catch (CalcException e) {
                    e.printStackTrace();
                }
    }


    @Test
    public void vectorAddScalarTaskB() {
        System.out.println(" Test {2, 3} + 2");
        try {
            Var calc = parser.calc("{2, 3} + 2");
            assertEquals("Wrong result for {2, 3} + 2", "{4.0, 5,0}", calc.toString());
            System.out.println(calc.toString());
            System.out.println("Test is successful, so you see this message");
        }
        catch (CalcException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void vectorAddVectorTaskB() {
        System.out.println(" Test {0, 1}+{2, 3}");
        try {
            Var calc = parser.calc("{0, 1}+{2, 3}");
            assertEquals("Wrong result for {0, 1}+{2, 3}", "{2.0, 4.0}", calc.toString());
            System.out.println(calc.toString());
            System.out.println("Test is successful, so you see this message");
        }
        catch (CalcException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void vectorSubScalarTaskB() {
        System.out.println(" Test {2, 3} - 2");
        try {
            Var calc = parser.calc("{2, 3} - 2");
            assertEquals("Wrong result for {2, 3} - 2", "{0.0, 1,0}", calc.toString());
            System.out.println(calc.toString());
            System.out.println("Test is successful, so you see this message");
        }
        catch (CalcException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void vectorSubVectorTaskB() {
        System.out.println(" Test {0, 1}-{2, 3}");
        try {
            Var calc = parser.calc("{0, 1}-{2, 3}");
            assertEquals("Wrong result for {0, 1}-{2, 3}", "{-2.0, -2.0}", calc.toString());
            System.out.println(calc.toString());
            System.out.println("Test is successful, so you see this message");
        }
        catch (CalcException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void vectorMulScalarTaskB() {
        System.out.println(" Test {{0, 1}, {2, 3}}*3");
        try {
            Var calc = parser.calc("{{0, 1}, {2, 3}}*3");
            assertEquals("Wrong result for {{0, 1}, {2, 3}}*3", "{{0.0, 3.0}, {6.0, 9.0}}", calc.toString());
            System.out.println(calc.toString());
            System.out.println("Test is successful, so you see this message");
        }
        catch (CalcException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void vectorMulVectorTaskB() {
        System.out.println(" Test {0, 1}*{2, 3}");
        try {
            Var calc = parser.calc("{0, 1}*{2, 3}");
            assertEquals("Wrong result for {0, 1}*{2, 3}", "3.0", calc.toString());
            System.out.println(calc.toString());
            System.out.println("Test is successful, so you see this message");
        }
        catch (CalcException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void vectorDivScalarTaskB() {
        System.out.println(" Test {2, 3} / 2");
        try {
            Var calc = parser.calc("{2, 3} / 2");
            assertEquals("Wrong result for {2, 3} / 2", "{1.0, 1,5}", calc.toString());
            System.out.println(calc.toString());
            System.out.println("Test is successful, so you see this message");
        }
        catch (CalcException e) {
            e.printStackTrace();
        }
    }
}
