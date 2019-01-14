package com.stepic.generic2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        List<Integer> out = new ArrayList<>();

        int count = 0;
        while (scanner.hasNextInt()){
            int i = scanner.nextInt();
            if (count++ % 2 != 0) {
                out.add(i);
            }
        }
        Collections.reverse((List<?>) out);
        out.forEach(x-> {
            if (sb.length() != 0) sb.append(" ");
            sb.append(x);
        });
        System.out.println(sb.toString());
    }
}
