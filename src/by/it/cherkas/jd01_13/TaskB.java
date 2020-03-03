package by.it.cherkas.jd01_13;

import java.util.Scanner;

public class TaskB {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sum = 0;
        while (true) {
            try {
                String str = sc.nextLine();
                if (str.equals("END"))
                    break;
                int num = (int) Double.parseDouble(str);
                sum = sum + num;
                double sqrt = Math.sqrt(sum);
                if (Double.isNaN(sqrt)) {
                    throw new ArithmeticException();
                }
                System.out.println("Num=" + num + " sqrt of sum=" + sqrt);
            } catch (NumberFormatException | NullPointerException | ArithmeticException e) {
                Helper.printExeption(e, TaskB.class.getName());
            }
        }

    }
}
