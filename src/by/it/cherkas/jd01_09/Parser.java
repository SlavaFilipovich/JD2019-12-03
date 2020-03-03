package by.it.cherkas.jd01_09;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    Var calc(String expression) {

        Matcher m =Pattern.compile(Patterns.MATH_SIGN).matcher(expression);
        if (m.find()){
            String sing = m.group();
            String[] values = expression.split("[-+/*]");
            String first = values[0];
            String second = values[1];
            if (Var.newVar(first) != null && Var.newVar(second) != null){
                switch (sing){
                    case "+":
                        return Var.newVar(first).add(Var.newVar(second));
                    case "-":
                        return Var.newVar(first).sub(Var.newVar(second));
                    case "*":
                        return Var.newVar(first).mul(Var.newVar(second));
                    case "/":
                        return Var.newVar(first).div(Var.newVar(second));
                }
            }

        }
        return null;
    }
}
