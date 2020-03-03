package by.it.cherkas.jd01_14;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static by.it.cherkas.jd01_14.Test_jd01_14.dir;


public class TaskA {

    public static void main(String[] args) {
        String fileName = Helper.getPath("dataTaskA.bin", TaskA.class);
        writeInt(fileName);

        List<Integer> list = new ArrayList<>();
        readListInteger(fileName, list);

        printListToConsole(list);

        String resultTxt = Helper.getPath("resultTaskA.txt", TaskA.class);
        printListToFile(list, resultTxt);
    }
    private static void writeInt(String fileName) {
        try (DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(fileName)))) {
            for (int i = 0; i <20 ; i++) {
                int value = (int) (Math.random()*100);
                dos.writeInt(value);
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    private static void printListToFile(List<Integer> list, String resultTxt) {
        try (PrintWriter writer = new PrintWriter(resultTxt)){
            double sum = 0;
            for (Integer i : list) {
                writer.print(i + " ");
                sum+=i;
            }
            writer.println("\navg=" + sum/list.size());

        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }

    }

    private static void printListToConsole(List<Integer> list) {
        double sum = 0;
        for (Integer i : list) {
            System.out.print(i + " ");
            sum+=i;
        }
        System.out.println("\navg=" +sum/list.size() );

    }

    private static void readListInteger(String fileName, List<Integer> list) {
        try (DataInputStream dis = new DataInputStream(
                new BufferedInputStream(new FileInputStream(fileName)))
        ) {
                while (dis.available()>0) {
                    list.add(dis.readInt());
                }

    } catch (IOException e) {
            System.out.println("Error");
        }
    }
}





