package thread.threads;

import game.Game;
import thread.GameThread;

/**
 * Created by Tomás on 27/11/2016.
 */
public class DisplayThread extends GameThread {
    public DisplayThread(){
        fps = 60;
    }

    @Override
    public void action(){
        Game.tick();
        Game.render();
    }
}
