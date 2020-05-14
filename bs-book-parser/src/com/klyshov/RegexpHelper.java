package com.klyshov;

import sun.font.TrueTypeFont;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpHelper implements Iterable<String>{

    public String text;
    public String patternString;
    private int num = 0;

    public Pattern pattern;
    public Matcher matcher;
    public Iterator<String> iterator;
    public boolean currentLineReaded = false;

    public RegexpHelper(String text, String patternString, int num){
        this.text = text;
        this.patternString = patternString;
        this.num = num;
        this.pattern = Pattern.compile(this.patternString);
        this.matcher = this.pattern.matcher(this.text);
    }

    public RegexpHelper(String text, String patternString){
        this(text, patternString, 0);
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                // Если hasNext() с matcher.find() уже вызывался
                if (currentLineReaded){
                    return true;
                }
                currentLineReaded = true;
                return matcher.find();
            }

            @Override
            public String next() {
                // Если find уже вызывался через hasNext(), то возвращаем строку
                if (currentLineReaded){
                    currentLineReaded = false;
                    //return text.substring(matcher.start(), matcher.end());
                    return matcher.group(1);
                }
                // Если find не вызывался через hasNext(), то сначала ищем
                if (matcher.find()) {
                    //return text.substring(matcher.start(), matcher.end());
                    return matcher.group(1);
                }else{
                    return null;
                }
            }
        };
    }

}
