package by.it.cherkas.jd01_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class TaskB2 {
    public static void main(String[] args) throws IOException {
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(input.readLine());
        ArrayList<String> peopleAL=new ArrayList<String>();
        LinkedList<String> peopleLL=new LinkedList<>();
        for (int i = 0; i <n ; i++) {
            String name=input.readLine();
            peopleAL.add(name);
            peopleLL.add(name);
        }
        System.out.println(process(peopleAL));
        System.out.println(process(peopleLL));
    }
    static String process(ArrayList<String>peoples){
        int i=1;
        while (peoples.size()!=1){
            Iterator<String> iterator=peoples.iterator();
            while (iterator.hasNext()){
                iterator.next();
                if ((i%2)==0){
                    iterator.remove();
                }
                i++;
            }
        }
        return String.valueOf(peoples.get(0));
    }
    static String process(LinkedList<String>peoples) {
        int i = 1;
        while (peoples.size() != 1) {
            Iterator<String> iterator = peoples.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                if ((i % 2) == 0) {
                    iterator.remove();
                }
                i++;
            }
        }
        return String.valueOf(peoples.get(0));
    }
}
