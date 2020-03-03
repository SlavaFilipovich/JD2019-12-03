package by.it.ban.calc;

import java.io.*;
import java.text.DateFormat;
import java.util.Date;


class LogFile {

    static FileWriter out;

    LogFile() throws IOException {
        File file = new File(getPath("log.txt"));
        out = new FileWriter(file, true);
    }

    static String getPath(String fileName) {
        String root = System.getProperty("user.dir");
        String strPackage = LogFile.class.getName().replace(LogFile.class.getSimpleName(), "");
        String path = root + File.separator + "src" + File.separator + strPackage.replace(".", File.separator);
        fileName = path + fileName;
        return fileName;
    }

    static void print(String message) throws IOException {
        Date d = new Date();
        out.append(DateFormat
                .getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(d))
                .append(" | ").append(message).append("\n");
    }

    static void close() {
        try {
            out.close();
            trimLogFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void trimLogFile() {
        File oldFile = new File(getPath("log.txt"));
        final File newFile = new File(getPath("log.new"));
        long count=0;
        try (BufferedReader r1 = new BufferedReader(new FileReader(oldFile))){
            count=r1.lines().count();
            count = count - 50;
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(oldFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            while (reader.ready()) {
                while (count > 0) {
                    reader.readLine();
                    count--;
                }
                writer.write(reader.readLine()+"\n");
            }
            //System.out.println("delete=" + oldFile.delete());
            //System.out.println("rename=" + newFile.renameTo(oldFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
