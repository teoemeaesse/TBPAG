package world.map.entities;

import threads.HydroponicsThread;
import tools.Tools;
import world.map.Component;
import world.map.Map;
import world.map.components.DroneHangar;
import world.map.components.Hydroponics;

/**
 * Created by Tomás on 29/03/2016.
 */
public class Ship {
    public static int x, y;
    public static double water = 15, food = 5, fuel = 10, medicalEquipment = 1, scrap = 0, extractionProgress = 0;
    public static Drone[] drones = new Drone[1];
    public static Component components[] = new Component[5];

    public static void init(){
        Ship.drones[0] = new Drone();
        Ship.drones[0].x = Ship.x;
        Ship.drones[0].y = Ship.y;
        components[0] = new DroneHangar();
        HydroponicsThread.start();
    }

    public static void displayShipStatus(){
        Tools.out("/-------------------------|\n" + "|Coordinates: " + x + " - " + y);
        for(int i = 0; i < 12 - (x + " - " + y).length(); i++){Tools.out(" ");}

        Tools.out("|\n|                         |\n|Water: " + water);
        for(int i = 0; i < 18 - (water + "").length(); i++){Tools.out(" ");}

        Tools.out("|\n|Food: " + food);
        for(int i = 0; i < 19 - (food + "").length(); i++){Tools.out(" ");}

        Tools.out("|\n|Fuel: " + fuel);
        for(int i = 0; i < 19 - (fuel + "").length(); i++){Tools.out(" ");}

        Tools.out("|\n|Medical equipment: " + medicalEquipment);
        for(int i = 0; i < 6 - (medicalEquipment + "").length(); i++){Tools.out(" ");}

        Tools.out("|\n|Scrap: " + scrap);
        for(int i = 0; i < 18 - (scrap + "").length(); i++){Tools.out(" ");}

        Tools.out("|\n|-------------------------/\n\n");

        Tools.out("/-------------------------|");
        for(int c = 0; c < components.length; c++){
            if(components[c] != null){
                Tools.out("\n|" + components[c].name);
                for (int i = 0; i < 25 - (components[c].name).length(); i++) {
                    Tools.out(" ");
                }
                Tools.out("|\n|\tlevel: " + components[c].level + "");
                for (int i = 0; i < 25 - ("   level: " + components[c].level).length(); i++) {
                    Tools.out(" ");
                }
                Tools.out("|");
            }
        }
        Tools.out("\n|-------------------------/\n\n");
    }
    public static void navigateShip(int x, int y, int dist){
        if(fuel - dist >= 0) {
            String[] dronesMoved = new String[drones.length];
            for(int i = 0; i < drones.length; i++){
                dronesMoved[i] = "";
                if(drones[i].x == Ship.x && drones[i].y == Ship.y) {
                    dronesMoved[i] += i + 1;
                    drones[i].x = x;
                   drones[i].y = y;
                }
            }
            Ship.x = x;
            Ship.y = y;
            fuel -= dist;
            String dronesPrinted = "";
            for (int i = 0; i < dronesMoved.length; i++) {
                if (!dronesMoved[i].equals("")) {
                    dronesPrinted += dronesMoved[i] + " ";
                }
            }
            Map.displayMap();
            Tools.out("\nCourse set..." + dist + " fuel will be consumed.\nDrones " + dronesPrinted + "were carried with the mother-ship\n\n");
        }else{
            Map.displayMap();
            Tools.out("\nNot enough fuel...\n\n");
        }
    }


    public static int calcXDifference(int x1, int x2){
        int diff = x1 - x2;
        diff = Math.abs(diff);
        return diff;
    }
    public static int calcYDifference(int y1, int y2){
        int diff = y1 - y2;
        diff = Math.abs(diff);
        return diff;
    }
    public static int calcHipotenuse(int x, int y){
        double xs = Math.pow(x, 2), ys = Math.pow(y, 2);
        double sum = xs + ys;
        return (int) Math.sqrt(sum);
    }
}