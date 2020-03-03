package by.it.shulga.jd01.calc02_04;

import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

abstract class Var implements Operation {
    private static Map<String, Var> vars = new HashMap<>();

    @Override
    public Var add(Var other) throws CalcException {
        throw new CalcException("Operation " + other + "+" + this + " impossible");
    }

    public Var add(Scalar other) throws CalcException {
        throw new CalcException("Operation " + other + "+" + this + " impossible");
    }

    public Var add(Vector other) throws CalcException {
        throw new CalcException("Operation " + other + "+" + this + " impossible");
    }


    @Override
    public Var sub(Var other) throws CalcException {
        throw new CalcException("Operation " + other + "-" + this + " impossible");
    }

    public Var sub(Scalar other) throws CalcException {
        throw new CalcException("Operation " + other + "-" + this + " impossible");
    }

    public Var sub(Vector other) throws CalcException {
        throw new CalcException("Operation " + other + "-" + this + " impossible");
    }


    @Override
    public Var mul(Var other) throws CalcException {
        throw new CalcException("Operation " + other + "*" + this + " impossible");
    }

    public Var mul(Scalar other) throws CalcException {
        throw new CalcException("Operation " + other + "*" + this + " impossible");
    }

    public Var mul(Vector other) throws CalcException {
        throw new CalcException("Operation " + other + "*" + this + " impossible");
    }

    @Override
    public Var div(Var other) throws CalcException {
        throw new CalcException("Operation " + other + "/" + this + " impossible");
    }

    public Var div(Scalar other) throws CalcException {
        throw new CalcException("Operation " + other + "/" + this + " impossible");
    }

    public Var div(Vector other) throws CalcException {
        throw new CalcException("Operation " + other + "/" + this + " impossible");
    }

    @Override
    public String toString() {
        return "abstract Var";
    }

    static Var createVar(String strVar) throws CalcException {
        if (strVar.matches(Patterns.SCALAR))
            return new Scalar(strVar);
        else if (strVar.matches(Patterns.VECTOR))
            return new Vector(strVar);

        else if (vars.containsKey(strVar))
            return vars.get(strVar);
        throw new CalcException("Unknown expression: " + strVar);
    }

    static void saveVar(String key, Var value) {
        vars.put(key, value);
    }

    public static String getStringVars() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String varName : vars.keySet()) {
            stringBuilder.append(varName)
                    .append("=")
                    .append(vars.get(varName))
                    .append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public static void sortAndPrintVars() {
        Set<String> set = new TreeSet<>(vars.keySet());
        for (String s : set) {
            System.out.println(s + "=" + vars.get(s));
        }
    }

    static void saveVarToFile() throws CalcException {
        String fileName = "vars.txt";
        try (PrintWriter writer = new PrintWriter("src/by/it/shulga/jd01/calc02_04/" + fileName)) {
            vars.forEach((key, value) -> writer.printf("%s=%s\n", key, value));
        } catch (FileNotFoundException e) {
            throw new CalcException(e);
        }
    }

    static void loadVarFromFile(Parser parser) throws CalcException {
        String fileName = "vars.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(Paths.get("src/by/it/shulga/jd01/calc02_04/" + fileName).toFile()))) {
            while (reader.ready()) {
                String line = reader.readLine();
                if (line == null) {
                    throw new CalcException("Error reading file");
                }
                parser.calc(line);
            }
        } catch (IOException e) {
            throw new CalcException("error loading data " + e.getMessage());
        }
    }
}
