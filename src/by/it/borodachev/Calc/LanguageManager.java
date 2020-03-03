package by.it.borodachev.Calc;
import java.util.Locale;
import java.util.ResourceBundle;

enum ResourceManager {
    INSTANCE;
    private String locationRes = Helper.getLocalePath(Var.class);
    private ResourceBundle resourceBundle;
    ResourceManager() {
        setLocale(Locale.ENGLISH);
    }
    void setLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(locationRes, locale);
    }
    String get(String key) {
        return resourceBundle.getString(key);
    }
}

public class LanguageManager {
    static private ResourceManager resource;
    static private Locale locale;

    static {
        Locale locale = new Locale(System.getProperty("user.language"), System.getProperty("user.country"));
        resource = ResourceManager.INSTANCE;
        resource.setLocale(locale);
    }

    /**
     * set language
     *
     * @param language = en, by, ru
     */
    static void setLanguage(String language) {
        if (language.toLowerCase().equals("en")) {
            locale = new Locale("en", "US");
            resource.setLocale(locale);
        } else if (language.toLowerCase().equals("by") || language.toLowerCase().equals("be")) {
            locale = new Locale("by", "BY");
            resource.setLocale(locale);
        } else if (language.toLowerCase().equals("ru")) {
            locale = new Locale("ru", "RU");
            resource.setLocale(locale);
        }
    }

    static String get(String str) {
        return resource.get(str)+" ";
    }
}
