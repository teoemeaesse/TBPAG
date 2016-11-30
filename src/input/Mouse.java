package input;

import gfx.Frame;

import java.awt.*;

/**
 * Created by Tomás on 30/11/2016.
 */
public class Mouse {
    public static int x, y;

    public static void update(){
        x = MouseInfo.getPointerInfo().getLocation().x + Frame.getWindows()[0].getX();
        y = MouseInfo.getPointerInfo().getLocation().y + Frame.getWindows()[0].getY();
    }
}
