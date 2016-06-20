package world.map.components;

import threads.MiningThread;
import tools.Tools;
import world.map.Component;
import world.map.entities.Drone;
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
    }

    public void upgrade(){
        if(Ship.scrap >= baseUpgradeCost * (level + 0.5)){
            Ship.drones = (Drone[]) Tools.increaseArraySize(Ship.drones);
            MiningThread.dronesToCheck = Tools.increaseArraySize(MiningThread.dronesToCheck);
        }else{
            Tools.out("\nNot enough scrap to upgrade this component.\n\n");
        }
    }
}