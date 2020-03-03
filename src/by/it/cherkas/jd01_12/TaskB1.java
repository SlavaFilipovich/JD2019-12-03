package by.it.cherkas.jd01_12;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TaskB1 {
    public static void main(String[] args) {
        Map<String, Integer> map=new HashMap<>();
        Scanner sc=new Scanner(System.in);
        while (true){
            String line=sc.nextLine();
            if (line.equals("end"))
                break;
                String[] words = line.split("[^a-zA-Z']+");
                for (String word : words) {
                    if (word.equals("end"))
                        break;
                        if (map.containsKey(word)){
                            Integer integer=map.get(word);
                            integer++;
                            map.put(word, integer);
                        }
                        else {
                            map.put(word, 1);
                        }
                    }
                }
                for (Map.Entry<String, Integer>entry:map.entrySet()){
                    System.out.println(entry.getKey()+"="+entry.getValue());
                }
            }
        }



