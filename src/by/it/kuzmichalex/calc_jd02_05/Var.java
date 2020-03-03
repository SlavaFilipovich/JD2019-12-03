package by.it.kuzmichalex.calc_jd02_05;


import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

abstract class Var implements Operation {
    /*
     * array for mapping
     * Key - Var name
     * Value - Var value
     * */
    private static Map<String, Var> mapVars = new HashMap<>();

    public static Var getCreatedVar(String expression) throws CalcException {
        Var returnVar = mapVars.get(expression);
        if (returnVar == null) throw new CalcException(LanguageManager.get(Messages.variable_not_defined) + expression);
        return returnVar;
    }

    /**
     * override Object.toString method
     */
    @Override
    public String toString() {
        return ("abstract class Var");
    }


    /**
     * convert all Vars to String
     *
     * @return String like VarName=Value\n
     */
    static String printVars() {
        StringBuilder retValue = new StringBuilder();
        if (mapVars.isEmpty()) return (LanguageManager.get(Messages.no_variables_defined));
        Set<Map.Entry<String, Var>> entries = mapVars.entrySet();
        for (Map.Entry<String, Var> varMapValue : entries) {
            retValue.append(varMapValue.getKey()).append("=");
            retValue.append(varMapValue.getValue().toString());
            retValue.append('\n');
        }
        return retValue.toString();
    }

    /**
     * convert all Vars to String witch sorting by key
     *
     * @return String like VarName=Value\n
     */
    static String sortVars() {
        StringBuilder retValue = new StringBuilder();
        if (mapVars.isEmpty()) return (LanguageManager.get(Messages.no_variables_defined));
        TreeMap<String, Var> sortedVars = new TreeMap<>(mapVars);
        Set<Map.Entry<String, Var>> entries = sortedVars.entrySet();
        for (Map.Entry<String, Var> nextSorted : entries) {
            retValue.append(nextSorted.getKey()).append("=").append(nextSorted.getValue().toString()).append('\n');
        }
        return retValue.toString();
    }

    /**
     *
     */
    static void saveVarToFile() {
        String sFileName = FHelper.getFileName("vars.txt", Var.class);
        try (PrintWriter pw = new PrintWriter(new FileWriter(sFileName))
        ) {
            String sVars = sortVars();
            pw.print(sVars);
        } catch (IOException e) {
            Logger.printAndLog(LanguageManager.get(Messages.error_save_variables) + sFileName);
        }
    }

    static void loadVarsFromFile() {
        String sFileName = FHelper.getFileName("vars.txt", Var.class);
        Parser tmpParser = new Parser();
        try (BufferedReader br = new BufferedReader(new FileReader(sFileName))) {
            while (true) {
                String readedString = br.readLine();
                if(readedString==null)break;
                Logger.printAndLog(LanguageManager.get(Messages.executing_from_file) + readedString);
                tmpParser.calc(readedString);
            }
        } catch (IOException e) {
            Logger.printAndLog(LanguageManager.get(Messages.error_reading_variables) + sFileName);
        } catch (CalcException e) {
            Logger.printAndLog(LanguageManager.get(Messages.error_parsing_variables) + e.getMessage());
        }
    }


    /////////// Methods for add operation
    @Override
    public Var add(Var rightOperand) throws CalcException {
        return null;
    }

    public Var add(Scalar leftOperand) throws CalcException {
        throw new CalcException(LanguageManager.get(Messages.operation) + leftOperand + " + " + this + LanguageManager.get(Messages.impossible));
    }

    public Var add(Vector leftOperand) throws CalcException {
        throw new CalcException(LanguageManager.get(Messages.operation) + leftOperand + " + " + this + LanguageManager.get(Messages.impossible));
    }

    public Var add(Matrix leftOperand) throws CalcException {
        throw new CalcException(LanguageManager.get(Messages.operation) + leftOperand + " + " + this + LanguageManager.get(Messages.impossible));
    }

    /////////// Methods for sub operation
    @Override
    public Var sub(Var rightOperand) throws CalcException {
        throw new CalcException(LanguageManager.get(Messages.operation) + this + " - " + rightOperand + LanguageManager.get(Messages.impossible));
    }

    public Var sub(Scalar leftOperand) throws CalcException {
        throw new CalcException(LanguageManager.get(Messages.operation) + leftOperand + " - " + this + LanguageManager.get(Messages.impossible));
    }

    public Var sub(Vector leftOperand) throws CalcException {
        throw new CalcException(LanguageManager.get(Messages.operation) + leftOperand + " - " + this + LanguageManager.get(Messages.impossible));
    }

    public Var sub(Matrix leftOperand) throws CalcException {
        throw new CalcException(LanguageManager.get(Messages.operation) + leftOperand + " - " + this + LanguageManager.get(Messages.impossible));
    }

    /////////// Methods for mul operation
    @Override
    public Var mul(Var rightOperand) throws CalcException {
        return null;
    }

    public Var mul(Scalar leftOperand) throws CalcException {
        throw new CalcException(LanguageManager.get(Messages.operation) + leftOperand + " * " + this + LanguageManager.get(Messages.impossible));
    }

    public Var mul(Vector leftOperand) throws CalcException {
        throw new CalcException(LanguageManager.get(Messages.operation) + leftOperand + " * " + this + LanguageManager.get(Messages.impossible));
    }

    public Var mul(Matrix leftOperand) throws CalcException {
        throw new CalcException(LanguageManager.get(Messages.operation) + leftOperand + " * " + this + LanguageManager.get(Messages.impossible));
    }

    /////////// Methods for div operation
    @Override
    public Var div(Var rightOperand) throws CalcException {
        return null;
    }

    public Var div(Scalar leftOperand) throws CalcException {
        throw new CalcException(LanguageManager.get(Messages.operation) + leftOperand + " / " + this + LanguageManager.get(Messages.impossible));
    }

    public Var div(Vector leftOperand) throws CalcException {
        throw new CalcException(LanguageManager.get(Messages.operation) + leftOperand + " / " + this + LanguageManager.get(Messages.impossible));
    }

    public Var div(Matrix leftOperand) throws CalcException {
        throw new CalcException(LanguageManager.get(Messages.operation) + leftOperand + " / " + this + LanguageManager.get(Messages.impossible));
    }

    /**
     * save Var and value into HashMap mapVars
     *
     * @param key - var name. A, B, counter, superPuperVar etc
     */
    static void save(String key, Var value) throws CalcException {
        if (!key.matches(Patterns.VARNAME)) throw new CalcException(LanguageManager.get(Messages.invalid_variable_name) + key);
        if (KeyWords.isKeyWord(key))
            throw new CalcException(LanguageManager.get(Messages.invalid_variable_name_no_keywords) + KeyWords.ALL_KEY_WORDS);
        mapVars.put(key, value);
        saveVarToFile();
    }
}
