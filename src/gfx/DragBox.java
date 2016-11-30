package gfx;

import input.Mouse;

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
        if(Mouse.mouseOverBox(this)) highlight(g);
    }


    private void renderBase(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(color.darker());
        g.drawRect(x, y, width - 2, height - 2);
        g.drawRect(x + 1, y + 1, width - 2, height - 2);
    }
    private void highlight(Graphics g){
        g.setColor(Color.black);
        g.drawRect(x + 2, y + 2, width - 5, height - 5);
    }
}