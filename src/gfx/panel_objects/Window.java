package gfx.panel_objects;

import gfx.PanelObject;
import input.Mouse;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Tomás on 28/11/2016.
 */
public class Window extends PanelObject {
    public Rectangle dragBox;

    public Window(int x, int y, int width, int height, Color color, ArrayList<PanelObject> panelObjects) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.panelObjects = panelObjects;
        dragBox = new Rectangle(x, y, width, height);
    }


    @Override
    public void render(Graphics g){
        renderWindow(g);
        for(int i = 0; i < panelObjects.size(); i++) panelObjects.get(i).render(g);
    }

    @Override
    public void tick(){
        updateDragBox();
        mouseDraggingDragBox();
        for(int i = 0; i < panelObjects.size(); i++) panelObjects.get(i).tick();
    }


    private void mouseDraggingDragBox(){
        if(Mouse.mouseOverBox(dragBox) && Mouse.down){
            x += (Mouse.x - Mouse.lastX);
            y += (Mouse.y - Mouse.lastY);
            Mouse.lastX = Mouse.x;
            Mouse.lastY = Mouse.y;
        }
    }
    private void updateDragBox(){
        dragBox.x = x;
        dragBox.y = y;
    }
    private void renderWindow(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(color.darker().darker());
        g.drawRect(x, y, width, height);
        g.drawRect(x + 1, y + 1, width - 2, height - 2);
    }
}