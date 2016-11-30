package thread.threads;

import game.Game;
import thread.GameThread;
import tools.Tools;

/**
 * Created by Tomás on 27/11/2016.
 */
public class DisplayThread extends GameThread {
    public DisplayThread(){
        fps = 60;
        timePerTick = 1000000000 / fps;
    }

    @Override
    public void action(){
        Game.tick();
        Game.render();
    }
}
