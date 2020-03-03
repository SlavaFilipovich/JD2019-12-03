package by.it.busel.calc02_05;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * a class that prints a value of Var objects in a console and logs all datum which are both inputted and outputted
 */
class Printer {
    /**
     * a static object, which defines an unchanging format of Date objects that contains present time
     */
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    /**
     * a method that print a Var expression according to its type (Scalar, Vector, Matrix).
     *
     * @param variable a Var expression called to be printed
     */
    void print(Var variable) {
        System.out.println(variable);
    }

    /**
     * a method that print a Var expression according to its type (Scalar, Vector, Matrix) in a console
     * and logs all information that concerns one separate input and related to it output
     *
     * @param input        an expression which was inputted in a console
     * @param resultOutput an expression that contains a result of processing of a previously mentioned input
     */
    void printAndSave(String input, String resultOutput) {
        System.out.println(resultOutput);
        Logger.save(formLog(input, resultOutput));
    }

    /**
     * a method that forms log on the basis of input and output of a console.
     * <p>
     * A contamination of String objects (instead of StringBuilder) is used
     * because constant parts of each log are taken from "a String-pool".
     * <p>
     * Inlining is also used in order to deduce text overload.
     *
     * @param input   an input to a console
     * @param element an output from a console
     * @return one separate log that is formed on the basis of input and output of a console
     */
    static String formLog(String input, String element) {
        return dateFormat.format(new Date()) + '\n' + ResourcesManager.get(Message.PRINTER_INPUT)
                + '\n' + input + '\n' + ResourcesManager.get(Message.PRINTER_OUTPUT) +
                '\n' + element + '\n';
    }
}
