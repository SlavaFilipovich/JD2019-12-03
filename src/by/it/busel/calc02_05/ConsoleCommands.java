package by.it.busel.calc02_05;

import java.util.Locale;

/**
 * a class which contains main commands that can be called in order to perform some actions with
 * var expressions that were assigned and saved during previous executions of "Calc project"
 */
class ConsoleCommands {
    /**
     * a String containing character data, to which an input is being compared, and which defines whether
     * a user of "Calc" wants to view all assigned and saved var expressions
     */
    private static final String PRINTVAR = "PRINTVAR";

    /**
     * a String containing character data, to which an input is being compared,
     * and which defines whether a user of "Calc" wants to
     * view all assigned and saved var expressions in a sorted by names order
     */
    private static final String SORTVAR = "SORTVAR";

    /**
     * a Locale object, which is used when all exception messages or other statement shall be in Belarusian
     */
    private static final Locale localeBLR = new Locale("be", "BY");

    /**
     * a Locale object, which is used when all exception messages or other statement shall be in English.
     * This Locale is a default one.
     * Moreover, country "GB" is chosen, because it learnt British English
     */
    private static final Locale localeGB = new Locale("en", "GB");

    /**
     * a Locale object, which is used when all exception messages or other statement shall be in Russian
     */
    private static final Locale localeRU = new Locale("ru", "RU");

    /**
     * a method which verifies if one of "Calc" commands is called.
     * At the present they are:
     * 1) "printvar" - prints all saved Var-expressions;
     * 2) "sortvar" - prints all saved Var-expressions in an ascending order;
     * 3) "be" - changes a language of the Calc application to Belarusian;
     * 4) "ru" - changes a language of the Calc application to Russian;
     * 5) "en" - changes a language of the Calc application to British English;
     *
     * @param varExpression a String which potentially can contain a "Calc" command
     * @return a boolean expression that states "true" when one of "Calc" commands was called
     */
    public static boolean call(String varExpression) {
        if (varExpression.toUpperCase().equals(ConsoleCommands.PRINTVAR)) {
            Storage.printvar();
        } else if (varExpression.toUpperCase().equals(ConsoleCommands.SORTVAR)) {
            Storage.sortvar();
        } else if (varExpression.length() == 2) {
            switch (varExpression) {
                case "be":
                    ResourcesManager.setLocale(localeBLR);
                    break;
                case "en":
                    ResourcesManager.setLocale(localeGB);
                    break;
                case "ru":
                    ResourcesManager.setLocale(localeRU);
                    break;
            }
        } else {
            return false;
        }
        return true;
    }
}
