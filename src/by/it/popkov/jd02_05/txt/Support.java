package by.it.popkov.jd02_05.txt;

import java.io.File;

class Support {
    public static String fileFullName(Class<?> taskClass, String fileName) {
        String projectDir = System.getProperty("user.dir");
        String classPath = taskClass.getName()
                .replace(taskClass.getSimpleName(), "")
                .replace(".", File.separator);
        return projectDir + File.separator + "src" + File.separator + classPath + fileName;
    }
}
