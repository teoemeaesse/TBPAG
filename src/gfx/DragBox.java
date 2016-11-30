package gfx;

import input.Mouse;
import tools.Tools;

import java.awt.*;

/**
 * Created by Tomás on 29/11/2016.
 */
public class DragBox extends Rectangle {
    public Color color;

    public DragBox(int x, int y, int width, int height, Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }


    public void update(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(Graphics g){
        renderBase(g);
        if(mouseOverBox()) highlight(g);
    }


    private boolean mouseOverBox(){
        boolean mouseOver = false;

        if(intersects(new Rectangle(Mouse.x, Mouse.y))) mouseOver = true;

        return mouseOver;
    }
    private void renderBase(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(color.darker());
        g.drawRect(x, y, width - 2, height - 2);
        g.drawRect(x + 1, y + 1, width - 2, height - 2);
    }
    private void highlight(Graphics g){
        g.setColor(color.darker());
        g.drawRect(x + 2, y + 2, width - 3, height - 3);
    }
}