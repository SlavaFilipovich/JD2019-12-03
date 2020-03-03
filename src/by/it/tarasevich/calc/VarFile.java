package by.it.tarasevich.calc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class VarFile {
    private static String varFile = getPath("vars.txt", VarFile.class);

    static String getPath(String fileName, Class<?> aClass) {
        String root = System.getProperty("user.dir");
        String strPackage = aClass.getName().replace(aClass.getSimpleName(), "");
        String path = root + File.separator + "src" + File.separator + strPackage.replace(".", File.separator);
        fileName = path + fileName;
        return fileName;
    }

    static void load(){
        try {
            Parser parser = new Parser();
            Files.lines(Paths.get(varFile))
                    .forEach(expression -> {
                        try {
                            parser.calc(expression);
                        } catch (CalcException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {

        }
    }


    static void save(Map<String, Var> vars) throws CalcException {
        try {
            try (PrintWriter writer = new PrintWriter(varFile)){
                for (Map.Entry<String, Var> entry : vars.entrySet()) {
                    writer.printf("%s=%s\n",entry.getKey(),entry.getValue());
                }
            }
        } catch (FileNotFoundException e) {
            throw new CalcException(varFile+" error",e);

        }
    }
}
