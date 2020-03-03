package by.it.kuzmichalex.calc_jd02_05;

 class CalcException extends Exception {
    public CalcException() {
        super();
    }

    public CalcException(String message) {
        super(LanguageManager.get(Messages.error) + message);
    }

    public CalcException(String message, Throwable cause) {
        super(message, cause);
    }

    public CalcException(Throwable cause) {
        super(cause);
    }

    protected CalcException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

