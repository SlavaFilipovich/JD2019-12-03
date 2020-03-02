package by.it.filipovich.jd02_05;

import java.util.Locale;
import java.util.ResourceBundle;


public enum Manager {

    INSTANCE;
    private final String locationDirect = "by.it.filipovich.jd02_05.properties.messages";
    private ResourceBundle resourceBundle;

    Manager(){
        setLocale(new Locale("en","US"));
    }

    void setLocale(Locale locale){
        resourceBundle = ResourceBundle.getBundle(locationDirect,locale);
    }

    String get(String key){
        return resourceBundle.getString(key);
    }


}
