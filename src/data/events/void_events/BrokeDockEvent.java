package data.events.void_events;

import data.Event;
import data.main_computer.MainComputer;
import game.GameConstants;

import java.util.Random;

/**
 * Created by Tomás on 11/10/2016.
 */
public class BrokeDockEvent extends Event {
    private int x, y;

    public BrokeDockEvent(int x, int y){
        this.x = x;
        this.y = y;
    }


    @Override
    public void event(){
        Random r = new Random();
        int fine = r.nextInt(50 - 10) + 10;

        if(r.nextInt((int) Math.round(MainComputer.hardware[0].value / GameConstants.BASE_VALUE_CPU + 10)) == 0){
            fireEvents(new DemandPayEvent(
                    fine,
                    "\nYou destroyed the docking hatch! The post demands that you pay " + fine + "$. Will you pay? (y/any other key)\n\n",
                    "\nYou hand over the " + fine + "$ and are cleared to dock.\n\n",
                    "\nThey inform you that you are no longer welcome at this trading post.\n\n",
                    "\nYou don't have enough money to pay the fine.\nYou are informed that you are no longer allowed in the trading post.\n\n",
                    x,
                    y
                    ));
        }
    }
}
