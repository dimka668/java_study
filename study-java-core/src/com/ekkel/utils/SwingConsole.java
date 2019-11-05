package com.ekkel.utils;

import javax.swing.*;

/**
 * Created by 16688641 on 03.04.2019.
 */
public class SwingConsole {
    public static void run(final JFrame f, final int width, final int height) {
        SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                f.setTitle(f.getClass().getSimpleName());
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(width, height);
                f.setVisible(true);
            }
        });
    }
}
