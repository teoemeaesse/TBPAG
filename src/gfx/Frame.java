package gfx;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tomás on 27/11/2016.
 */
public class Frame extends JFrame {
    public static Panel panel = new Panel();

    public Frame(String name, int width, int height){
        super(name);
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - height / 2);
        panel.setIgnoreRepaint(false);
        //add listeners
        add(panel);
        setVisible(true);
    }
}
