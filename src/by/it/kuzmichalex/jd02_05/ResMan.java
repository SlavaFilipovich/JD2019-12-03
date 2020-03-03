package by.it.kuzmichalex.jd02_05;

import java.util.Locale;
import java.util.ResourceBundle;

enum ResMan {
    INSTANCE;

    private String locationRes = "by.it.kuzmichalex.jd02_05.properties.Messages";

    private ResourceBundle resourceBundle;

    ResMan() {
        setLocale(Locale.ENGLISH);
    }

    void setLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(locationRes, locale);
    }

    String get(String key) {
        return resourceBundle.getString(key);
    }

}
