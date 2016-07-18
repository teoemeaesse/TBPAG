package data.components.storage;

import tools.Tools;
import data.Component;
import world.map.entities.Ship;

/**
 * Created by Tomás on 07/17/2016.
 */
public class WaterStorage extends Component {
    public WaterStorage(){
        name = "Water Storage";
        description = "This stores water.\n";
        buildCost = 16;
        baseUpgradeCost = 10;
        level = 1;

        Ship.waterCapacity += level * 30;
    }

    public void upgrade(int index){
        if(Ship.scrap >= baseUpgradeCost * (level + 0.5)){
            Ship.scrap -= baseUpgradeCost * (level + 0.5);
            Ship.components[index].level++;
            Ship.waterCapacity += 20;
            Tools.out("\nWater Storage component successfully upgraded, having spent " + baseUpgradeCost * (level + 0.5) + " scrap.\n\n");
        }else{
            Tools.out("\nNot enough scrap to upgrade this component. You need " + baseUpgradeCost * (level + 0.5) + " scrap.");
        }
    }
}
