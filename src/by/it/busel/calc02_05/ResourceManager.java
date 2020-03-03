package by.it.busel.calc02_05;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * a class which manages all activities concerning a language of this Calc application
 */
class ResourcesManager {
    /**
     * a field which manages locale-specific objects according to current specified Locale
     */
    static private ResourceBundle resourceBundle;
    /**
     * a "package"-path for ".properties"-files, which contains String data in Belarusian, Russian, English
     */
    private static final String resourceBundleName = generateBundleName(".resources.errors");

    /**
     * a default-constructor, which sets a default Locale of a user,
     * whom computer this Calc application is being executed on
     */
    ResourcesManager() {
        resourceBundle = ResourceBundle.getBundle(resourceBundleName, Locale.getDefault());
    }

    /**
     * a method that changes a language of this Calc application
     *
     * @param locale a Locale which is to be set as main
     */
    static void setLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(resourceBundleName, locale);
    }

    /**
     * a method which returns a current Locale of this Calc application
     *
     * @return a current Locale of this Calc application
     */
    static Locale getLocale() {
        return resourceBundle.getLocale();
    }

    /**
     * a method which returns a String for the given key from this resource bundle in Belarusian, Russian or English,
     * depending on a current Locale
     *
     * @param resManKey a key for a desired String
     * @return a String which contains String data, which is a pair for a key "resManKey"
     */
    static String get(String resManKey) {
        return resourceBundle.getString(resManKey);
    }

    /**
     * a method by invoking of which the current language of the application
     * is printed to System.out Print Stream
     * in Belarusian, Russian or English depending on the current Locale
     */
    static void informAboutCurrentLocale() {
        System.out.printf(resourceBundle.getString(Message.CURRENT_LANGUAGE),
                resourceBundle.getLocale().getDisplayLanguage());
    }

    /**
     * a method that returns a String, which contains a "package"-path for ".properties"-files
     *
     * @param fileName a String, which contains a folder, where ".properties"-files is stored,
     *                 and a base-name for them
     * @return a String, which contains a "package"-path for ".properties"-files
     */
    private static String generateBundleName(String fileName) {
        return ResourcesManager.class.getPackage().getName() + fileName;
    }
}