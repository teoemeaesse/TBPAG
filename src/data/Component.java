package world.map;


import tools.Tools;
import world.map.components.DroneHangar;
import world.map.components.production.Hydroponics;
import world.map.components.JumpDrive;
import world.map.components.production.ScrapProcessor;
import world.map.components.storage.FoodStorage;
import world.map.components.storage.MedicalStorage;
import world.map.components.storage.ScrapStorage;
import world.map.components.storage.WaterStorage;
import world.map.entities.Ship;

/**
 * Created by Tomás on 12/04/2016.
 */
public class Component {
    public String name, description;
    public double buildCost, baseUpgradeCost, consumption = 0, produce = 0, timeTaken = 0, level;
    public boolean active = false;

    public static final Component[] components = new Component[]{new Hydroponics(), new DroneHangar(), new JumpDrive(),
                                                                 new WaterStorage(), new FoodStorage(), new MedicalStorage(), new ScrapStorage(), new WaterStorage(),
                                                                 new ScrapProcessor()};

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
