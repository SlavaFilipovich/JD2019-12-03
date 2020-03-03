package by.it.tarasevich.calc;

public class Printer {
    void print(Var var) {
        if (var != null)
            System.out.println(var);
        else System.out.println("UNKNOWN ERROR!!");
    }
}
