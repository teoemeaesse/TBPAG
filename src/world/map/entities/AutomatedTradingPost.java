package world.map.entities;

import data.Event;
import data.events.MissedDockingEvent;
import game.GameConstants;
import world.map.Tile;

/**
 * Created by Tomás on 08/10/2016.
 */
public class AutomatedTradingPost extends Tile {
    public static String icon = GameConstants.MAP_AMTP, name = GameConstants.NAME_ATP;

    public AutomatedTradingPost(){
        super.name = name;
        super.icon = icon;
    }

    @Override
    public void interact(){
        Event.fireEvents(new MissedDockingEvent());
    }
}
