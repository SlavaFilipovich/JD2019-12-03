package by.it.cherkas.jd01_12;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskA3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> arr = new ArrayList<>();
        int pos = 0;
        while (true) {
            String s = sc.next();
            if (s.equals("end")) break;
            Integer value = Integer.valueOf(s);
            if (value < 0)
                arr.add(value);
            else if (value == 0)
                arr.add(pos, value);
            else
                arr.add(pos++, value);
        }
        System.out.println(arr);

    }
    }

