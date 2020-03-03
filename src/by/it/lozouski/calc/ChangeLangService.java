package by.it.lozouski.calc;

import java.util.Locale;
import java.util.ResourceBundle;

enum ChangeLangService {
    START;
    private ResourceBundle resourceBundle;
    private final String resourceName = "by.it.lozouski.calc.calcTxtFiles.messages";

    ChangeLangService() {
        resourceBundle = ResourceBundle.getBundle(resourceName, new Locale("en","US"));
    }

    void setLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(resourceName, locale);
        Logging.logFileRecord(ConsoleRunner.langService.get(Messages.MES_CURRENT_LANG));
    }

    public String get(String key) {
        return resourceBundle.getString(key);
    }
}
