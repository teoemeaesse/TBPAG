package gfx;

import game.Settings;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tomás on 27/11/2016.
 */
public class Panel extends JPanel {
    @Override
    public void paint(Graphics g){
        drawBackground(g);
    }


    private void drawBackground(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Settings.width, Settings.height);
    }
}
