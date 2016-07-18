package game;

import data.main_computer.MainComputer;
import threads.HydroponicsThread;
import threads.MiningThread;
import tools.Tools;
import data.Component;
import world.map.Map;
import data.components.DroneHangar;
import data.components.production.Hydroponics;
import data.components.JumpDrive;
import data.components.production.ScrapProcessor;
import data.components.storage.*;
import world.map.entities.*;

import java.util.Scanner;

public class Game {
    private static Scanner in = new Scanner(System.in);

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
                sc(input);
            }
            catch(NumberFormatException e){
                Tools.out("\nWrong parameters\n\n");
            }
        }
    }

    private static void init(){
        //IO.loadComponents();

        Map.generateSector();

        Ship.init();
        MiningThread.start();
        HydroponicsThread.start();

        new MainComputer();
    }


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
                        Ship.drones[drone - 1].collectDrone(drone - 1, Ship.calcHipotenuse(Ship.calcXDifference(Ship.drones[drone - 1].x, Ship.x), Ship.calcYDifference(Ship.drones[drone - 1].y, Ship.y)));
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
        if(input.length() > 2 && input.toUpperCase().substring(0, 3).equals("U C")) {
            if (input.length() > 3) {
                int component;
                if (Integer.parseInt(input.substring(3, 5).trim()) <= Ship.components.length && Integer.parseInt(input.substring(3, 5).trim()) > 0) {
                    component = Integer.parseInt(input.substring(3, 5).trim());
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    if (Ship.components[component - 1] != null) {
                        switch (Ship.components[component - 1].name) {
                            case "Hydroponics":
                                ((Hydroponics) Ship.components[component - 1]).upgrade(component - 1);
                                break;
                            case "Drone Hangar":
                                ((DroneHangar) Ship.components[component - 1]).upgrade(component - 1);
                                break;
                            case "Jump Drive":
                                ((JumpDrive) Ship.components[component - 1]).upgrade(component - 1);
                                break;
                            case "Water Storage":
                                ((WaterStorage) Ship.components[component - 1]).upgrade(component - 1);
                                break;
                            case "Food Storage":
                                ((FoodStorage) Ship.components[component - 1]).upgrade(component - 1);
                                break;
                            case "Fuel Storage":
                                ((FuelStorage) Ship.components[component - 1]).upgrade(component - 1);
                                break;
                            case "Medical Storage":
                                ((MedicalStorage) Ship.components[component - 1]).upgrade(component - 1);
                                break;
                            case "Scrap Storage":
                                ((ScrapStorage) Ship.components[component - 1]).upgrade(component - 1);
                                break;
                            case "Scrap Processor":
                                ((ScrapProcessor) Ship.components[component - 1]).upgrade(component - 1);
                                break;

                        }
                    } else {
                        Tools.out("\nComponent does not exist\n\n");
                    }
                } else {
                    Tools.out("\nComponent does not exist\n\n");
                }
            } else {
                Tools.out("\nComponent ID missing\n\n");
            }
        }
    }
    private static void bc(String input) throws Exception {
        if(input.length() > 2 && input.toUpperCase().substring(0, 3).equals("B C")) {
            if (input.length() > 3) {
                for (int i = 0; i < Ship.components.length; i++) {
                    if (Ship.components[i] == null) {

                        for(int a = 0; a < Component.components.length; a++){
                            if(input.toUpperCase().substring(4).replace(" ", "").equals(Component.components[a].name.toUpperCase().replace(" ", ""))){
                                if(Ship.scrap >= Component.components[a].buildCost){
                                    Ship.scrap -= Component.components[a].buildCost;
                                    Ship.components[i] = Component.components[a];
                                    Tools.out("\nSuccessfully built " + Component.components[a].name + " component (" + (i + 1) + ")\n\n");
                                }else{
                                    Tools.out("\nNot enough scrap to build this component\n\n");
                                }
                                i = Ship.components.length;
                            }
                        }

                    } else if (i == Ship.components.length - 1) {
                        Tools.out("\nNot enough component space\n\n");
                    }
                }
            } else {
                Tools.out("\nComponent name missing\n\n");
            }
        }
    }
    private static void sc(String input) throws Exception {
        if(input.length() > 2 && input.toUpperCase().substring(0, 3).equals("S C")){
            if(input.length() > 3){
                int component;
                if (Integer.parseInt(input.substring(3, 5).trim()) <= Ship.components.length && Integer.parseInt(input.substring(3, 5).trim()) > 0){
                    component = Integer.parseInt(input.substring(3, 5).trim());
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    if(Ship.components[component - 1] != null){
                        Ship.components[component - 1].scrap();
                        Ship.components[component - 1] = null;
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
}
