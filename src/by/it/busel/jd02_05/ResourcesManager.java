package by.it.busel.jd02_05;

import java.util.Locale;
import java.util.ResourceBundle;

enum ResourcesManager {
    INITIALIZE;

    private ResourceBundle resourceBundle;

    private final String resourceBundleName = generateBundleName(".resources.internationalization");

    ResourcesManager() {
        resourceBundle = ResourceBundle.getBundle(resourceBundleName, new Locale("en", "GB"));
    }

    void setLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(resourceBundleName, locale);
    }

    Locale getLocale() {
        return resourceBundle.getLocale();
    }

    String get(String resManKey) {
        return resourceBundle.getString(resManKey);
    }

    void informAboutCurrentLocale() {
        System.out.printf("The current language is %s\n", resourceBundle.getLocale().getDisplayLanguage());
    }

    private String generateBundleName(String fileName) {
        return ResourcesManager.class.getPackage().getName() + fileName;
    }

}
