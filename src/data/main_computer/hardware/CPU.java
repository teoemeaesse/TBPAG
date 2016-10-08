package data.main_computer.hardware;

import data.main_computer.Hardware;
import game.GameConstants;
import tools.Tools;
import world.map.entities.Ship;

/**
 * Created by Tomás on 07/18/2016.
 */
public class CPU extends Hardware {
    public double frequency;
    public int cores;

    public CPU(double frequency, int cores){
        name = "CPU";
        tag = "CARGO_" + Ship.hardwareStorage.size();
        value = frequency * cores * GameConstants.BASE_VALUE_CPU;
        this.frequency = frequency;
        this.cores = cores;
    }

    @Override
    public void describe(){
        Tools.out("\n\nType: " + name + "\n\nFrequency: " + frequency + "GHz\nCores: " + cores + "\n\nValue: " + value + "$");
    }
}
