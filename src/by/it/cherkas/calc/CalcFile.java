package by.it.cherkas.calc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class CalcFile {
    static private String fullFileName = fileFullName(Var.class, "vars.txt");

    public static String getFullFileName(){
        return fullFileName;
    }

    static void readValue(Parser parser) throws IOException{
        Files.lines(Paths.get(fullFileName)).forEach(o ->{
            try {
                parser.calc(o);
            }
            catch (CalcException e) {
                e.printStackTrace();
            }
        });
    }

    static void writeValue(Map<String, Var> valueMap) throws CalcException{
        try (PrintWriter printWriter = new PrintWriter(fullFileName)){
            for (Map.Entry<String, Var> entry : valueMap.entrySet()){
                printWriter.printf("%s=%s\n", entry.getKey(), entry.getValue().toString());
            }
        }
        catch (FileNotFoundException e) {
            throw new CalcException("ERROR writeToFile");
        }
    }

    static String fileFullName(Class<?> taskClass, String fileName){
        String projectDir = System.getProperty("user.dir");
        String classPath = taskClass.getName().replace(taskClass.getSimpleName(), "")
                .replace(".", File.separator);
        return projectDir + File.separator + "src" + File.separator + classPath + fileName;
    }
}
