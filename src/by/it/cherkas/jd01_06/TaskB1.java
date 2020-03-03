package by.it.cherkas.jd01_06;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskB1 {
    private static String[] words=new String[0];
    private static int[] counters=new int[0];

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[а-яА-ЯёЁ]+");
        Matcher matcher = pattern.matcher(Poem.text);
        while (matcher.find()) {
            if (ok(matcher.group()))
                System.out.println(matcher.group());

        }
    }
            private static boolean ch (char vowels) {
                char[] chars = {'а', 'е', 'ё', 'о', 'и', 'ы', 'у', 'ю', 'я', 'А', 'Е', 'Ё', 'О', 'И', 'Ы', 'У', 'Ю', 'Я'};
                for (int i = 0; i < chars.length; i++) {
                    if (vowels == chars[i])
                        return true;
                }
                return false;
            }
            private static boolean ok(String words){
        char first=words.charAt(0);
        char last=words.charAt(words.length()-1);
        return (ch(last) & ch(first)==false);

            }
}