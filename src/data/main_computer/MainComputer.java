package data.main_computer;

import data.main_computer.hardware.CPU;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomás on 07/18/2016.
 */
public class MainComputer {
    public static int hardwareCapacity = 3;
    public static List<Hardware> hardware = new ArrayList<>(hardwareCapacity);

    public MainComputer(){
        hardware.add(new CPU(0.5, 1));
    }
}
