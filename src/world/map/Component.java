package world.map;


import tools.Tools;
/**
 * Created by Tomás on 12/04/2016.
 */
public class Component {
    public String name, description, produceType;
    public double buildCost, baseUpgradeCost, consumption, produce, timeTaken, level;
    public boolean active = false;

    public static Component[] components;

    public void activate(){
        active = true;
        Tools.out("\n" + name + " component has been activated.\n\n");
    }
    public void deactivate(){
        active = false;
        Tools.out("\n" + name + " component has been deactivated.\n\n");
    }
}
