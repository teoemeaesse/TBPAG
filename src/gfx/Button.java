package gfx;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Tomás on 28/11/2016.
 */
public class Button extends PanelObject {
    public Button(int x, int y, int width, int height, Color color, ArrayList<PanelObject> textField){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.panelObjects = textField;
    }


    @Override
    public void render(Graphics g){

    }

    @Override
    public void tick(){

    }
}
