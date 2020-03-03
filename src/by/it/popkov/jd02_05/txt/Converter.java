package by.it.popkov.jd02_05.txt;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;


class Converter {
    public static void main(String[] args) {
        Map<String, String> map = getStringMapFromFile(Support.fileFullName(Converter.class, "hello_be_BY.txt"));
        convertValueToHex(map);
        printMapToFile(map, Support.fileFullName(Converter.class, "hello_be_BY.properties"));
    }

    static void printMapToFile(Map<String, String> map, String filePath) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                printWriter.println(entry.getKey()+"="+entry.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void convertValueToHex(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            StringBuilder stringBuilder = new StringBuilder();
            entry.getValue().chars().mapToObj(Integer::toHexString).forEach(o -> stringBuilder.append("\\u0").append(o));
            entry.setValue(stringBuilder.toString());
        }
    }

    static Map<String, String> getStringMapFromFile(String filePath) {
        Map<String, String> map = new LinkedHashMap<>();
        try {
            Files.lines(
                    Paths.get(filePath))
                    .map(o -> o.split("=")).forEach(o -> map.put(o[0], o[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
