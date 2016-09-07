package world.map.entities;

import data.DronePart;
import tools.Tools;
import world.map.Map;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomás on 06/04/2016.
 */
public class Drone {
    //public static final int INACTIVE = 0, SCRAP_MINER = 1, GATHERER = 2, ASTEROID_MINER = 3;
    public int x, y;
    public static final int waterCapacity = 20, foodCapacity = 20, fuelCapacity = 20, medicalEquipmentCapacity = 10, scrapCapacity = 30;
    public double water, food, fuel, medicalEquipment, scrap, extractionProgress;
    //public int type = INACTIVE;
    public List<DronePart> parts = new ArrayList<>();

    public Drone(){
        x = Ship.x;
        y = Ship.y;
    }

    public void displayDroneStatus(int index){
        Tools.out("/-------------------------|\n" + "|Coordinates: " + Ship.drones.get(index).x + " - " + Ship.drones.get(index).y);
        for(int i = 0; i < 12 - (Ship.drones.get(index).x + " - " + Ship.drones.get(index).y).length(); i++){
            Tools.out(" ");
        }
        Tools.out("|\n|                         |\n|Water: " + Ship.drones.get(index).water);
        for(int i = 0; i < 18 - (Ship.drones.get(index).water + "").length(); i++){
            Tools.out(" ");
        }
        Tools.out("|\n|Food: " + Ship.drones.get(index).food);
        for(int i = 0; i < 19 - (Ship.drones.get(index).food + "").length(); i++){
            Tools.out(" ");
        }
        Tools.out("|\n|Fuel: " + Ship.drones.get(index).fuel);
        for(int i = 0; i < 19 - (Ship.drones.get(index).fuel + "").length(); i++){
            Tools.out(" ");
        }
        Tools.out("|\n|Medical equipment: " + Ship.drones.get(index).medicalEquipment);
        for(int i = 0; i < 6 - (Ship.drones.get(index).medicalEquipment + "").length(); i++){
            Tools.out(" ");
        }
        Tools.out("|\n|Scrap: " + Ship.drones.get(index).scrap);
        for(int i = 0; i < 18 - (Ship.drones.get(index).scrap + "").length(); i++){
            Tools.out(" ");
        }
        Tools.out("|\n|-------------------------/\n\n");
    }
    public void navigateDrone(int x, int y, int dist){
        if(Ship.fuel >= dist) {
            this.x = x;
            this.y = y;
            Ship.fuel -= dist;
            Map.displayMap();
            Tools.out("\nDrone arrived at destination (" + dist + " fuel consumed)\n\n");
        }else{
            Tools.out("\nMother-ship does not have enough fuel to charge the drone enough for it to reach its destination\n\n");
        }
    }
    public void collectDrone(int index, int dist){
        Map.displayMap();
        if(Ship.fuel >= dist){
            Ship.fuel -= dist;
            collect(index);
            Tools.out("\nDrone unloaded all goods on-board at " + Ship.x + " - " + Ship.y + "\n\n");
        }else{
            Tools.out("\nMother-ship does not have enough fuel to remotely charge the drone in order to return\n\n");
        }
    }
    private void collect(int index){
        Ship.drones.get(index).x = Ship.x;
        Ship.drones.get(index).y = Ship.y;
        Ship.water += Ship.drones.get(index).water;
        Ship.food += Ship.drones.get(index).food;
        Ship.fuel += Ship.drones.get(index).fuel;
        Ship.medicalEquipment += Ship.drones.get(index).medicalEquipment;
        Ship.scrap += Ship.drones.get(index).scrap;

        correctStorageFull(index);
    }

    private void correctStorageFull(int index){
        if(Ship.water > Ship.waterCapacity){
            Ship.drones.get(index).water = Ship.water - Ship.waterCapacity;
            Ship.water = Ship.waterCapacity;
        }else{
            Ship.drones.get(index).water = 0;
        }

        if(Ship.food > Ship.foodCapacity){
            Ship.drones.get(index).food = Ship.food - Ship.foodCapacity;
            Ship.food = Ship.foodCapacity;
        }else{
            Ship.drones.get(index).food = 0;
        }

        if(Ship.fuel > Ship.fuelCapacity){
            Ship.drones.get(index).fuel = Ship.fuel - Ship.fuelCapacity;
            Ship.fuel = Ship.fuelCapacity;
        }else{
            Ship.drones.get(index).fuel = 0;
        }

        if(Ship.medicalEquipment > Ship.medicalEquipmentCapacity){
            Ship.drones.get(index).medicalEquipment = Ship.medicalEquipment - Ship.medicalEquipmentCapacity;
            Ship.medicalEquipment = Ship.medicalEquipmentCapacity;
        }else{
            Ship.drones.get(index).medicalEquipment = 0;
        }

        if(Ship.scrap > Ship.scrapCapacity){
            Ship.drones.get(index).scrap = Ship.scrap - Ship.scrapCapacity;
            Ship.scrap = Ship.scrapCapacity;
        }else{
            Ship.drones.get(index).scrap = 0;
        }
    }
}