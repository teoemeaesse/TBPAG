package data.events.returnable_events;

import data.Event;
import tools.Tools;
import world.map.Map;

/**
 * Created by Tomás on 23/11/2016.
 */
public class EntranceDAEvent extends Event {
    private int x, y;

    public EntranceDAEvent(int x, int y){
        this.x = x;
        this.y = y;
    }


    @Override
    public boolean eventB(){
        boolean allowed = true;

        if(!Map.map[x][y].allowedIn){
            Tools.out("\nYou are not allowed in here.\n\n");
            allowed = false;
        }

        return allowed;
    }
}
