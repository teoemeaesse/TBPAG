package world.map.entities;

import data.DronePart;
import data.drone_parts.DroneParts;
import data.Hardware;
import data.main_computer.MainComputer;
import data.main_computer.hardware.CPU;
import tools.Tools;
import data.Component;
import world.map.Map;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomás on 29/03/2016.
 */
public class Ship {
    public static int x, y;
    public static int hullPoints = 100, money = 9999999;
    public static double[] resourceCapacities = {30, 30, 30, 5, 20};
    public static double extractionProgress = 0;
    public static double[] resources = {15, 5, 999, 1, 999};//water, food, fuel, medequip, scrap
    public static int droneCapacity = 1;
    public static Component components[] = new Component[10];
    public static List<Drone> drones = new ArrayList<>(droneCapacity);
    public static List<Hardware> hardwareStorage = new ArrayList<>();
    public static List<DronePart> dronePartsStorage = new ArrayList<>();

    public static void init(){
        Ship.drones.add(new Drone());
        Ship.dronePartsStorage.add(DroneParts.SMALL_DRILL.getDronePart());
        Ship.dronePartsStorage.add(DroneParts.LARGE_DRILL.getDronePart());
        Ship.hardwareStorage.add(new CPU(2, 8));
        components[0] = new MainComputer();
    }

    public static void displayShipStatus(){
        Tools.out("/--------------------------------------|\n" + "| Coordinates: " + x + " - " + y);
        for(int i = 0; i < 24 - (x + " - " + y).length(); i++){Tools.out(" ");}

        Tools.out("|\n|                                      |\n| Water: " + resources[0] + " / " + resourceCapacities[0]);
        for(int i = 0; i < 24 - (resources[0] + resourceCapacities[0] + "").length(); i++){Tools.out(" ");}

        Tools.out("|\n| Food: " + resources[1] + " / " + resourceCapacities[1]);
        for(int i = 0; i < 25 - (resources[1] + resourceCapacities[1] + "").length(); i++){Tools.out(" ");}

        Tools.out("|\n| Fuel: " + resources[2] + " / " + resourceCapacities[2]);
        for(int i = 0; i < 25 - (resources[2] + resourceCapacities[2] + "").length(); i++){Tools.out(" ");}

        Tools.out("|\n| Medical equipment: " + resources[3] + " / " + resourceCapacities[3]);
        for(int i = 0; i < 12 - (resources[3] + resourceCapacities[3] + "").length(); i++){Tools.out(" ");}

        Tools.out("|\n| Scrap: " + resources[4] + " / " + resourceCapacities[4]);
        for(int i = 0; i < 24 - (resources[4] + resourceCapacities[4] + "").length(); i++){Tools.out(" ");}

        Tools.out("|\n| Money: " + money + "$");
        for(int i = 0; i < 29 - String.valueOf(money).length(); i++){Tools.out(" ");}

        Tools.out("|\n|--------------------------------------/\n\n");

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
        if(resources[2] - dist >= 0) {
            String[] dronesMoved = new String[drones.size()];
            for(int i = 0; i < drones.size(); i++){
                dronesMoved[i] = "";
                if(drones.get(i).x == Ship.x && drones.get(i).y == Ship.y) {
                    dronesMoved[i] += i + 1;
                    drones.get(i).x = x;
                    drones.get(i).y = y;
                }
            }
            Ship.x = x;
            Ship.y = y;
            resources[2] -= dist;
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

    public static void displayHardwareStorage(){
        Tools.out("\n\n");
        for(int i = 0; i < hardwareStorage.size(); i++){
            Tools.out((i + 1) + ". " + hardwareStorage.get(i).name + " [" + hardwareStorage.get(i).tag + "]\n");
        }
        Tools.out("\n\n");
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
    public static int calcHypotenuse(int x, int y){
        double xs = Math.pow(x, 2), ys = Math.pow(y, 2);
        double sum = xs + ys;
        return (int) Math.sqrt(sum);
    }
}