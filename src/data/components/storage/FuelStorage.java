package data.components.storage;

import tools.Tools;
import data.Component;
import world.map.entities.Ship;

/**
 * Created by Tomás on 07/18/2016.
 */
public class FuelStorage extends Component {
    public FuelStorage(){
        name = "Fuel Storage";
        description = "This stores fuel.\n";
        buildCost = 16;
        baseUpgradeCost = 10;
        level = 1;

        Ship.fuelCapacity += level * 30;
    }

    public void upgrade(int index){
        if(Ship.scrap >= baseUpgradeCost * (level + 0.5)){
            Ship.scrap -= baseUpgradeCost * (level + 0.5);
            Ship.components[index].level++;
            Ship.fuelCapacity += 20;
            Tools.out("\nFuel Storage component successfully upgraded, having spent " + baseUpgradeCost * (level + 0.5) + " scrap.\n\n");
        }else{
            Tools.out("\nNot enough scrap to upgrade this component. You need " + baseUpgradeCost * (level + 0.5) + " scrap.");
        }
    }
}
