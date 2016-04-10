package game;

import threads.TimeThread;
import tools.Tools;
import world.map.*;
import world.map.entities.Drone;
import world.map.entities.Ship;
import world.map.entities.Asteroid;
import world.map.entities.LargeAsteroid;

import java.util.Random;
import java.util.Scanner;

import static world.map.Map.map;

public class Game {
    private static Scanner in = new Scanner(System.in);
    private static Random r = new Random();

    public static void main(String[] args) throws Exception {
        Tools.out("If you need help with commands, read the commands.pdf file.\n\n");
        init();
        while(true){
            String input = in.nextLine();
            try{
                shws(input);
                shwd(input);
                shwm(input);
                navs(input);
                navd(input);
                mines(input);
                mined(input);
                cold(input);
            }
            catch(NumberFormatException e){
                Tools.out("\nWrong parameters\n\n");
            }
        }
    }

    private static void init(){
        Ship.x = r.nextInt(20);
        Ship.y = r.nextInt(20);
        for(int i = 0; i < 3; i++){
            Ship.drones[i] = new Drone();
            Ship.drones[i].x = Ship.x;
            Ship.drones[i].y = Ship.y;
        }
        for(int x = 0; x < 20; x++){
            for(int y = 0; y < 20; y++){
                if(r.nextInt(26) == 0){
                    map[x][y] = new Asteroid();
                    map[x][y].icon = Asteroid.icon;
                }
                else if(r.nextInt(48) == 0){
                    map[x][y] = new LargeAsteroid();
                    map[x][y].icon = LargeAsteroid.icon;
                }
                else{
                    map[x][y] = new Space();
                    map[x][y].icon = Space.icon;
                }
                map[x][y].x = x;
                map[x][y].y = y;
            }
        }
        TimeThread.start();
    }

    private static void shws(String input){
        if(input.length() > 4 && input.toUpperCase().substring(0, 5).equals("SHW S")){
            Ship.displayShipStatus();
        }
    }
    private static void shwd(String input){
        if(input.length() > 4 && input.toUpperCase().substring(0, 5).equals("SHW D")){
            if(input.length() > 5){
                int drone;
                if (Integer.parseInt(input.substring(5, 7).trim()) <= Ship.drones.length && Integer.parseInt(input.substring(5, 7).trim()) > 0){
                    drone = Integer.parseInt(input.substring(5, 7).trim());
                    if(Ship.drones[drone].extractionProgress == 0) Ship.drones[drone - 1].displayDroneStatus(drone - 1);
                    else{
                        Tools.out("\nDrone is mining...\n\n");
                    }
                }else{
                    Tools.out("\nDrone does not exist\n\n");
                }
            }else{
                Tools.out("\nDrone ID missing\n\n");
            }
        }
    }
    private static void shwm(String input){
        if(input.length() > 4 && input.toUpperCase().substring(0, 5).equals("SHW M")) {
            Tools.out("\n            1\n");
            Tools.out("  01234567890123456789\n");
            for(int y = 0; y < 20; y++){
                for(int x = 0; x < 22; x++){
                    if(x == 0 && y != 10){Tools.out(" ");}
                    if(y == 10 && x == 0){Tools.out("1");}
                    if(x == 1 && y < 10){Tools.out(y + "");}
                    if(y >= 10 && x == 1){Tools.out((y - 10) + "");}
                    if(x > 1){
                        String icon = map[x - 2][y].icon;
                        if(x - 2 == Ship.x && y == Ship.y) icon = "@";
                        else{
                            for(int i = 0; i < Ship.drones.length; i++){
                                if(x - 2 == Ship.drones[i].x && y == Ship.drones[i].y) icon = "D";
                            }
                        }
                        Tools.out(icon);
                    }
                }
                Tools.out("\n");
            }
            Tools.out("\n\n");
        }
    }
    private static void navs(String input){
        if(input.length() > 4 && input.toUpperCase().substring(0, 5).equals("NAV S")){
            if(Ship.extractionProgress == 0){
                if(input.length() > 5){
                    if(input.length() > 7){
                        int xDestination = Integer.parseInt(input.substring(6, 8));
                        if(xDestination >= 0 && xDestination < 20) {
                            if (input.length() > 8) {
                                if (input.length() > 10) {
                                    int yDestination = Integer.parseInt(input.substring(9, 11));
                                    if(yDestination >= 0 && yDestination < 20) {
                                        Ship.navigateShip(xDestination, yDestination, Ship.calcHipotenuse(Ship.calcXDifference(Ship.x, xDestination), Ship.calcYDifference(Ship.y, yDestination)));
                                    } else {
                                        Tools.out("\nY coordinate invalid\n\n");
                                    }
                                } else {
                                    Tools.out("\nY coordinate invalid\n\n");
                                }
                            } else {
                                Tools.out("\nY coordinate missing\n\n");
                            }
                        }else{
                            Tools.out("\nX coordinate invalid\n\n");
                        }
                    }else{
                        Tools.out("\nX coordinate invalid\n\n");
                    }
                }else{
                    Tools.out("\nCoordinates missing\n\n");
                }
            }else {
                Tools.out("\nShip is mining...\n\n");
            }
        }
    }
    private static void navd(String input){
        if(input.length() > 4 && input.toUpperCase().substring(0, 5).equals("NAV D")){
            if(Ship.extractionProgress == 0){
                if(input.length() > 5){
                    int drone;
                    if(input.length() > 6){
                        if (Integer.parseInt(input.substring(5, 7).trim()) <= Ship.drones.length && Integer.parseInt(input.substring(5, 7).trim()) > 0) {
                            drone = Integer.parseInt(input.substring(5, 7).trim());
                            if (input.length() > 9) {
                                int xDestination = Integer.parseInt(input.substring(8, 10));
                                if (xDestination >= 0 && xDestination < 20) {
                                    if (input.length() > 10) {
                                        if (input.length() > 12) {
                                            int yDestination = Integer.parseInt(input.substring(11, 13));
                                            if (yDestination >= 0 && yDestination < 20) {
                                                Ship.drones[drone - 1].navigateDrone(xDestination, yDestination);
                                            } else {
                                                Tools.out("\nY coordinate invalid\n\n");
                                            }
                                        } else {
                                            Tools.out("\nY coordinate invalid\n\n");
                                        }
                                    } else {
                                        Tools.out("\nY coordinate missing\n\n");
                                    }
                                } else {
                                    Tools.out("\nX coordinate invalid\n\n");
                                }
                            } else {
                                Tools.out("\nX coordinate invalid\n\n");
                            }
                        }else{
                            Tools.out("\nDrone does not exist\n\n");
                        }
                    }else{
                        Tools.out("\nDrone ID missing\n\n");
                    }
                }else{
                    Tools.out("\nDrone ID missing\n\n");
                }
            }else {
                Tools.out("\nDrone is mining...\n\n");
            }
        }
    }
    private static void mines(String input){
        if(input.length() > 6 && input.toUpperCase().substring(0, 6).equals("MINE S")){
            Tools.out("\nMining of" + Map.map[Ship.x][Ship.y].name + " commencing... It should be finished in " + Map.map[Ship.x][Ship.y].extractionTime + "s...\n\n");
            TimeThread.shipMining = true;
        }
    }
    private static void mined(String input){
        if(input.length() > 6 && input.toUpperCase().substring(0, 6).equals("MINE D")){
            if(input.length() > 7){
                int drone = Integer.parseInt(input.substring(7).trim()) - 1;
                if(drone >= 0 && drone < Ship.drones.length){
                    if(Ship.drones[drone].extractionProgress == 0) {
                        Tools.out("\nMining of" + Map.map[Ship.drones[drone].x][Ship.drones[drone].y].name + " commencing... It should be finished in " + Map.map[Ship.drones[drone].x][Ship.drones[drone].y].extractionTime + "s...\n\n");
                        TimeThread.insertDrone(Ship.drones[drone]);
                    }else{
                        Tools.out("\nDrone is mining...\n\n");
                    }
                }else{
                    Tools.out("\nDrone doesn't exist\n\n");
                }
            }else{
                Tools.out("\nDrone id missing\n\n");
            }
        }
    }
    private static void cold(String input){
        if(input.length() > 4 && input.toUpperCase().substring(0, 5).equals("COL D")){
            if(input.length() > 5){
                int drone;
                if (Integer.parseInt(input.substring(5, 7).trim()) <= Ship.drones.length && Integer.parseInt(input.substring(5, 7).trim()) > 0){
                    drone = Integer.parseInt(input.substring(5, 7).trim());
                    if(Ship.drones[drone].extractionProgress == 0) Ship.drones[drone - 1].collectDrone(drone - 1);
                    else{
                        Tools.out("\nDrone is mining...\n\n");
                    }
                }else{
                    Tools.out("\nDrone does not exist\n\n");
                }
            }else{
                Tools.out("\nDrone ID missing\n\n");
            }
        }
    }
}