package data.components.storage;

import game.Settings;
import tools.Tools;
import data.Component;
import world.map.entities.Ship;

/**
 * Created by Tomás on 07/18/2016.
 */
public class ScrapStorage extends Component {
    public ScrapStorage(){
        name = Settings.COMPONENT_SCRAPSTORAGE;
        buildCost = 16;
        baseUpgradeCost = 10;
        level = 1;

        Ship.resourceCapacities[4] += level * 20;
    }

    public void upgrade(int index){
        if(Ship.resources[4] >= baseUpgradeCost * (level + 0.5)){
            Ship.resources[4] -= baseUpgradeCost * (level + 0.5);
            Ship.components[index].level++;
            Ship.resourceCapacities[4] += 20;
            Tools.out("\nScrap Storage component successfully upgraded, having spent " + baseUpgradeCost * (level + 0.5) + " scrap.\n\n");
        }else{
            Tools.out("\nNot enough scrap to upgrade this component. You need " + baseUpgradeCost * (level + 0.5) + " scrap.");
        }
    }
}
