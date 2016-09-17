package data.main_computer;

import data.Component;
import data.main_computer.hardware.CPU;
import game.GameConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomás on 07/18/2016.
 */
public class MainComputer extends Component {
    public static int hardwareCapacity = 3;
    public static List<Hardware> hardware = new ArrayList<>(hardwareCapacity);

    public MainComputer(){
        name = GameConstants.COMPONENT_DRONEHANGAR;
        buildCost = 40;
        baseUpgradeCost = 25;
        level = 1;
        active = false;
    }
}
