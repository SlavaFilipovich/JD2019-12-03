package by.it.popkov.jd02_05;

import java.util.Locale;
import java.util.ResourceBundle;

enum  LangSwitcher  {
    LANG_SWITCHER;
    private String baseName = LangSwitcher.class.getName()
            .replace(LangSwitcher.class.getSimpleName(), "") + "resource.hello";
    private ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, Locale.US);

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public void setResourceBundle(Locale locale) {
        this.resourceBundle = ResourceBundle.getBundle(baseName, locale);
    }
}
