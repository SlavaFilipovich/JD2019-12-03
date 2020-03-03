package by.it.shulga.jd01.calc02_04;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

class Logger {
    private int count;

    public Logger() {
        try {
            count = (int) Files.lines(Paths.get("src/by/it/shulga/jd01/calc02_04/log.txt")).count();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    void print(String message) {
        if (message != null) {
            if (count >= 50) {
                clear();
            }
            if (message.equals("end")) {
                message += "\nI hope to see you again \u270B";
            }
            try (PrintWriter writer = new PrintWriter(new FileWriter("src/by/it/shulga/jd01/calc02_04/log.txt", true))) {
                count++;
                writer.println(message);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    void clear() {
        count = 0;
        try {
            Files.delete(Paths.get("src/by/it/shulga/jd01/calc02_04/log.txt"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
