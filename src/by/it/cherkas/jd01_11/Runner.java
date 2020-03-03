package by.it.cherkas.jd01_11;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        ArrayList<String> list=new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        System.out.println(list);
        list.remove(2);
        System.out.println(list);

        List<String> list1=new ListA<>();
        list1.add("one");
        list1.add("two");
        list1.add("three");
        list1.add("four");
        System.out.println(list1);
        list1.remove(2);
        System.out.println(list1);
    }

}