package by.it.cherkas.calc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

public class Log {
    static private final String logFullName = CalcFile.fileFullName(Log.class, "log.txt");

    public static void reWriteLog(String newMessage) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(logFullName));
            lines.remove(0);
            lines.add(newMessage);
            Files.write(Paths.get(logFullName), lines);
        }
        catch (IOException e) {
            System.out.println("Error reWriteLog");
        }
    }

    public static void writeLog(String message) {
        String now = LocalDateTime.now() + ": ";
        try {
            if (Files.exists(Paths.get(logFullName)) && Files.readAllLines(Paths.get(logFullName)).size() >=50)
                reWriteLog(now + message);
            else {
                try
                    (final PrintWriter logName = new PrintWriter
                            (new FileWriter(logFullName, true))) {
                        logName.println(now + message);
                    }

                catch (IOException e) {
                    System.out.println("Error writeLog");
                }
            }
        }
         catch (IOException e) {
            e.printStackTrace();
         }
    }
}
