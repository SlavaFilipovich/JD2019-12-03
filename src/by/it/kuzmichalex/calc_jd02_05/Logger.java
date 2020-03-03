package by.it.kuzmichalex.calc_jd02_05;

import java.io.*;
import java.util.ArrayDeque;

class Logger {
   static private ArrayDeque<String> logList = new ArrayDeque<>();
   static private String sLogFileName = FHelper.getFileName("log.txt", Logger.class);

   /**
    * load log into ArrayDeque
    */
   static void loadLog() {
       try (BufferedReader br = new BufferedReader(new FileReader(sLogFileName))) {
           logList.clear();
           String logString;
           while (true) {
               logString = br.readLine();
               if (logString == null) break;
               logList.addLast(logString);
               if (logList.size() > 50) logList.removeFirst();
           }
       } catch (IOException e) {
           System.out.println(LanguageManager.get(Messages.error_read_log) + e.getMessage());
       }
   }

   /**
    *
    */
   static void printAndLog(String sMessage) {
       System.out.println(sMessage);
       logList.addLast(sMessage);
       if (logList.size() > 50) logList.removeFirst();

       try (PrintWriter pw = new PrintWriter(new FileWriter(sLogFileName))
       ) {
           for (String s : logList) {
               pw.print(s);
               if (!s.endsWith("\n")) pw.println();
           }
       } catch (IOException e) {
           System.out.println(LanguageManager.get(Messages.error_write_log) + e.getMessage());
       }
   }
}
