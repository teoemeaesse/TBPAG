package data.components;

import game.Settings;
import tools.Tools;
import data.Component;
import world.map.entities.Ship;

/**
 * Created by Tomás on 16/06/2016.
 */
public class DroneHangar extends Component {
    public DroneHangar(){
        name = Settings.COMPONENT_DRONEHANGAR;
        buildCost = 40;
        baseUpgradeCost = 25;
        level = 1;
        active = false;

        Ship.droneCapacity++;
    }

    public void upgrade(int index){
        if(Ship.resources[4] >= baseUpgradeCost * (level + 0.5)){
            Ship.resources[4] -= baseUpgradeCost * (level + 0.5);
            Ship.components[index].level++;
            Ship.droneCapacity++;
            Tools.out("\nDrone Hangar component successfully upgraded, having spent " + baseUpgradeCost * (level + 0.5) + " scrap.\n\n");
        }else{
            Tools.out("\nNot enough scrap to upgrade this component. You need " + baseUpgradeCost * (level + 0.5) + " scrap.");
        }
    }





    /**
     * Ship.drones = Tools.increaseArraySize(Ship.drones);
     * MiningThread.dronesToCheck = Tools.increaseArraySize(MiningThread.dronesToCheck);
     *
     * USE TO BUILD DRONE
     **/
}