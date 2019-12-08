package com.ekkel.gui;

/**
 * Created by 16688641 on 05.04.2019.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.ekkel.generators.Generator;
import com.ekkel.utils.*;
import static com.ekkel.utils.SwingConsole.*;

public class TextPane extends JFrame {
    private JButton b = new JButton("Add Text");
    private JTextPane tp = new JTextPane();
    private static Generator sg = new RandomGenerator.String(7);
    public TextPane() {
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 1; i < 10; i++)
                    tp.setText(tp.getText() + sg.next() + "\n");
            }
        });
        add(new JScrollPane(tp));
        add(BorderLayout.SOUTH, b);
    }
    public static void main(String[] args) {
        run(new TextPane(), 475, 425);
    }
}
