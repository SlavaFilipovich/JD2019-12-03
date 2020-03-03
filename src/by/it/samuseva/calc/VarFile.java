package by.it.samuseva.calc;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

class VarFile {
    private static String varFileName = getPath("vars.txt", VarFile.class);
    static String getPath(String fileName, Class<?> aClass) {
        String pathDir = System.getProperty("user.dir") + File.separator + "src" + File.separator;
        String pathFolder = aClass.getName().replace(aClass.getSimpleName(), "").replace(".", File.separator);
        return pathDir+pathFolder+fileName;
    }

    static void writer (Map<String, Var> vars) throws CalcException {
        try {
            try (PrintWriter writer = new PrintWriter(varFileName)) {
                for (Map.Entry<String, Var> var : vars.entrySet()){
                    Var valye = var.getValue();
                    writer.printf("%s=%s\n", var.getKey(), valye);
                }
            } catch (FileNotFoundException e) {
                throw new CalcException(varFileName+" error", e);
            }
        }catch (Exception e) {
            e.getStackTrace();
        }

    }

    static void load (){

       /* try {
            Parset parset = new Parset();
            Files.lines(Paths.get(varFileName)).forEach(expression -> {
                try {
                    parset.calc(expression);
                } catch (CalcException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
           // ex.printStackTrace();
        }*/


       try (BufferedReader reader = new BufferedReader(new FileReader(varFileName))){
            Parset parset = new Parset();
            String line;
            while ((line=reader.readLine())!= null){
                try {
                    parset.calc(line);
                } catch (CalcException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
