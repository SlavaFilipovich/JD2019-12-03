package by.it.lozouski.jd02_05;

import java.util.Locale;
import java.util.ResourceBundle;

enum ChangeLanguageService {
    START;

    private ResourceBundle resourceBundle;

    ChangeLanguageService() {
        setLocale(Locale.ENGLISH);
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    void setLocale(Locale locale) {
        String locationPath = "by.it.lozouski.jd02_05.txt.messages";
        resourceBundle = ResourceBundle.getBundle(locationPath, locale);
    }

    String get(String key) {
        return resourceBundle.getString(key);
    }
}
