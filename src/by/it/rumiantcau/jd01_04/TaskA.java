package by.it.rumiantcau.jd01_04;

import java.util.Scanner;

public class TaskA {
    public static void main(String[] args) {
        printMulTable();

        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        buildOneDimArray(s);

    }



    static void printMulTable(){
        for (int i = 2; i < 10; i++) {
            for (int j = 2; j < 10; j++) {
          // System.out.print    (i+"*"+j+"="+i*j+"   ");
                System.out.printf("%d*%d=%-2d ", i, j, i * j);
                            }
            System.out.println();
        }

    }

    static void buildOneDimArray(String line){

        double[] array = InOut.getArray(line);
        double start = array[0];
        double end = array[array.length-1];
        InOut.printArray(array,"V",5);
        Helper.sort(array);
        InOut.printArray(array,"V",4);
        for (int i = 0; i < array.length; i++) {
            if (array[i] == start) {
                System.out.println("first element="+i);
            }
            if (array[i] == end) {
                System.out.println("last element="+i);
            }

        }

    }





}
