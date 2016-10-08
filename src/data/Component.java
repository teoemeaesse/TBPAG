package data;


import data.components.drone.DroneModifier;
import tools.Tools;
import data.components.DroneHangar;
import data.components.JumpDrive;
import data.components.production.Hydroponics;
import data.components.production.ScrapProcessor;
import data.components.storage.FoodStorage;
import data.components.storage.MedicalStorage;
import data.components.storage.ScrapStorage;
import data.components.storage.WaterStorage;
import world.map.entities.Ship;

/**
 * Created by Tomás on 12/04/2016.
 */
public class Component {
    public String name;
    public double buildCost = 0, baseUpgradeCost = 0, consumption = 0, produce = 0, timeTaken = 0, level;
    public boolean active = false;

    public static final Component[] components = new Component[]{new Hydroponics(), new DroneHangar(), new JumpDrive(),
                                                                 new WaterStorage(), new FoodStorage(), new MedicalStorage(),
                                                                 new ScrapStorage(), new WaterStorage(), new ScrapProcessor(),
                                                                 new DroneModifier()};

    public void activate() throws Exception {
        active = true;
        Tools.out("\n" + name + " component has been activated.\n\n");
    }
    public void deactivate(){
        active = false;
        Tools.out("\n" + name + " component has been deactivated.\n\n");
    }
    public void scrap(){
        Tools.out("\n" + name + " has been scrapped for " + buildCost * 0.75 + " scrap.\n\n");
        Ship.scrap += buildCost * 0.75;
    }
}
