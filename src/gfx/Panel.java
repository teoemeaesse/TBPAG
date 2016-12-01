package gfx;

import game.Settings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Tomás on 27/11/2016.
 */
public class Panel extends JPanel {
    public static ArrayList<PanelObject> panelObjects = new ArrayList<>();

    public Panel(){
        ArrayList<PanelObject> panelObjects = new ArrayList<>();


        this.panelObjects.add(
                new gfx.panel_objects.Window(
                        30,
                        30,
                        800,
                        450,
                        Color.GREEN.darker(),
                        panelObjects
                        )
        );
    }


    @Override
    public void paint(Graphics g){
        renderBackground(g);
        renderPanelObjects(g);

        g.dispose();
    }


    private void renderPanelObjects(Graphics g){
        for(int i = 0; i < panelObjects.size(); i++) panelObjects.get(i).render(g);
    }
    private void renderBackground(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Settings.width, Settings.height);
    }
}
