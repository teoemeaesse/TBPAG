package world.map.components;

import tools.Tools;
import world.map.Component;
import world.map.entities.Ship;

/**
 * Created by Tomás on 12/04/2016.
 */
public class Hydroponics extends Component {
    public Hydroponics(){
        name = "Hydroponics";
        description = "This transforms water into food via plant growing.\n";
        produceType = "food";//USEFUL ONLY FOR DESCRIPTION
        buildCost = 20;
        baseUpgradeCost = 12.5;
        consumption = 2;
        produce = 1;
        timeTaken = 1;
        level = 1;
        active = false;
    }

    public void hydroponics(){
        if(Ship.water >= 5 + 2){
            Ship.water -= consumption * (level / 2 + 0.5);
            Ship.food += produce * (level / 2 + 0.5);
            Tools.out("\n\nYour hydroponics ship component just produced " + produce * (level / 2 + 0.5) + " food, having consumed " + consumption * (level / 2 + 0.5) + " water.\n\n");
        }else{
            Tools.out("\n\nNot enough water in your mother-ship to safely continue plant growing.\n\n");
        }
    }

    public void upgrade(int index){
        if(Ship.scrap >= baseUpgradeCost * (level + 0.5)){
            Ship.scrap -= baseUpgradeCost * (level + 0.5);
            Ship.components[index].level++;
        }else{
            Tools.out("\nNot enough scrap to upgrade this component.\n\n");
        }
    }
}