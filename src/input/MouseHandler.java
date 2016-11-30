package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Tomás on 30/11/2016.
 */
public class MouseHandler implements MouseListener {
    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {
        Mouse.down = true;
        Mouse.lastX = Mouse.x;
        Mouse.lastY = Mouse.y;
    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {
        Mouse.down = false;
    }
}
