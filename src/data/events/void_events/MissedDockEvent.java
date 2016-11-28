package data.events.void_events;

import data.Event;
import data.main_computer.MainComputer;
import game.Settings;
import tools.Tools;

import java.util.Random;

/**
 * Created by Tomás on 08/10/2016.
 */
public class MissedDockEvent extends Event {
    @Override
    public void event(){
        Random r = new Random();

        if(r.nextInt((int) Math.round(MainComputer.hardware[0].value / Settings.BASE_VALUE_CPU + 6)) == 0){
            int damageTaken = r.nextInt(Settings.MISSED_DOCK_DAMAGE_MAX - Settings.MISSED_DOCK_DAMAGE_MIN) + Settings.MISSED_DOCK_DAMAGE_MIN;
            Tools.out("\nYou missed the docking hatch! You took " + damageTaken + " damage to your hull.\n\n");

            fireEvents(new ShipDamagedEvent(damageTaken));
        }
    }
}
