package thread.threads;

import data.components.production.Hydroponics;
import thread.GameThread;
import world.map.entities.Ship;

/**
 * Created by Tomás on 07/05/2016.
 */
public class HydroponicsThread extends GameThread {
    @Override
    public void action(){
        for(int i = 0; i < Ship.components.length; i++){
            if(Ship.components[i] != null && Ship.components[i].name.equals("Hydroponics") && Ship.components[i].active){
                ((Hydroponics) Ship.components[i]).hydroponics();
            }
        }
    }

    @Override
    public void setFps(){
        for(int i = 0; i < Ship.components.length; i++){
            if(Ship.components[i] != null && Ship.components[i].name.equals("Hydroponics")){
                fps = 1.0 / Ship.components[i].timeTaken;
                timePerTick = 1000000000 / fps;
            }
        }
    }
}
