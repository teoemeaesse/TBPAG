package world.map.entities;

import tools.Tools;

/**
 * Created by Tomás on 29/03/2016.
 */
public class Ship {
    public static int x, y;
    public static int water = 15, food = 5, fuel = 10, medicalEquipment = 1, scrap = 0, extractionProgress = 0;
    public static Drone[] drones = new Drone[3];

    public static void displayShipStatus(){
        Tools.out("/-------------------------|\n" + "|Coordinates: " + x + " - " + y);
        for(int i = 0; i < 12 - (x + " - " + y).length(); i++){
            Tools.out(" ");
        }
        Tools.out("|\n|                         |\n|Water: " + water);
        for(int i = 0; i < 18 - (water + "").length(); i++){
            Tools.out(" ");
        }
        Tools.out("|\n|Food: " + food);
        for(int i = 0; i < 19 - (food + "").length(); i++){
            Tools.out(" ");
        }
        Tools.out("|\n|Fuel: " + fuel);
        for(int i = 0; i < 19 - (fuel + "").length(); i++){
            Tools.out(" ");
        }
        Tools.out("|\n|Medical equipment: " + medicalEquipment);
        for(int i = 0; i < 6 - (medicalEquipment + "").length(); i++){
            Tools.out(" ");
        }
        Tools.out("|\n|Scrap: " + scrap);
        for(int i = 0; i < 18 - (scrap + "").length(); i++){
            Tools.out(" ");
        }
        Tools.out("|\n|-------------------------/\n\n");
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
    public static void navigateShip(int x, int y, int dist){
        Ship.x = x;
        Ship.y = y;
        fuel -= dist;
        Tools.out("\nCourse set..." + dist + " fuel will be consumed.\n\n");
    }
}