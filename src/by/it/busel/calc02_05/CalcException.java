package by.it.busel.calc02_05;

/**
 * a class-exception that is created in order to report about any incompatible mathematical operations or
 * other conditions when mathematical operations can not be performed
 */
@SuppressWarnings("all")
class CalcException extends Exception {
    /**
     * a default constructor
     */
    CalcException() {
    }

    /**
     * a constructor with a String argument, which begins with "ERROR: " (or its equivalent in Russian, Belarusian)
     *
     * @param message
     */
    CalcException(String message) {
        super(String.format("%s ", ResourcesManager.get(Message.CALC_EXCEPTION_MESSAGE_PART)) + message);
    }

    /**
     * a constructor with a String argument, which begins with "ERROR: " (or its equivalent in Russian, Belarusian),
     * and with a Throwable argument, which is a real cause for an exception
     *
     * @param message
     * @param cause
     */
    CalcException(String message, Throwable cause) {
        super(String.format("%s ", ResourcesManager.get(Message.CALC_EXCEPTION_MESSAGE_PART)) + message, cause);
    }

    /**
     * a constructor with a Throwable argument, which is a real cause for an exception
     *
     * @param cause a Throwable argument, which is a real cause for an exception
     */
    CalcException(Throwable cause) {
        super(cause);
    }
}
