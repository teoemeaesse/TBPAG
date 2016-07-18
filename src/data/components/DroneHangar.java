package data.components;

import tools.Tools;
import data.Component;
import world.map.entities.Ship;

/**
 * Created by Tomás on 16/06/2016.
 */
public class DroneHangar extends Component {
    public DroneHangar(){
        name = "Drone Hangar";
        description = "This holds all your drones. Upgrading will increase drone capacity.";
        buildCost = 40;
        baseUpgradeCost = 25;
        level = 1;
        active = false;

        Ship.droneCapacity++;
    }

    public void upgrade(int index){
        if(Ship.scrap >= baseUpgradeCost * (level + 0.5)){
            Ship.scrap -= baseUpgradeCost * (level + 0.5);
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