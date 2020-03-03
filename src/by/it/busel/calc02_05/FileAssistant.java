package by.it.busel.calc02_05;

import java.io.File;

/**
 * a class that performs activities concerning files.
 * At the present time is still being developed
 */
class FileAssistant {
    /**
     * a method that forms a path for a file depending on the arguments inputted
     *
     * @param filename       a file name of a file
     * @param classStructure a flag that helps to find an absolute path for a file beginning from a root
     * @param <T>            any class, which structure will be used in order to find an absolute path for a file
     *                       beginning from a root
     * @return a String representation of an absolute path for a file including a name of a file
     */
    static <T> String generateFilePath(String filename, Class<T> classStructure) {
        StringBuilder builder = new StringBuilder();
        String packageName = classStructure.getName().replace(classStructure.getSimpleName(), "")
                .replace(".", File.separator);
        builder.append(System.getProperty("user.dir"))
                .append(File.separator)
                .append("src")
                .append(File.separator)
                .append(packageName)
                .append(File.separator)
                .append(filename);
        return builder.toString();
    }
}
