package by.it.kuzmichalex.calc_jd02_05;

class KeyWords {
    static final String EXIT = "exit";
    static final String QUIT = "quit";
    static final String END = "end";
    static final String PRINTVAR = "printvar";
    static final String SORTVAR = "sortvar";
    static final String LANGUAGE_EN = "en";
    static final String LANGUAGE_BY = "by";
    static final String LANGUAGE_BE = "be";
    static final String LANGUAGE_RU = "ru";
    static final String ALL_KEY_WORDS = "," + EXIT + "," + QUIT + "," + END + "," + PRINTVAR + "," + SORTVAR + "," + LANGUAGE_EN + "," + LANGUAGE_BY + "," + LANGUAGE_BE + "," + LANGUAGE_RU + ",";

    /**
     * @param strExpression - string parameter
     * @return true when string parameter is keyword
     */
    static boolean isKeyWord(String strExpression) {
        return ALL_KEY_WORDS.contains("," + strExpression.toLowerCase() + ",");
    }
}
