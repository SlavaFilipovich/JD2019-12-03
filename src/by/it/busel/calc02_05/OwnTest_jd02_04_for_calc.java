package by.it.busel.calc02_05;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class OwnTest_jd02_04_for_calc {

    private static ResourcesManager rManager = new ResourcesManager();

    private String className;
    private Class<?> classStructure;
    private Method method;
    private Object instance;

    public OwnTest_jd02_04_for_calc() {
    }

    private OwnTest_jd02_04_for_calc(String classNameToTest) {
        try {
            className = classNameToTest;
            classStructure = Class.forName(classNameToTest);
            if (classNameToTest.equals("by.it.busel.calc02_05.Parser")) {
                method = classStructure.getDeclaredMethod("calc", String.class);
                instance = classStructure.newInstance();
            }
        } catch (ClassNotFoundException | NoSuchMethodException
                | InstantiationException | IllegalAccessException e) {
            fail("ERROR: Class " + classNameToTest + " not found");
        }
    }

    private OwnTest_jd02_04_for_calc run(String methodName) {
        Throwable informant = new Throwable(); // Reflection-informant
        StackTraceElement[] stackTrace = informant.getStackTrace();
        StackTraceElement stackTraceElement;
        int i = 0;
        do {
            stackTraceElement = stackTrace[i++];
        } while (!stackTraceElement.getMethodName().contains(methodName));
        String[] path = stackTraceElement.getClassName().split("\\.");
        String nameTestMethod = stackTraceElement.getMethodName();
        String classNameToTest = nameTestMethod.replaceFirst(".+__", "");
        classNameToTest = stackTraceElement.getClassName().replace(path[path.length - 1], classNameToTest);
        System.out.println("---------------------------------------------------");
        System.out.printf("Test for \"%s\" has started%nby method %s()%n",
                classNameToTest, stackTraceElement.getMethodName());
        System.out.println("---------------------------------------------------");
        return new OwnTest_jd02_04_for_calc(classNameToTest);
    }

    private Object invoke(Method tMethod, Object tObject, String tObjectArguments) throws Exception {
        try {
            return tMethod.invoke(tObject, tObjectArguments);
        } catch (IllegalAccessException | InvocationTargetException e) {
            Exception cause = (Exception) e.getCause();
            if (cause.toString().contains("CalcException")) {
                return null;
            }
            throw new Exception(e);
        }
    }

    private void finish(OwnTest_jd02_04_for_calc test) {
        System.out.printf("Test for %s\nsuccessfully finished%n", test.className);
    }

    private void assertMatrixArrayEquals(String message, double[][] expected, double[][] actual, double delta) {
        for (int i = 0; i < expected.length; i++) {
            double[] expectedArray = expected[i];
            double[] actualArray = actual[i];
            assertArrayEquals(message, expectedArray, actualArray, delta);
        }
    }

    public String toString(double[] vector) {
        String sep = ", ";
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < vector.length; i++) {
            sb.append(vector[i]);
            if (i != vector.length - 1) {
                sb.append(sep);
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public String toString(double[][] matrix) {
        String separator = ", ";
        StringBuilder strToString = new StringBuilder("{");
        for (int i = 0; i < matrix.length; i++) {
            strToString.append("{");
            for (int j = 0; j < matrix[0].length; j++) {
                strToString.append(matrix[i][j]);
                if (j != matrix[0].length - 1) {
                    strToString.append(separator);
                }
            }
            strToString.append("}");
            if (i != matrix.length - 1) {
                strToString.append(separator);
            }
        }
        strToString.append("}");
        return strToString.toString();
    }

    @Test
    public void testTaskA1_add_ScalarBy__Parser() throws Exception {
        OwnTest_jd02_04_for_calc test = run("testTaskA1_add_ScalarBy__Parser");
        String taskA1 = "1+1+2+2+3.2+3.2";
        double expected = 1 + 1 + 2 + 2 + 3.2 + 3.2;
        Object invoke = test.method.invoke(test.instance, taskA1);
        double result = Double.parseDouble(invoke.toString());
        assertEquals("Multiaddition is incorrect ", expected, result, 1e-10);
        finish(test);
    }

    @Test
    public void testTaskA2_sub_ScalarBy__Parser() throws Exception {
        OwnTest_jd02_04_for_calc test = run("testTaskA2_sub_ScalarBy__Parser");
        String taskA2 = "23.1-87.4-9-0.9-1.23-4.58";
        double expected = 23.1 - 87.4 - 9.0 - 0.9 - 1.23 - 4.58;
        Object invoke = test.method.invoke(test.instance, taskA2);
        double result = Double.parseDouble(invoke.toString());
        assertEquals("Multisubtraction is incorrect", expected, result, 1e-10);
        finish(test);
    }

    @Test
    public void testTaskA3_mul_ScalarBy__Parser() throws Exception {
        OwnTest_jd02_04_for_calc test = run("testTaskA3_mul_ScalarBy__Parser");
        String taskA3 = "6172839.5*2*8*9";
        double expected = 6172839.5 * 2.0 * 8.0 * 9.0;
        Object invoke = test.method.invoke(test.instance, taskA3);
        double result = Double.parseDouble(invoke.toString());
        assertEquals("Multimultiplication is incorrect", expected, result, 1e-10);
        finish(test);
    }

    @Test
    public void testTaskA4_div_ScalarBy__Parser() throws Exception {
        OwnTest_jd02_04_for_calc test = run("testTaskA4_div_ScalarBy__Parser");

        String taskA4 = "888888888/9.1/8.2";
        double expected = 888888888 / 9.1 / 8.2;
        Object invoke = test.method.invoke(test.instance, taskA4);
        double result = Double.parseDouble(invoke.toString());
        assertEquals("Multidivision is incorrect", expected, result, 1e-10);

        String taskA4second = "888888888/9.1/8.2/0";
        Object invoke2 = invoke(test.method, test.instance, taskA4second);
        if (invoke2 != null) {
            fail("Multidivision division by zero shall throw a CalcException");
        }
        finish(test);
    }

    @Test
    public void testTaskB1_add_VectorBy__Parser() throws Exception {
        OwnTest_jd02_04_for_calc test = run("testTaskB1_add_VectorBy__Parser");

        String taskB1First = "{1,2,3}+({4,5,6}+{7,8,9})";
        double[] expected1st = new double[]{12, 15, 18};
        Vector result1st = (Vector) test.method.invoke(test.instance, taskB1First);
        assertArrayEquals("Multiaddition between Vectors is incorrect",
                expected1st, result1st.getValue(), 1e-10);

        String taskB1Second = "({1,2,3.4}+1.23456789)+{4.5,5,6}";
        double[] expected2nd = new double[]{6.73456789, 8.23456789, 10.63456789};
        Vector result2nd = (Vector) test.method.invoke(test.instance, taskB1Second);
        assertArrayEquals("Multiaddition between Vectors and Scalars is incorrect",
                expected2nd, result2nd.getValue(), 1e-10);

        String taskB1Third = "{1,2,3.4}+{1,2,3,4}+{1,2,3,4,5}";
        Object result3rd = invoke(test.method, test.instance, taskB1Third);
        if (result3rd != null) {
            fail("While adding two Vectors of the different size a CalcException shall be thrown.");
        }
        finish(test);
    }

    @Test
    public void testTaskB2_sub_VectorBy__Parser() throws Exception {
        OwnTest_jd02_04_for_calc test = run("testTaskB2_sub_VectorBy__Parser");

        String taskB2First = "{1.2,3.4,5.6}-({7.8,9.1,11.12}-{13.14,15.16,17.18})";
        double[] expected1st = new double[]{6.54, 9.46, 11.66};
        Vector result1st = (Vector) test.method.invoke(test.instance, taskB2First);
        assertArrayEquals("Multisubtraction between Vectors is incorrect",
                expected1st, result1st.getValue(), 1e-10);

        String taskB2Second = "{1.2,3.4,5.6}-({7.8,9.1,11.12}-13)-{14.15,16.17,18.19}";
        double[] expected2nd = new double[]{-7.75, -8.87, -10.71};
        Vector result2nd = (Vector) test.method.invoke(test.instance, taskB2Second);
        assertArrayEquals("Multisubtraction between Vectors and Scalar is incorrect",
                expected2nd, result2nd.getValue(), 1e-10);

        String taskB2Third = "{1,2,3.4}-{1,2,3,4}-{5,7}";
        Object result3rd = invoke(test.method, test.instance, taskB2Third);
        if (result3rd != null) {
            fail("While subtracting two Vectors of the different size a CalcException shall be thrown.");
        }
        finish(test);
    }

    @Test
    public void testTaskB3_mul_VectorBy__Parser() throws Exception {
        OwnTest_jd02_04_for_calc test = run("testTaskB3_mul_VectorBy__Parser");

        String taskB3First = "{1.2,3.4}*{5.6,7.8}*({9.1,11.12}*{13.14,15.16})";
        double expected1st = 9578.212368;
        Scalar result1st = (Scalar) test.method.invoke(test.instance, taskB3First);
        assertEquals("Multimultiplication between Vectors is incorrect",
                expected1st, result1st.getValue(), 1e-10);

        String taskB3Second = "1.2*({3.4,5.6}*7.8)";
        double[] expected2nd = new double[]{31.824, 52.416};
        Vector result2nd = (Vector) test.method.invoke(test.instance, taskB3Second);
        assertArrayEquals("Multimultiplication between Vectors and Scalars is incorrect",
                expected2nd, result2nd.getValue(), 1e-10);

        String taskB3Third = "1.2*{3.4,5.6}*{7.8,9.1,11.12}";
        Object result3rd = invoke(test.method, test.instance, taskB3Third);
        if (result3rd != null) {
            fail("While multiplying two Vectors of the different size a CalcException shall be thrown");
        }
        finish(test);
    }

    @Test
    public void testTaskB4_div_VectorBy__Parser() throws Exception {
        OwnTest_jd02_04_for_calc test = run("testTaskB4_div_VectorBy__Parser");

        String taskB4First = "(1.23+{4.56,7.89})/10.12";
        double[] expected1st = new double[]{0.572134387, 0.901185771};
        Vector result1st = (Vector) test.method.invoke(test.instance, taskB4First);
        assertArrayEquals("Division of Vectors by Scalars is incorrect",
                expected1st, result1st.getValue(), 1e-9);

        String taskB4Second = "({1.2,3.4}+4.5)/{5.6,7.8}";
        Object result2rd = invoke(test.method, test.instance, taskB4Second);
        if (result2rd != null) {
            fail("While dividing two Vectors a CalcException shall be thrown");
        }

        String taskB4Third = "(1.2/{3.4,5.6})/{7.8,9.10}";
        Object result3rd = invoke(test.method, test.instance, taskB4Third);
        if (result3rd != null) {
            fail("While dividing Vector by Scalar a CalcException shall be thrown");
        }

        String taskB4Forth = "((1.2+{3.45,6.789})/0";
        Object result4th = invoke(test.method, test.instance, taskB4Forth);
        if (result4th != null) {
            fail("While dividing Vector by Zero-Scalar a CalcException shall be thrown");
        }
        finish(test);
    }

    @Test
    public void testTaskC1_add_MatrixBy__Parser() throws Exception {
        OwnTest_jd02_04_for_calc test = run("testTaskC1_add_MatrixBy__Parser");

        String taskC1First = "{{1.23,4.56,7.89},{10.1112,13.1415,16.1718},{19.2021,22.2324,25.2627}}+(28.2930+31.3233)";
        double[][] expected1st = new double[][]{{60.8463, 64.1763, 67.5063},
                {69.7275, 72.7578, 75.7881}, {78.8184, 81.8487, 84.879}};
        Matrix result1st = (Matrix) test.method.invoke(test.instance, taskC1First);
        assertMatrixArrayEquals("Addition between Matrixs and Scalars is incorrent",
                expected1st, result1st.getValue(), 1e-10);

        String taskC1Second = "{{1.23,4.56,7.89},{10.1112,13.1415,16.1718},{19.2021,22.2324,25.2627}}"
                + "+{28.2930,31.3233,34.3536}+{37.3839,40.4142}";
        Object result2nd = invoke(test.method, test.instance, taskC1Second);
        if (result2nd != null) {
            fail("While adding Matrix to Vector a CalcException shall be thrown");
        }

        String taskC1Third = "{{1.2,3.4},{5.6,7.8},{9.1,11.12}}+{{13.14,15.16},{17.18,19.20},{21.22,23.24}}+25.26";
        double[][] expected3rd = new double[][]{{39.6, 43.82}, {48.04, 52.26}, {55.58, 59.62}};
        Matrix result3rd = (Matrix) test.method.invoke(test.instance, taskC1Third);
        assertMatrixArrayEquals("Addition betweeen Matrixs is incorrect",
                expected3rd, result3rd.getValue(), 1e-11);

        String taskC1Fourth = "{{1.2,3.4},{1.2,3.4}}+{{1.2,3.4,4.5},{5.6,7.8,8.9},{1.2,3.4,4.5}}+123456789";
        Object result4th = invoke(test.method, test.instance, taskC1Fourth);
        if (result4th != null) {
            fail("While adding between Matrixs that are of different size a CalcException shall be thrown");
        }
        finish(test);
    }

    @Test
    public void testTaskC2_sub_MatrixBy__Parser() throws Exception {
        OwnTest_jd02_04_for_calc test = run("testTaskC2_sub_MatrixBy__Parser");
        String testC2First = "{{1.23,4.56,7.89},{10.1112,13.1415,16.1718},{19.2021,22.2324,25.2627}}-(28.2930+31.3233)";
        double[][] expected1st = new double[][]{{-58.3863, -55.0563, -51.7263}, {-49.5051, -46.4748, -43.4445},
                {-40.4142, -37.3839, -34.3536}};
        Matrix result1st = (Matrix) test.method.invoke(test.instance, testC2First);
        assertMatrixArrayEquals("Subtraction between Matrix and Scalars is incorrect",
                expected1st, result1st.getValue(), 1e-10);

        String testC2Second = "{{1.23,4.56,7.89},{10.1112,13.1415,16.1718},{19.2021,22.2324,25.2627}}"
                + "-{28.2930,31.3233,34.3536}-{37.3839,40.4142}";
        Object result2nd = invoke(test.method, test.instance, testC2Second);
        if (result2nd != null) {
            fail("While subtracting Matrixs and Vectors a CalcException shall be thrown");
        }

        String testC2Third = "{{1.2,3.4},{5.6,7.8},{9.1,11.12}}-{{13.14,15.16},{17.18,19.20},{21.22,23.24}}-25.26";
        double[][] expected3rd = new double[][]{{-37.2, -37.02}, {-36.84, -36.66}, {-37.38, -37.38}};
        Matrix result3rd = (Matrix) test.method.invoke(test.instance, testC2Third);
        assertMatrixArrayEquals("Subtraction between Matrixs is incorrect",
                expected3rd, result3rd.getValue(), 1e-11);

        String testC2Fourth = "{{1.2,3.4},{1.2,3.4}}-{{1.2,3.4,4.5},{5.6,7.8,8.9},{1.2,3.4,4.5}}-123456789";
        Object result4th = invoke(test.method, test.instance, testC2Fourth);
        if (result4th != null) {
            fail("While subtracting between Matrixs that are of different size a CalcException shall be thrown");
        }
        finish(test);
    }

    @Test
    public void testTaskC3_mul_Matrix__Parser() throws Exception {
        OwnTest_jd02_04_for_calc test = run("testTaskC3_mul_Matrix__Parser");
        String taskC3First = "({{1.12,2.34,3.56},{4.78,5.91,6.1112},{7.1314,8.1516,9.1718}}*11.11)*22.22";
        double[][] expected1st = new double[][]{{276.487904, 577.662228, 878.836552}, {1180.010876, 1458.967422, 1508.636499},
                {1760.487356, 2012.338213, 2264.18907}};
        Matrix result1st = (Matrix) test.method.invoke(test.instance, taskC3First);
        assertMatrixArrayEquals("Multiplication between Matrixs and Scalars is incorrect,",
                expected1st, result1st.getValue(), 1e-5);

        String taskC3Second = "{{1.23,4.56},{7.89,10.11},{12.13,14.15}}*{16.17,18.19}";
        double[] expected2nd = new double[]{102.8355, 311.4822, 453.5306};
        Vector result2nd = (Vector) test.method.invoke(test.instance, taskC3Second);
        assertArrayEquals("Multiplication between Matrixs and Vectors is incorrect",
                expected2nd, result2nd.getValue(), 1e-10);

        String taskC3Third = "{{1.23,4.56},{7.89,10.11},{12.13,14.15}}*{16.17,18.19,20.21}*22";
        Object result3rd = invoke(test.method, test.instance, taskC3Third);
        if (result3rd != null) {
            fail("While multiplying Matrixs and Vectors that are incompatible a CalcException shall be thrown");
        }

        String taskC3Fourth = "{{1.2,3.4},{5.6,7.8},{9.1,11.1}}*{{12.2,13.3,14.4},{15.5,16.6,17.7}}*{1,2,3}";
        double[] expected4th = new double[]{444.52, 1253.24, 1876.18};
        Vector result4th = (Vector) test.method.invoke(test.instance, taskC3Fourth);
        assertArrayEquals("Multiplication between Matrix is incorrect",
                expected4th, result4th.getValue(), 1e-10);

        String taskC3Fifth = "{{1.2,3.4},{5.6,7.8},{9.1,11.1}}*{{12.2,13.3,14.4},{15.5,16.6,17.7},{15.5,16.6,17.7}}" +
                "*{1,2,3}";
        Object result5th = invoke(test.method, test.instance, taskC3Fifth);
        if (result5th != null) {
            fail("While multiplying Matrixs of an incompatible size a CalcException shall be thrown");
        }
        finish(test);
    }

    @Test
    public void testTaskC4_div_MatrixBy__Parser() throws Exception {
        OwnTest_jd02_04_for_calc test = run("testTaskC4_div_MatrixBy__Parser");
        String taskC4First = "{{1.2,3.4,5.6},{7.8, 9.1, 11.12}}/0.1/3";
        double[][] expected1st = new double[][]{{4, 11.33333333, 18.66666667}, {26, 30.33333333, 37.06666667}};
        Matrix result1st = (Matrix) test.method.invoke(test.instance, taskC4First);
        assertMatrixArrayEquals("Division between Matrixs and Scalars is incorrect",
                expected1st, result1st.getValue(), 1e-8);

        String taskC4Second = "{{1.2,3.4,5.6},{7.8, 9.1, 11.12}}/0.1/0";
        Object result2nd = invoke(test.method, test.instance, taskC4Second);
        if (result2nd != null) {
            fail("While dividing Matrixs by zero-Scalar a CalcException is to be thrown");
        }

        String taskC4Third = "{{1.2,3.4,5.6},{7.8, 9.1, 11.12}}/{5,6,7}/{{1.2,3.4,5.6},{7.8, 9.1, 11.12}}";
        Object result3rd = invoke(test.method, test.instance, taskC4Third);
        if (result3rd != null) {
            fail("While dividing Matrixs by Vectors a CalcException shall be thrown");
        }

        String taskC4Forth = "{{1.2,3.4,5.6},{7.8, 9.1, 11.12}}/{{1.2,3.4,5.6},{7.8, 9.1, 11.12}}"
                + "/{{1.2,3.4,5.6},{7.8, 9.1, 11.12}}";
        Object result4th = invoke(test.method, test.instance, taskC4Forth);
        if (result4th != null) {
            fail("While dividing Matrixs by Matrixs a CalcException shall be thrown");
        }
        finish(test);
    }

    @Test
    public void testTaskD1_StringConstructors__Scalar() throws Exception {
        OwnTest_jd02_04_for_calc test = run("testTaskD1_StringConstructors__Scalar");
        Constructor<?> cons = test.classStructure.getDeclaredConstructor(String.class);
        double scalarExpected = 0.123456789;
        Scalar scalarActual = (Scalar) cons.newInstance(" 0.123456789 ");
        assertEquals("A String-constructor of Scalar is incorrect",
                scalarExpected, scalarActual.getValue(), 1e-11);
        finish(test);
    }

    @Test
    public void testTaskD2_StringConstructors__Vector() throws Exception {
        OwnTest_jd02_04_for_calc test = run("testTaskD2_StringConstructors__Vector");
        Constructor<?> cons = test.classStructure.getDeclaredConstructor(String.class);
        double[] vectorExpected = new double[]{1, 2.345678912, 3.456789123, 888888888};
        Vector vectorActual = (Vector) cons.newInstance
                ("{1, 2.34567891200000,3.456789123, 8.88888888E8}");
        assertArrayEquals("A String-constructor of Vector is incorrect",
                vectorExpected, vectorActual.getValue(), 1e-11);
        assertEquals("toString is incorrect", toString(vectorExpected), vectorActual.toString());
        finish(test);
    }

    @Test
    public void testTaskD3_StringConstructors__Matrix() throws Exception {
        OwnTest_jd02_04_for_calc test = run("testTaskD3_StringConstructors__Matrix");
        Constructor<?> cons = test.classStructure.getDeclaredConstructor(String.class);
        double[][] matrixExpected = new double[][]{{1, 2.34567891, 3.45678912},
                {4.56789123, 5.67891234, 6.78912345}, {7.89123456, 8.91234567, 9.1234567}};
        Matrix matrixActual = (Matrix) cons.newInstance("{{1, 2.34567891, 3.45678912}," +
                " {4.56789123, 5.67891234, 6.78912345}, {7.89123456,8.91234567,9.123456700000}}");
        assertMatrixArrayEquals("A String-constructor of Matrix is incorrect",
                matrixExpected, matrixActual.getValue(), 1e-10);
        assertEquals("toString is incorrect", toString(matrixExpected), matrixActual.toString());
        finish(test);
    }
}