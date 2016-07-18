package data.components.storage;

import tools.Tools;
import data.Component;
import world.map.entities.Ship;

/**
 * Created by Tomás on 07/18/2016.
 */
public class MedicalStorage extends Component {
    public MedicalStorage(){
        name = "Medical Storage";
        description = "This stores medical equipment.\n";
        buildCost = 20;
        baseUpgradeCost = 12.5;
        level = 1;

        Ship.medicalEquipmentCapacity += level * 10;
    }

    public void upgrade(int index){
        if(Ship.scrap >= baseUpgradeCost * (level + 0.5)){
            Ship.scrap -= baseUpgradeCost * (level + 0.5);
            Ship.components[index].level++;
            Ship.medicalEquipmentCapacity += 20;
            Tools.out("\nMedical Storage component successfully upgraded, having spent " + baseUpgradeCost * (level + 0.5) + " scrap.\n\n");
        }else{
            Tools.out("\nNot enough scrap to upgrade this component. You need " + baseUpgradeCost * (level + 0.5) + " scrap.");
        }
    }
}
