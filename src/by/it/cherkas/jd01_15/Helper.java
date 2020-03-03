package by.it.cherkas.jd01_15;

import java.io.File;

public class Helper {
        static String getPath(String fileName, Class<?> aClass){
            String dir = System.getProperty("user.dir");
            String strPackage = aClass.getName().replace(aClass.getSimpleName(), "");
            String path = dir+ File.separator+"src"+File.separator+strPackage.replace(".", File.separator);
            fileName = path + fileName;
            return fileName;
        }
}
