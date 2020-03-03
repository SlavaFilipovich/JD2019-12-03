package by.it.samuseva.calc;

public class Printer {
    void print(Var rezult){
        if (rezult != null){
            System.out.println(rezult);
        } else{
            System.out.println("Неизвестная ошибка!");
        }
    }
}
