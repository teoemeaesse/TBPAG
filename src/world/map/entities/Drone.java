package world.map.entities;

import tools.Tools;
import world.map.Map;

/**
 * Created by Tomás on 06/04/2016.
 */
public class Drone {
    public int x, y;
    public int water = 0, food = 0, fuel = 0, medicalEquipment = 0, scrap = 0, extractionProgress = 0;

    public void displayDroneStatus(int index){
        Tools.out("/-------------------------|\n" + "|Coordinates: " + Ship.drones[index].x + " - " + Ship.drones[index].y);
        for(int i = 0; i < 12 - (Ship.drones[index].x + " - " + Ship.drones[index].y).length(); i++){
            Tools.out(" ");
        }
        Tools.out("|\n|                         |\n|Water: " + Ship.drones[index].water);
        for(int i = 0; i < 18 - (Ship.drones[index].water + "").length(); i++){
            Tools.out(" ");
        }
        Tools.out("|\n|Food: " + Ship.drones[index].food);
        for(int i = 0; i < 19 - (Ship.drones[index].food + "").length(); i++){
            Tools.out(" ");
        }
        Tools.out("|\n|Fuel: " + Ship.drones[index].fuel);
        for(int i = 0; i < 19 - (Ship.drones[index].fuel + "").length(); i++){
            Tools.out(" ");
        }
        Tools.out("|\n|Medical equipment: " + Ship.drones[index].medicalEquipment);
        for(int i = 0; i < 6 - (Ship.drones[index].medicalEquipment + "").length(); i++){
            Tools.out(" ");
        }
        Tools.out("|\n|Scrap: " + Ship.drones[index].scrap);
        for(int i = 0; i < 18 - (Ship.drones[index].scrap + "").length(); i++){
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
        if(Ship.fuel >= dist) {
            Ship.drones[index].x = Ship.x;
            Ship.drones[index].y = Ship.y;
            Ship.water += Ship.drones[index].water;
            Ship.food += Ship.drones[index].food;
            Ship.fuel += Ship.drones[index].fuel;
            Ship.fuel -= dist;
            Ship.medicalEquipment += Ship.drones[index].medicalEquipment;
            Ship.scrap += Ship.drones[index].scrap;
            Ship.drones[index].water = 0;
            Ship.drones[index].food = 0;
            Ship.drones[index].fuel = 0;
            Ship.drones[index].medicalEquipment = 0;
            Ship.drones[index].scrap = 0;
            Tools.out("\nDrone unloaded all goods on-board at " + Ship.x + " - " + Ship.y + "\n\n");
        }else{
            Tools.out("\nMother-ship does not have enough fuel to remotely charge the drone in order to return\n\n");
        }
    }
}
