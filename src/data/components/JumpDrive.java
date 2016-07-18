package data.components;

import tools.Tools;
import data.Component;
import world.map.Map;
import world.map.entities.Ship;

/**
 * Created by Tomás on 07/17/2016.
 */
public class JumpDrive extends Component {
    public JumpDrive(){
        name = "Jump Drive";
        description = "Used for jumping between sectors.\n";
        buildCost = 40;
        baseUpgradeCost = 25;
        consumption = 10;
        level = 1;
    }

    public void jumpDrive(){
        if(Ship.fuel - 5 >= consumption){
            Map.generateSector();
            Tools.out("\nSuccessfully jumped to another sector. Check map to scan it.\n\n");
        }else{
            Tools.out("\nNot enough fuel to safely make the jump to another sector.\n\n");
        }
    }

    public void upgrade(int index){
        if(Ship.scrap >= baseUpgradeCost * (level + 0.5)){
            Ship.scrap -= baseUpgradeCost * (level + 0.5);
            Ship.components[index].level++;
            consumption *= 0.95;
            Tools.out("\nJump Drive component successfully upgraded, having spent " + baseUpgradeCost * (level + 0.5) + " scrap.\n\n");
        }else{
            Tools.out("\nNot enough scrap to upgrade this component. You need " + baseUpgradeCost * (level + 0.5) + " scrap.");
        }
    }

    @Override
    public void activate(){
        jumpDrive();
    }
}
