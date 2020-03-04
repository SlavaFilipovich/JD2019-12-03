package by.it.filipovich.calc;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;

public class Logger {
    private static volatile Logger instance;
    private static String loggerFile = pathToFile("log.txt", Logger.class);
    private static ArrayDeque<String> logList = new ArrayDeque<>();
    private String currentTime;

    public Logger() {
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    void logWrite(String message) {
        loadLog();
        currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        logList.add(currentTime + " | " + message);
        try (PrintWriter pw = new PrintWriter(new FileWriter(loggerFile))) {
            for (String s : logList) {
                pw.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void loadLog() {
        try (BufferedReader br = new BufferedReader(new FileReader(loggerFile))) {
            logList.clear();
            String logLine;
            while (true) {
                logLine = br.readLine();
                if (logLine == null) break;
                logList.addLast(logLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String pathToFile(String fileName, Class<?> aClass) {
        String root = System.getProperty("user.dir");
        String strPackage = aClass.getName().replace(aClass.getSimpleName(), "");
        String path = root + File.separator + "src" + File.separator + strPackage.replace(".", File.separator);
        fileName = path + fileName;
        return fileName;
    }
}
