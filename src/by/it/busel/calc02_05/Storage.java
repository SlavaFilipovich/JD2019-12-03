package by.it.busel.calc02_05;

import java.io.*;
import java.util.*;

/**
 * a class that operates activities concerning storing Var expressions
 */
class Storage {
    /**
     * a Collection that contains all var expressions saved during the previous calculations
     */
    private static Map<String, Var> mapStorage = new HashMap<>();

    /**
     * a String that contains a Path for a file, where var expressions stored
     */
    private static final String STORAGE_FILE_PATH =
            FileAssistant.generateFilePath("vars.txt", Storage.class);

    /*
     * JavaDoc can't be formed for static logic blocks
     */
    static {
        try {
            storageReconstructionFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * a method which checks whether a target file with "STORAGE_FILE_PATH" is created,
     * if it is this method loads vars to "storage",
     * or just creates this file.
     * Moreover, the annotation "SuppressWarnings" is used in order to suppress
     * an IDEA's warning about non-usage of the value "file.createNewFile()"
     */
    @SuppressWarnings("all")
    private static void storageReconstructionFromFile() throws IOException {
        File file = new File(STORAGE_FILE_PATH);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null && line.length() > 0) {
                    String[] operand = line.split("=");
                    mapStorage.put(operand[0], Var.createVar(operand[1].trim()));
                }
            } catch (IOException | CalcException e) {
                e.printStackTrace();
            }
        } else {
            file.createNewFile();
        }
    }

    /**
     * a method that puts together a String that contains all assigned var expressions and their names
     *
     * @return a String that contains all assigned var expressions and their names in the following format:
     * "varName=varValue"
     */
    private static String storageToString() {
        Set<Map.Entry<String, Var>> entries = mapStorage.entrySet();
        Iterator<Map.Entry<String, Var>> iterator = entries.iterator();
        StringBuilder text = new StringBuilder();
        String delimiter = "";
        while (iterator.hasNext()) {
            Map.Entry<String, Var> me = iterator.next();
            text.append(delimiter).append(me.getKey()).append("=").append(me.getValue().toString());
            delimiter = "\n";
        }
        return text.toString();
    }

    /**
     * a method which prints all assigned var expressions and their names in the following format:
     * "varName=varValue". Moreover, output is logged
     */
    static void printvar() {
        String strStorage = storageToString();
        System.out.println(strStorage);
        Logger.save(Printer.formLog("printvar", strStorage));
    }

    /**
     * a method that sorts assigned var expressions by a name, then print them in the following
     * format "varName=varValue" in a console. Besides, output is logged
     */
    static void sortvar() {
        TreeMap<String, Var> treeMap = new TreeMap<>(mapStorage);
        Set<Map.Entry<String, Var>> entriesOfTryMap = treeMap.entrySet();
        Iterator<Map.Entry<String, Var>> iterator = entriesOfTryMap.iterator();
        StringBuilder text = new StringBuilder();
        String delimiter = "";
        while (iterator.hasNext()) {
            Map.Entry<String, Var> singleton = iterator.next();
            text.append(delimiter).append(singleton.getKey()).append("=").append(singleton.getValue().toString());
            delimiter = "\n";
        }
        String strOrderedStorage = text.toString();
        System.out.println(strOrderedStorage);
        Logger.save(Printer.formLog("sortvar", strOrderedStorage));
    }

    /**
     * a method that puts into a virtual storage (mapStorage field) and rewrites "mapStorage" in a target file
     *
     * @param key   a name of a var expression
     * @param value a value of a var expression
     */
    static void putVarToStorage(String key, Var value) {
        mapStorage.put(key, value);
        try (PrintWriter writer = new PrintWriter(new FileWriter(STORAGE_FILE_PATH))) {
            writer.print(storageToString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * a method which returns a value of a var expression to which the specified key (a var name) is mapped
     *
     * @param key a var name
     * @return a value of a var expression
     */
    static Var getVarFromStorage(String key) {
        return mapStorage.get(key);
    }

    /**
     * a method returns if this "mapStorage" contains a value for a specified var name
     *
     * @param key a var name which is to be found in the "mapStorage"
     * @return a boolean expression which states if "mapStorage" contains such value
     */
    static boolean containsKey(String key) {
        return mapStorage.containsKey(key);
    }
}
