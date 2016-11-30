package gfx;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Tomás on 28/11/2016.
 */
public abstract class PanelObject {
    public int x, y, width, height;
    public String title, text;
    public Color color;
    public ArrayList<PanelObject> panelObjects = new ArrayList<>();


    public abstract void render(Graphics g);

    public abstract void tick();
}
