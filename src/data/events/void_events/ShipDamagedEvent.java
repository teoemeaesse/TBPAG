package data.events.void_events;

import data.Event;
import game.Game;
import tools.Tools;
import world.map.entities.Ship;

/**
 * Created by Tomás on 11/10/2016.
 */
public class ShipDamagedEvent extends Event {
    private int damage;

    public ShipDamagedEvent(int damage){
        this.damage = damage;
    }

    @Override
    public void event(){
        Ship.hullPoints -= damage;
        if(Ship.hullPoints <= 0){
            Game.alive = false;
            Tools.out("\n\nYour ship took too much damage! It's going t---------------------\n\n");
        }
    }
}
