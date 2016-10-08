package data.main_computer.hardware;

import data.main_computer.Hardware;
import game.GameConstants;
import tools.Tools;
import world.map.entities.Ship;

/**
 * Created by Tomás on 05/10/2016.
 */
public class SSD extends Hardware {
    public int memory;

    public SSD(int memory){
        name = "RAM";
        tag = "CARGO_" + Ship.hardwareStorage.size();
        value = memory * GameConstants.BASE_VALUE_SSD;
        this.memory = memory;
    }

    @Override
    public void describe(){
        Tools.out("\n\nType: " + name + "\n\nMemory size: " + memory + "Gb\n\nValue: " + value + "$");
    }
}
