package com.other.f1;


import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Tester implements Serializable {
    static class MyThread extends Thread {
        public void run() {
            System.out.print("Thread ");
        }
    }

    public static void main(String[] args) throws IOException {

        StringBuilder result = new StringBuilder();

        //BufferedReader bf = new BufferedReader(new FileReader(new File("C:\\Users\\16688641\\Desktop\\answers.txt")));
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\16688641\\Desktop\\answers.txt")));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bf.readLine()) != null) {
            sb.append(line);
        }
        Iterator regexHeplerIter = new RegexHepler(sb.toString(), "SINGLE QUESTION(.+?)question-summary-score").iterator();
        while (regexHeplerIter.hasNext()) {
            String currentQuestionString = (String) regexHeplerIter.next();
            if (currentQuestionString.contains("status-correct")) {
                System.out.println("Правильно");
                result.append("Правильно\r\n");
            } else if (currentQuestionString.contains("status-mistake")){
                    System.out.println("Неправильно");
                result.append("Неправильно\r\n");
            }else {
                System.out.println("Ошибка разбора");
                result.append("Ошибка разбора\r\n");
            }

            Iterator questionIterator = new RegexHepler(currentQuestionString, "<span class=\"task-number\">\\d+.</span>(.+?)</p>").iterator();
            String question = ((String) questionIterator.next()).replaceAll("\\s+", " ");
            System.out.println(question);
            result.append(question + "\r\n");
            Iterator answerIterator = new RegexHepler(currentQuestionString, "<li>(.+?)</li>").iterator();
            while (answerIterator.hasNext()) {
                boolean selected = false;

                String answer = ((String) answerIterator.next()).replaceAll("\\s+", " ");
                if (answer.contains("fa-circle")) {
                    System.out.print("+ ");
                    result.append("+ ");
                }
                System.out.println(answer.replaceAll("<.+?>", ""));
                result.append(answer.replaceAll("<.+?>", "") + "\r\n");
            }
            result.append("========================================================================\r\n");
            //break;
        }
        FileWriter nFile = new FileWriter("C:\\Users\\16688641\\Desktop\\answers_out.txt");
        nFile.write(result.toString());
        nFile.close();
    }
}
