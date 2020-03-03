package by.it.ban.calc;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ResManager {
    INSTANCE;

    private ResourceBundle rb;
    private Locale locale;

    ResManager() {
        setLocale(Locale.getDefault());
    }

    void setLocale(Locale locale) {
        this.locale = locale;
        String path = "by.it.ban.calc.lang.translation";
        rb = ResourceBundle.getBundle(path, this.locale);
    }

    Locale getLocale() {
        return this.locale;
    }

    public String get(String key) {
        return rb.getString(key);
    }
}