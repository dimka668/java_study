package com.ekkel.oi;

import java.util.prefs.Preferences;

public class PreferencesDemo {
    public static void main(String[] args) {
        Preferences prefs = Preferences.userNodeForPackage(PreferencesDemo.class);
        int i = prefs.getInt("count", 0);
        System.out.println(i);
        prefs.putInt("count", i+1);

    }
}
