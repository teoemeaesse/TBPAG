package data.components.production;

import game.GameConstants;
import tools.Tools;
import data.Component;
import world.map.entities.Ship;

/**
 * Created by Tomás on 12/04/2016.
 */
public class Hydroponics extends Component {
    public Hydroponics(){
        name = GameConstants.COMPONENT_HYDROPONICS;
        buildCost = 20;
        baseUpgradeCost = 12.5;
        consumption = 2;
        produce = 1;
        timeTaken = 180;
        level = 1;
    }

    public void hydroponics(){
        if(Ship.resources[0] >= 5 + 2){
            Ship.resources[0] -= consumption * (level / 2 + 0.5);
            Ship.resources[1] += produce * (level / 2 + 0.5);
            Tools.out("\nYour hydroponics ship component just produced " + produce * (level / 2 + 0.5) + " food, having consumed " + consumption * (level / 2 + 0.5) + " water.\n\n");
        }else{
            Tools.out("\nNot enough water in your mother-ship to safely continue plant growing.\n\n");
        }
    }

    public void upgrade(int index){
        if(Ship.resources[4] >= baseUpgradeCost * (level + 0.5)){
            Ship.resources[4] -= baseUpgradeCost * (level + 0.5);
            Ship.components[index].level++;
            consumption += 0.1;
            produce += 0.3;
            Tools.out("\nHydroponics component successfully upgraded, having spent " + baseUpgradeCost * (level + 0.5) + " scrap.\n\n");
        }else{
            Tools.out("\nNot enough scrap to upgrade this component. You need " + baseUpgradeCost * (level + 0.5) + " scrap.");
        }
    }
}