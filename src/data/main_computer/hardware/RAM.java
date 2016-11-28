package data.main_computer.hardware;

import data.Hardware;
import game.Settings;
import tools.Tools;
import world.map.entities.Ship;

/**
 * Created by Tomás on 18-Sep-16.
 */
public class RAM extends Hardware {
    public int memory;

    public RAM(int memory){
        name = "RAM";
        tag = "CARGO_" + Ship.hardwareStorage.size();
        value = memory * Settings.BASE_VALUE_RAM;
        this.memory = memory;
    }

    @Override
    public void describe(){
        Tools.out("\n\nType: " + name + "\n\nMemory size: " + memory + "Gb\n\nValue: " + value + "$");
    }
}
