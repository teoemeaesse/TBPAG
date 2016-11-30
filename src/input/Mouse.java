package input;

import gfx.Frame;

import java.awt.*;

/**
 * Created by Tomás on 30/11/2016.
 */
public class Mouse {
    public static int x, y, lastX, lastY;
    public static boolean down = false;

    public static void update(){
        x = MouseInfo.getPointerInfo().getLocation().x - Frame.getWindows()[0].getX() - 2;
        y = MouseInfo.getPointerInfo().getLocation().y - Frame.getWindows()[0].getY() - 32;
    }


    public static boolean mouseOverBox(Rectangle r){
        boolean mouseOver = false;
        if(r.intersects(new Rectangle(x, y, 1, 1))) mouseOver = true;
        return mouseOver;
    }
}
