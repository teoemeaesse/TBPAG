package world.map.entities;

import data.DronePart;
import tools.Tools;
import world.map.Map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomás on 06/04/2016.
 */
public class Drone {
    //public static final int INACTIVE = 0, SCRAP_MINER = 1, GATHERER = 2, ASTEROID_MINER = 3;
    public int x, y;
    public boolean mining = false;
    public static double[] resourceCapacities = {20, 20, 20, 4, 30};
    public double[] resources = new double[5];
    public double miningProgress = 0;
    //public int type = INACTIVE;
    public List<DronePart> parts = new ArrayList<>();

    public Drone(){
        x = Ship.x;
        y = Ship.y;
    }

    public void displayDroneStatus(int index){
        Tools.out("/--------------------------------------|\n" + "| Coordinates: " + Ship.drones.get(index).x + " - " + Ship.drones.get(index).y);
        for(int i = 0; i < 24 - (Ship.drones.get(index).x + " - " + Ship.drones.get(index).y).length(); i++){Tools.out(" ");}

        Tools.out("|\n|                                      |\n| Water: " + Ship.drones.get(index).resources[0] + " / " + resourceCapacities[0]);
        for(int i = 0; i < 24 - (Ship.drones.get(index).resources[0] + resourceCapacities[0] + "").length(); i++){Tools.out(" ");}

        Tools.out("|\n| Food: " + Ship.drones.get(index).resources[1] + " / " + resourceCapacities[1]);
        for(int i = 0; i < 25 - (Ship.drones.get(index).resources[1] + resourceCapacities[1] + "").length(); i++){Tools.out(" ");}

        Tools.out("|\n| Fuel: " + Ship.drones.get(index).resources[2] + " / " + resourceCapacities[2]);
        for(int i = 0; i < 25 - (Ship.drones.get(index).resources[2] + resourceCapacities[2] + "").length(); i++){Tools.out(" ");}

        Tools.out("|\n| Medical equipment: " + Ship.drones.get(index).resources[3] + " / " + resourceCapacities[3]);
        for(int i = 0; i < 12 - (Ship.drones.get(index).resources[3] + resourceCapacities[3] + "").length(); i++){Tools.out(" ");}

        Tools.out("|\n| Scrap: " + Ship.drones.get(index).resources[4] + " / " + resourceCapacities[4]);
        for(int i = 0; i < 24 - (Ship.drones.get(index).resources[4] + resourceCapacities[4] + "").length(); i++){Tools.out(" ");}

        Tools.out("|\n|--------------------------------------/\n\n");
    }
    public void navigateDrone(int x, int y, int dist) throws Exception{
        if(Ship.resources[2] >= dist) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            this.x = x;
            this.y = y;
            Ship.resources[2] -= dist;
            Map.displayMap();
            Tools.out("\nDrone arrived at destination (" + dist + " fuel consumed)\n\n");
        }else{
            Tools.out("\nMother-ship does not have enough fuel to charge the drone enough for it to reach its destination\n\n");
        }
    }
    public void collectDrone(int index, int dist){
        Map.displayMap();
        if(Ship.resources[2] >= dist){
            Ship.resources[2] -= dist;
            collect(index);
            Tools.out("\nDrone unloaded all goods on-board at " + Ship.x + " - " + Ship.y + "\n\n");
        }else{
            Tools.out("\nMother-ship does not have enough fuel to remotely charge the drone in order to return\n\n");
        }
    }
    private void collect(int index){
        Ship.drones.get(index).x = Ship.x;
        Ship.drones.get(index).y = Ship.y;
        for(int i = 0; i < Ship.resources.length; i++) Ship.resources[i] += Ship.drones.get(index).resources[i];

        correctStorageFull(index);
    }
    private void correctStorageFull(int index){
        for(int i = 0; i < Ship.resources.length; i++){
            if(Ship.resources[i] > Ship.resourceCapacities[i]){
                Ship.drones.get(index).resources[i] = Ship.resources[i] - Ship.resourceCapacities[i];
                Ship.resources[i] = Ship.resourceCapacities[i];
            }else{
                Ship.drones.get(index).resources[i] = 0;
            }
        }
    }

    public void stopMining(){
        mining = false;
        miningProgress = 0;
    }
}