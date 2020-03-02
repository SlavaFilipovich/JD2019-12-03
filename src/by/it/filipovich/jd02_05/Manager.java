package by.it.filipovich.jd02_05;

import java.awt.im.InputContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;


public enum Manager {

    INSTANCE;
    private final String locationDirect = "by.it.filipovich.jd02_05.properties.messages";
    private ResourceBundle resourceBundle;
    private Date date;
    private DateFormat dateFormat;


    Manager(){
        InputContext context = InputContext.getInstance();
        setLocale(context.getLocale());
    }

    String getCurrentTime(Locale locale){
        date = new Date();
        dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        return dateFormat.format(date);
    }

    void setLocale(Locale locale){
        resourceBundle = ResourceBundle.getBundle(locationDirect,locale);
    }

    String get(String key){
        return resourceBundle.getString(key);
    }


}
