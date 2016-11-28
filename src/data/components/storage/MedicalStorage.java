package data.components.storage;

import game.Settings;
import tools.Tools;
import data.Component;
import world.map.entities.Ship;

/**
 * Created by Tomás on 07/18/2016.
 */
public class MedicalStorage extends Component {
    public MedicalStorage(){
        name = Settings.COMPONENT_MEDICALSTORAGE;
        buildCost = 20;
        baseUpgradeCost = 12.5;
        level = 1;

        Ship.resourceCapacities[3] += level * 10;
    }

    public void upgrade(int index){
        if(Ship.resources[4] >= baseUpgradeCost * (level + 0.5)){
            Ship.resources[4] -= baseUpgradeCost * (level + 0.5);
            Ship.components[index].level++;
            Ship.resourceCapacities[3] += 20;
            Tools.out("\nMedical Storage component successfully upgraded, having spent " + baseUpgradeCost * (level + 0.5) + " scrap.\n\n");
        }else{
            Tools.out("\nNot enough scrap to upgrade this component. You need " + baseUpgradeCost * (level + 0.5) + " scrap.");
        }
    }
}
