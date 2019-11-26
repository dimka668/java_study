package com.sbt.pattern.iterator;

import java.util.Iterator;

/**
 * Created by SBT-Klyshov-DA on 27.06.2018.
 */
public class Main {
    public static void main(String[] args) {
        Word.Root root = new Word.Root("беж");
        Word.Prefix prefix = new Word.Prefix("пере");
        Word.Suffix suffix = new Word.Suffix("к");
        Word.Ending ending = new Word.Ending("a");

        Word word = new Word(root, prefix, suffix, ending);

        Iterator wordIterator = word.iterator();
        while (wordIterator.hasNext()) {
            Word.WordPart part = (Word.WordPart) wordIterator.next();
            System.out.println(part.getClass() + ": " + part.getWordPart());
        }
    }
}
