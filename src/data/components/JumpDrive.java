package data.components;

import game.Settings;
import tools.Tools;
import data.Component;
import world.map.Map;
import world.map.entities.Ship;

import java.util.Random;

/**
 * Created by Tomás on 07/17/2016.
 */
public class JumpDrive extends Component {
    public static boolean jumping = false;

    public JumpDrive(){
        name = Settings.COMPONENT_JUMPDRIVE;
        buildCost = 40;
        baseUpgradeCost = 25;
        consumption = 10;
        timeTaken = 12;
        level = 1;
    }

    @Override
    public void action(){
        if(active){
            progress++;
            Map.generateTravelGFX();
            if(progress == timeTaken){
                Map.generateSector();
                Tools.out("\nYou have arrived at a new sector!\n\n");
                jumping = false;
                active = false;
                progress = 0;
            }
        }
    }

    public void upgrade(int index){
        if(Ship.resources[4] >= baseUpgradeCost * (level + 0.5)){
            Ship.resources[4] -= baseUpgradeCost * (level + 0.5);
            Ship.components[index].level++;
            consumption *= 0.95;
            Tools.out("\nJump Drive component successfully upgraded, having spent " + baseUpgradeCost * (level + 0.5) + " scrap.\n\n");
        }else{
            Tools.out("\nNot enough scrap to upgrade this component. You need " + baseUpgradeCost * (level + 0.5) + " scrap.");
        }
    }

    @Override
    public void activate(){
        if(Ship.resources[2] - 5 >= consumption){
            Tools.out("\nBeginning light-speed jump... Keep your eyes peeled for malfunctions.\n\n");
            jumping = true;
            active = true;
        }else{
            Tools.out("\nNot enough fuel to safely make the jump to another sector. You need " + consumption + " fuel.\n\n");
        }
    }

    @Override
    public void deactivate(){
        active = false;
    }
}
