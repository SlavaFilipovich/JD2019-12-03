package by.it.cherkas.jd01_10;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.Math;
import java.lang.reflect.Type;


public class PrintMath {
    private Method method;
    public static void main(String[] args) {
             Class<Math> mathClass = Math.class;
        Method[] methods=mathClass.getDeclaredMethods();
        for (Method method : methods) {
            StringBuilder str = new StringBuilder("public static");
            if (((method.getModifiers() & Modifier.PUBLIC) == Modifier.PUBLIC)
                    && (method.getModifiers() & Modifier.STATIC) == Modifier.STATIC) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                str.append(method.getReturnType()).append(" ").append(method.getName()).append("(");
                if (parameterTypes.length == 0) {
                    str.append(")");
                }
                if (parameterTypes.length == 1) {
                    str.append(parameterTypes[0]).append(")");
                } else
                    for (int i = 0; i < parameterTypes.length; i++) {
                        if (i == parameterTypes.length - 1) {
                            str.append(parameterTypes[i]).append(")");
                        } else
                            str.append(parameterTypes[i]).append(",");
                    }
            }
        }
        Field[] fields=mathClass.getFields();
        for (Field field:fields) {
            StringBuilder str=new StringBuilder("");
            str.append(" ").append(field.getType()).append(field.getName());
            System.out.println(str);

        }
        {

        }


                }
            }

