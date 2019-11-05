package com.ekkel.gui;

/**
 * Created by 16688641 on 03.04.2019.
 */
import javax.swing.*;
import java.awt.*;
import static com.ekkel.utils.SwingConsole.*;

public class Button1 extends JFrame {
    private JButton
            bl = new JButton("Button 1"),
            b2 = new JButton("Button 2");
    public Button1() {
        setLayout(new FlowLayout());
        add(bl);
        add(b2);
    }
    public static void main(String[] args) {
        run(new Button1(), 260, 106);
    }
}

