package by.it.borodachev.Calc;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Var implements Operation {
     private static Map<String, Var> field=new HashMap<>();

     @Override
     public String toString() {
          return "Var{}";
     }

     @Override
     public Var add(Var newValue) throws CalcException {
          throw new CalcException(LanguageManager.get(ErrorMessage.imposible_operation)+ " "+ this + " + " + newValue );
     }

     @Override
     public Var sub(Var newValue) throws CalcException {
          throw new CalcException(LanguageManager.get(ErrorMessage.imposible_operation)+ " "+ this + " - " + newValue );
     }

     @Override
     public Var mul(Var newValue) throws CalcException {
          throw new CalcException(LanguageManager.get(ErrorMessage.imposible_operation)+ " "+ this + " * " + newValue );
     }

     @Override
     public Var div(Var newValue) throws CalcException{
          throw new CalcException(LanguageManager.get(ErrorMessage.imposible_operation)+ " "+ this + " / " + newValue );
     }
     static Var createVar(String strVar) throws CalcException {
          if (strVar.matches(Patterns.SCALAR))
          {return new Scalar(strVar);}
          else if (strVar.matches(Patterns.VECTOR))
          {return new Vector(strVar);}
          else if (strVar.matches(Patterns.MATRIX))
          {return new Matrix(strVar);}
          else if(field.containsKey(strVar))  // Сначала поищем в локальных переменных
          {return field.get(strVar);}
         else
           throw new CalcException(LanguageManager.get(ErrorMessage.unknown_operation)+strVar);
          //TODO generate error here
     }
     static void save(String key, Var value) {
          field.put(key,value);
     }

     static void printvar() {
          for (Map.Entry<String, Var> stringVarEntry : field.entrySet()) {
               System.out.println(stringVarEntry.getKey()+"="+stringVarEntry.getValue());
          }
     }
     static void savevar() throws CalcException {
          String fileName=Helper.getPath("vars.txt",Var.class);
          try (PrintWriter writer = new PrintWriter(fileName)) {
               for (Map.Entry<String, Var> stringVarEntry : field.entrySet()) {
                    writer.println(stringVarEntry.getKey() + "=" + stringVarEntry.getValue());
               }
          } catch (IOException e) {throw new CalcException(e.getMessage());}
     }
     static void loadvar() throws CalcException {
          String fileName = Helper.getPath("vars.txt", Var.class);
          field.clear();
          Parser parser = new Parser();
          try (BufferedReader inLines = new BufferedReader(new FileReader(fileName))) {
               String inputLine;
               while ((inputLine = inLines.readLine()) != null) {
                    parser.calc(inputLine);
               }
          }
          catch (FileNotFoundException fnotexista ) {return;}
          catch (IOException e) { throw new CalcException(e.getMessage());}
     }
     static void sortvar() {
          Set<String> strings = field.keySet();
          Object[] objects = strings.toArray();
          Arrays.sort(objects);
          for (Object object : objects) {
               System.out.println(object+"="+field.get(object));
          }
     }
}
