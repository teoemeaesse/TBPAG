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
    public static final double waterCapacity = 20, foodCapacity = 20, fuelCapacity = 20, medicalEquipmentCapacity = 10, scrapCapacity = 30;
    public double water, food, fuel, medicalEquipment, scrap, extractionProgress;
    //public int type = INACTIVE;
    public List<DronePart> parts = new ArrayList<>();

    public Drone(){
        x = Ship.x;
        y = Ship.y;
    }

    public void displayDroneStatus(int index){
        Tools.out("/--------------------------------------|\n" + "| Coordinates: " + Ship.drones.get(index).x + " - " + Ship.drones.get(index).y);
        for(int i = 0; i < 24 - (Ship.drones.get(index).x + " - " + Ship.drones.get(index).y).length(); i++){Tools.out(" ");}

        Tools.out("|\n|                                      |\n| Water: " + Ship.drones.get(index).water + " / " + waterCapacity);
        for(int i = 0; i < 24 - (Ship.drones.get(index).water + waterCapacity + "").length(); i++){Tools.out(" ");}

        Tools.out("|\n| Food: " + Ship.drones.get(index).food + " / " + foodCapacity);
        for(int i = 0; i < 25 - (Ship.drones.get(index).food + foodCapacity + "").length(); i++){Tools.out(" ");}

        Tools.out("|\n| Fuel: " + Ship.drones.get(index).fuel + " / " + fuelCapacity);
        for(int i = 0; i < 25 - (Ship.drones.get(index).fuel + fuelCapacity + "").length(); i++){Tools.out(" ");}

        Tools.out("|\n| Medical equipment: " + Ship.drones.get(index).medicalEquipment + " / " + medicalEquipmentCapacity);
        for(int i = 0; i < 12 - (Ship.drones.get(index).medicalEquipment + medicalEquipmentCapacity + "").length(); i++){Tools.out(" ");}

        Tools.out("|\n| Scrap: " + Ship.drones.get(index).scrap + " / " + scrapCapacity);
        for(int i = 0; i < 24 - (Ship.drones.get(index).scrap + scrapCapacity + "").length(); i++){Tools.out(" ");}

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
        Ship.resources[0] += Ship.drones.get(index).water;
        Ship.resources[1] += Ship.drones.get(index).food;
        Ship.resources[2] += Ship.drones.get(index).fuel;
        Ship.resources[3] += Ship.drones.get(index).medicalEquipment;
        Ship.resources[4] += Ship.drones.get(index).scrap;

        correctStorageFull(index);
    }

    private void correctStorageFull(int index){
        if(Ship.resources[0] > Ship.resourceCapacities[0]){
            Ship.drones.get(index).water = Ship.resources[0] - Ship.resourceCapacities[0];
            Ship.resources[0] = Ship.resourceCapacities[0];
        }else{
            Ship.drones.get(index).water = 0;
        }

        if(Ship.resources[1] > Ship.resourceCapacities[1]){
            Ship.drones.get(index).food = Ship.resources[1] - Ship.resourceCapacities[1];
            Ship.resources[1] = Ship.resourceCapacities[1];
        }else{
            Ship.drones.get(index).food = 0;
        }

        if(Ship.resources[2] > Ship.resourceCapacities[2]){
            Ship.drones.get(index).fuel = Ship.resources[2] - Ship.resourceCapacities[2];
            Ship.resources[2] = Ship.resourceCapacities[2];
        }else{
            Ship.drones.get(index).fuel = 0;
        }

        if(Ship.resources[3] > Ship.resourceCapacities[3]){
            Ship.drones.get(index).medicalEquipment = Ship.resources[3] - Ship.resourceCapacities[3];
            Ship.resources[3] = Ship.resourceCapacities[3];
        }else{
            Ship.drones.get(index).medicalEquipment = 0;
        }

        if(Ship.resources[4] > Ship.resourceCapacities[4]){
            Ship.drones.get(index).scrap = Ship.resources[4] - Ship.resourceCapacities[4];
            Ship.resources[4] = Ship.resourceCapacities[4];
        }else{
            Ship.drones.get(index).scrap = 0;
        }
    }
}