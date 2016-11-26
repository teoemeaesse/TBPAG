package data.events.void_events;

import data.Event;
import data.events.void_events.ShipDamagedEvent;
import data.main_computer.MainComputer;
import game.GameConstants;
import tools.Tools;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Tomás on 08/10/2016.
 */
public class MissedDockEvent extends Event {
    @Override
    public void event(){
        Random r = new Random();

        if(r.nextInt((int) Math.round(MainComputer.hardware[0].value / GameConstants.BASE_VALUE_CPU + 6)) == 0){
            int damageTaken = r.nextInt(GameConstants.MISSED_DOCK_DAMAGE_MAX - GameConstants.MISSED_DOCK_DAMAGE_MIN) + GameConstants.MISSED_DOCK_DAMAGE_MIN;
            Tools.out("\nYou missed the docking hatch! You took " + damageTaken + " damage to your hull.\n\n");

            fireEvents(new ShipDamagedEvent(damageTaken));
        }
    }
}
