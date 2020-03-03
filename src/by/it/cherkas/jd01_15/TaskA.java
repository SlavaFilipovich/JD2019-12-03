package by.it.cherkas.jd01_15;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.Arrays.deepToString;

public class TaskA {
    public static void main(String[] args) throws IOException {
        int [][] array = new int[6][4];
        fill(array);
        String string = deepToString(array);
        String fileName = Helper.getPath("matrix.txt", TaskA.class);
        try (PrintWriter writer = new PrintWriter(fileName)){
            writer.print(string);

        }
        Files.lines(Paths.get(fileName)).forEach(System.out::println);
    }
    private static String deepToString(int[][] array){
        StringBuilder out = new StringBuilder();
        for (int[] row : array) {
            for (int i : row) {
                out.append(String.format("%3d ", i));
            }
            out.append("\n");
        }
        return out.toString();
    }

    private static void fill(int[][] array) {
        boolean min, max;
        do {
            min = false;
            max = false;
            for (int[] row : array) {
                for (int i = 0; i <row.length ; i++) {
                    row[i] = -15 + (int) (Math.random() * 31);
                    if (row[i] == -15)
                        min = true;
                    if (row[i] == 15)
                        max = true;

                }
            }
        }
        while (!min || !max);

    }
}
