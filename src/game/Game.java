package game;

import threads.MiningThread;
import tools.IO;
import tools.Tools;
import world.map.Map;
import world.map.components.DroneHangar;
import world.map.components.Hydroponics;
import world.map.entities.*;

import java.util.Random;
import java.util.Scanner;

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
                ac(input);
                dc(input);
                uc(input);
                bc(input);
            }
            catch(NumberFormatException e){
                Tools.out("\nWrong parameters\n\n");
            }
        }
    }

    private static void init(){
        IO.loadComponents();

        Ship.x = r.nextInt(20);
        Ship.y = r.nextInt(20);
        for(int x = 0; x < 20; x++){
            for(int y = 0; y < 20; y++){
                if(r.nextInt(26) == 0){
                    Map.map[x][y] = new Asteroid();
                    Map.map[x][y].icon = Asteroid.icon;
                }
                else if(r.nextInt(48) == 0){
                    Map.map[x][y] = new LargeAsteroid();
                    Map.map[x][y].icon = LargeAsteroid.icon;
                }
                else{
                    Map.map[x][y] = new Space();
                    Map.map[x][y].icon = Space.icon;
                }
                Map.map[x][y].x = x;
                Map.map[x][y].y = y;
            }
        }

        Ship.init();
        MiningThread.start();
    }
    
    
    
    
    //TODO: SIMPLIFY COMMAND BLOCKS AS IN uc() AND bc()

    private static void shws(String input) throws Exception {
        if(input.length() > 4 && input.toUpperCase().substring(0, 5).equals("SHW S")){
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            Map.displayMap();
            Ship.displayShipStatus();
        }
    }
    private static void shwd(String input) throws Exception {
        if(input.length() > 4 && input.toUpperCase().substring(0, 5).equals("SHW D")){
            if(input.length() > 5){
                int drone;
                if (Integer.parseInt(input.substring(5, 7).trim()) <= Ship.drones.length && Integer.parseInt(input.substring(5, 7).trim()) > 0){
                    drone = Integer.parseInt(input.substring(5, 7).trim());
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Map.displayMap();
                    Ship.drones[drone - 1].displayDroneStatus(drone - 1);
                }else{
                    Tools.out("\nDrone does not exist\n\n");
                }
            }else{
                Tools.out("\nDrone ID missing\n\n");
            }
        }
    }
    private static void shwm(String input) throws Exception {
        if(input.length() > 4 && input.toUpperCase().substring(0, 5).equals("SHW M")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            Map.displayMap();
        }
    }
    private static void navs(String input) throws Exception {
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
                                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
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
    private static void navd(String input) throws Exception {
        if (input.length() > 4 && input.toUpperCase().substring(0, 5).equals("NAV D")) {
            if (input.length() > 5) {
                int drone;
                if (input.length() > 6) {
                    if (Integer.parseInt(input.substring(5, 7).trim()) <= Ship.drones.length && Integer.parseInt(input.substring(5, 7).trim()) > 0) {
                        drone = Integer.parseInt(input.substring(5, 7).trim());
                        if (input.length() > 9) {
                            int xDestination = Integer.parseInt(input.substring(8, 10));
                            if (xDestination >= 0 && xDestination < 20) {
                                if (input.length() > 10) {
                                    if (input.length() > 12) {
                                        int yDestination = Integer.parseInt(input.substring(11, 13));
                                        if (yDestination >= 0 && yDestination < 20) {
                                            if(Ship.drones[drone - 1].extractionProgress == 0){
                                                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                                                Ship.drones[drone - 1].navigateDrone(xDestination, yDestination, Ship.calcHipotenuse(Ship.calcXDifference(Ship.drones[drone - 1].x, xDestination), Ship.calcYDifference(Ship.drones[drone - 1].y, yDestination)));
                                            } else {
                                                Tools.out("\nDrone is mining...\n\n");
                                            }
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
                    } else {
                        Tools.out("\nDrone does not exist\n\n");
                    }
                } else {
                    Tools.out("\nDrone ID missing\n\n");
                }
            } else {
                Tools.out("\nDrone ID missing\n\n");
            }
        }
    }
    private static void mines(String input) throws Exception {
        if(input.length() > 5 && input.toUpperCase().substring(0, 6).equals("MINE S")){
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            Map.displayMap();
            Tools.out("\nMining of" + Map.map[Ship.x][Ship.y].name + " commencing... It should be finished in " + Map.map[Ship.x][Ship.y].extractionTime + "s...\n\n");
            MiningThread.shipMining = true;
        }
    }
    private static void mined(String input) throws Exception {
        if(input.length() > 6 && input.toUpperCase().substring(0, 6).equals("MINE D")){
            if(input.length() > 7){
                int drone = Integer.parseInt(input.substring(7).trim()) - 1;
                if(drone >= 0 && drone < Ship.drones.length){
                    if(Ship.drones[drone].extractionProgress == 0) {
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        Map.displayMap();
                        Tools.out("\nMining of" + Map.map[Ship.drones[drone].x][Ship.drones[drone].y].name + " commencing... It should be finished in " + Map.map[Ship.drones[drone].x][Ship.drones[drone].y].extractionTime + "s...\n\n");
                        MiningThread.insertDrone(drone);
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
    private static void cold(String input) throws Exception {
        if(input.length() > 4 && input.toUpperCase().substring(0, 5).equals("COL D")){
            if(input.length() > 5){
                int drone;
                if (Integer.parseInt(input.substring(5, 7).trim()) <= Ship.drones.length && Integer.parseInt(input.substring(5, 7).trim()) > 0){
                    drone = Integer.parseInt(input.substring(5, 7).trim());
                    if(Ship.drones[drone - 1].extractionProgress == 0){
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        Ship.drones[drone - 1].collectDrone(drone - 1, Ship.calcHipotenuse(Ship.calcXDifference(Ship.drones[drone].x, Ship.x), Ship.calcYDifference(Ship.drones[drone].y, Ship.y)));
                    }
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
    private static void ac(String input) throws Exception {
        if(input.length() > 2 && input.toUpperCase().substring(0, 3).equals("A C")){
            if(input.length() > 3){
                int component;
                if (Integer.parseInt(input.substring(3, 5).trim()) <= Ship.components.length && Integer.parseInt(input.substring(3, 5).trim()) > 0){
                    component = Integer.parseInt(input.substring(3, 5).trim());
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    if(Ship.components[component - 1] != null){
                        Ship.components[component - 1].activate();
                    }else{
                        Tools.out("\nComponent does not exist\n\n");
                    }
                }else{
                    Tools.out("\nComponent does not exist\n\n");
                }
            }else{
                Tools.out("\nComponent ID missing\n\n");
            }
        }
    }
    private static void dc(String input) throws Exception {
        if(input.length() > 2 && input.toUpperCase().substring(0, 3).equals("D C")){
            if(input.length() > 3){
                int component;
                if (Integer.parseInt(input.substring(3, 5).trim()) <= Ship.components.length && Integer.parseInt(input.substring(3, 5).trim()) > 0){
                    component = Integer.parseInt(input.substring(3, 5).trim());
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    if(Ship.components[component - 1] != null){
                        Ship.components[component - 1].deactivate();
                    }else{
                        Tools.out("\nComponent does not exist\n\n");
                    }
                }else{
                    Tools.out("\nComponent does not exist\n\n");
                }
            }else{
                Tools.out("\nComponent ID missing\n\n");
            }
        }
    }
    
    
    
    
    
    private static void uc(String input) throws Exception {
        if(Tools.commandBlock("U C")){
            int component;
            if (Integer.parseInt(input.substring(3, 5).trim()) <= Ship.components.length && Integer.parseInt(input.substring(3, 5).trim()) > 0){
                component = Integer.parseInt(input.substring(3, 5).trim());
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                if(Ship.components[component - 1] != null){
                    switch(Ship.components[component - 1].name){
                        case "Hydroponics":
                            ((Hydroponics) Ship.components[component - 1]).upgrade(component - 1);
                            break;
                        case "Drone Hangar":
                            ((DroneHangar) Ship.components[component - 1]).upgrade();
                            break;
                    }
                }else{
                    Tools.out("\nComponent does not exist\n\n");
                }
            }else{
                Tools.out("\nComponent does not exist\n\n");
            }
        }else{
            Tools.out("\nComponent ID missing\n\n");
        }
    }
    private static void bc(String input) throws Exception {
        if(Tools.commandBlock("B C")){
            for(int i = 0; i < Ship.components.length; i++){
                if(Ship.components[i] == null){
                    switch(input.toUpperCase().substring(4).replace(" ", "")){
                        case "HYDROPONICS":
                            if(Ship.scrap >= Hydroponics.buildCost){
                                Ship.components[i] = new Hydroponics();
                                i = Ship.components.length;
                            }else{
                                Tools.out("\nNot enough scrap to build\n\n");
                                i = Ship.components.length;
                            }
                            break;

                        case "DRONEHANGAR":
                            if(Ship.scrap >= DroneHangar.buildCost){
                                Ship.components[i] = new DroneHangar();
                                i = Ship.components.length;
                            }else{
                                Tools.out("\nNot enough scrap to build\n\n");
                                i = Ship.components.length;
                            }
                            break;
                        default:
                            Tools.out("\nComponent does not exist\n\n");
                            i = Ship.components.length;
                        }

                }else if(i == Ship.components.length - 1){
                    Tools.out("\nNot enough component space\n\n");
                }
            }
        }else{
            Tools.out("\nComponent name missing\n\n");
        }
    }
}
