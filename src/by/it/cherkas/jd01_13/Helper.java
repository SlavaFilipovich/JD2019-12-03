package by.it.cherkas.jd01_13;

public class Helper {

    static void printExeption(RuntimeException e, String thisClassName) {
        String name = e.getClass().getName();
        String className = "";
        int strNum = 0;
        StackTraceElement [] stackTrace = e.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace){
            if (stackTraceElement.getClassName().equals(thisClassName)){
                className = stackTraceElement.getClassName();
                strNum = stackTraceElement.getLineNumber();
            }
        }
        System.out.printf(" name: %s\nclass: %s\n line: %d\n", name, className,strNum);
    }
}
