package by.it.cherkas.jd01_14;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskB {
    public static void main(String[] args) {
        final StringBuffer stringBuffer = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(Helper.getPath("text.txt", TaskB.class)));
        BufferedWriter writer = new BufferedWriter(new FileWriter(Helper.getPath("resultTaskB.txt", TaskB.class)))) {
            while (reader.ready()) {
                stringBuffer.append(reader.readLine() + "\n");
            }
            Matcher matcher = Pattern.compile("[а-яА-ЯёЁ]+").matcher(stringBuffer);
            int words = 0;
            while (matcher.find()) {
                words++;
            }
            matcher = Pattern.compile("[\\p{Punct}]+").matcher(stringBuffer);
            int punctuationMarks = 0;
            while (matcher.find()) {
                punctuationMarks++;
            }
                System.out.println("words=" + words + ", punctuation marks=" + punctuationMarks);
                writer.write("words=" + words + ", punctuation marks=" + punctuationMarks);
            }

        catch (IOException e) {
            e.printStackTrace();
        }

        }
    }

