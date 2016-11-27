package thread.threads;

import data.components.JumpDrive;
import thread.GameThread;
import tools.Tools;
import world.map.Map;
import world.map.entities.Ship;

import java.util.Random;

/**
 * Created by Tomás on 08/10/2016.
 */
public class JumpThread extends GameThread {
    @Override
    public void action(){
        if(JumpDrive.jumping){
            Random r = new Random();
            Map.generateSector(false, Tools.intToBool(r.nextInt(2)), Tools.intToBool(r.nextInt(2)));
            Tools.out("\nSuccessfully jumped to another sector. Check map to scan it.\n\n");
            stop();
        }
    }

    @Override
    public void setFps(){
        fps = 1.0 / new JumpDrive().timeTaken;
        timePerTick = 1000000000 / fps;
    }
}
