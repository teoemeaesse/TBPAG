package gfx;

import game.Settings;
import input.Mouse;
import tools.Tools;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Tomás on 28/11/2016.
 */
public class Window extends PanelObject {
    public DragBox dragBox;

    public Window(int x, int y, int width, int height, Color color, ArrayList<PanelObject> panelObjects) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.panelObjects = panelObjects;
        dragBox = new DragBox(x, y, width, Settings.HEIGHT_DRAG_BOX, color.brighter().brighter());
    }


    @Override
    public void render(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, width, height);
        dragBox.render(g);
        for(int i = 0; i < panelObjects.size(); i++) panelObjects.get(i).render(g);
    }

    @Override
    public void tick(){
        mouseDraggingDragBox();
        dragBox.update(x, y, width, Settings.HEIGHT_DRAG_BOX);
        for(int i = 0; i < panelObjects.size(); i++) panelObjects.get(i).tick();
    }


    private void mouseDraggingDragBox(){
        if(Mouse.mouseOverBox(dragBox) && Mouse.down){
            x = (Mouse.x - Mouse.lastX);
            y = (Mouse.y - Mouse.lastY);
        }
    }
}