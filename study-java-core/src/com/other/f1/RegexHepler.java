package com.other.f1;

import com.ekkel.innerclass.Outer;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 16688641 on 21.10.2019.
 */
public class RegexHepler implements Iterable<String> {
    private String text;
    private Pattern pattern;
    private Matcher matcher;
    public int i=1;

    RegexHepler(String text, String stringPattern){
        this.text = text;
        this.pattern = Pattern.compile(stringPattern);
        this.matcher = pattern.matcher(text);
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            boolean alreadyFetchNext = false;
            boolean lastResult = false;
            @Override
            public boolean hasNext() {
                if (alreadyFetchNext){
                    return lastResult;
                }
                alreadyFetchNext = true;
                lastResult = matcher.find();
                return lastResult;
            }

            @Override
            public String next() {
                if (alreadyFetchNext && lastResult) {
//                    return text.substring(matcher.start(), matcher.end());
                    alreadyFetchNext = false;
                    lastResult = false;
                    return matcher.group(1);
                }else {
                    if (matcher.find()) {
//                        return text.substring(matcher.start(), matcher.end());
                        return matcher.group(1);
                    }
                }
                return null;
            }
        };
    }
}
