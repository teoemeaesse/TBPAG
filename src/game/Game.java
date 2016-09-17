package game;

import data.components.drone.DroneModifier;
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
        Tools.out("If you need help, type in '" + GameConstants.COMMAND_HELPCOMMANDS.toLowerCase() + "'.\n\n");

        init();
        
        while(true){
            String input = in.nextLine();
            try{
                commandsHelp(input);
                componentsHelp(input);
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
            catch(Exception e){}
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

    private static void commandsHelp(String input) throws Exception {
        int initialLength = GameConstants.COMMAND_HELPCOMMANDS.length() - 1;

        if(input.length() > initialLength && input.toUpperCase().substring(0, initialLength + 1).equals(GameConstants.COMMAND_HELPCOMMANDS)){
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            Tools.drawCommandsHelp();
        }
    }
    private static void componentsHelp(String input) throws Exception {
        int initialLength = GameConstants.COMMAND_HELPCOMPONENTS.length() - 1;

        if(input.length() > initialLength && input.toUpperCase().substring(0, initialLength + 1).equals(GameConstants.COMMAND_HELPCOMPONENTS)){
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            Tools.drawComponentsHelp();
        }
    }
    private static void shws(String input) throws Exception {
        int initialLength = GameConstants.COMMAND_SHWS.length() - 1;

        if(input.length() > initialLength && input.toUpperCase().substring(0, initialLength + 1).equals(GameConstants.COMMAND_SHWS)){
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            Map.displayMap();
            Ship.displayShipStatus();
        }
    }
    private static void shwd(String input) throws Exception {
        int initialLength = GameConstants.COMMAND_SHWD.length() - 1;

        if(input.length() > initialLength && input.toUpperCase().substring(0, initialLength + 1).equals(GameConstants.COMMAND_SHWD)){
            if(input.length() > initialLength + 1){
                int drone;
                if (Integer.parseInt(input.substring(initialLength + 1, initialLength + 3).trim()) <= Ship.drones.size() && Integer.parseInt(input.substring(initialLength + 1, initialLength + 3).trim()) > 0){
                    drone = Integer.parseInt(input.substring(initialLength + 1, initialLength + 3).trim());
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Map.displayMap();
                    Ship.drones.get(drone - 1).displayDroneStatus(drone - 1);
                }else{
                    Tools.out("\nDrone does not exist\n\n");
                }
            }else{
                Tools.out("\nDrone ID missing\n\n");
            }
        }
    }
    private static void shwm(String input) throws Exception {
        int initialLength = GameConstants.COMMAND_SHWM.length() - 1;

        if(input.length() > initialLength && input.toUpperCase().substring(0, initialLength + 1).equals(GameConstants.COMMAND_SHWM)) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            Map.displayMap();
        }
    }
    private static void navs(String input) throws Exception {
        int initialLength = GameConstants.COMMAND_NAVS.length() - 1;

        if(input.length() > initialLength && input.toUpperCase().substring(0, initialLength + 1).equals(GameConstants.COMMAND_NAVS)){
            if(Ship.extractionProgress == 0){
                if(input.length() > initialLength + 1){
                    if(input.length() > initialLength + 3){
                        int xDestination = Integer.parseInt(input.substring(initialLength + 2, initialLength + 4));
                        if(xDestination >= 0 && xDestination < 20) {
                            if (input.length() > initialLength + 4) {
                                if (input.length() > initialLength + 6) {
                                    int yDestination = Integer.parseInt(input.substring(initialLength + 5, initialLength + 7));
                                    if(yDestination >= 0 && yDestination < 20) {
                                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                                        Ship.navigateShip(xDestination, yDestination, Ship.calcHypotenuse(Ship.calcXDifference(Ship.x, xDestination), Ship.calcYDifference(Ship.y, yDestination)));
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
        int initialLength = GameConstants.COMMAND_NAVD.length() - 1;

        if (input.length() > initialLength && input.toUpperCase().substring(0, initialLength + 1).equals(GameConstants.COMMAND_NAVD)) {
            if (input.length() > initialLength + 1) {
                int drone;
                if (input.length() > initialLength + 2) {
                    if (Integer.parseInt(input.substring(initialLength + 1, initialLength + 3).trim()) <= Ship.drones.size() && Integer.parseInt(input.substring(initialLength + 1, initialLength + 3).trim()) > 0) {
                        drone = Integer.parseInt(input.substring(initialLength + 1, initialLength + 3).trim());
                        if (input.length() > initialLength + 5) {
                            int xDestination = Integer.parseInt(input.substring(initialLength + 4, initialLength + 6));
                            if (xDestination >= 0 && xDestination < 20) {
                                if (input.length() > initialLength + 6) {
                                    if (input.length() > initialLength + 8) {
                                        int yDestination = Integer.parseInt(input.substring(initialLength + 7, initialLength + 9));
                                        if (yDestination >= 0 && yDestination < 20) {
                                            if(Ship.drones.get(drone - 1).extractionProgress == 0){
                                                Ship.drones.get(drone - 1).navigateDrone(xDestination, yDestination, Ship.calcHypotenuse(Ship.calcXDifference(Ship.drones.get(drone - 1).x, xDestination), Ship.calcYDifference(Ship.drones.get(drone - 1).y, yDestination)));
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
        int initialLength = GameConstants.COMMAND_MINES.length() - 1;

        if(input.length() > initialLength && input.toUpperCase().substring(0, initialLength + 1).equals(GameConstants.COMMAND_MINES)){
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            Map.displayMap();
            Tools.out("\nMining of" + Map.map[Ship.x][Ship.y].name + " commencing... It should be finished in " + Map.map[Ship.x][Ship.y].extractionTime + "s...\n\n");
            MiningThread.shipMining = true;
        }
    }
    private static void mined(String input) throws Exception {
        int initialLength = GameConstants.COMMAND_MINED.length() - 1;

        if(input.length() > initialLength && input.toUpperCase().substring(0, initialLength + 1).equals(GameConstants.COMMAND_MINED)){
            if(input.length() > initialLength + 2){
                int drone = Integer.parseInt(input.substring(initialLength + 2).trim()) - 1;
                if(drone >= 0 && drone < Ship.drones.size()){
                    if(Ship.drones.get(drone).extractionProgress == 0) {
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        Map.displayMap();
                        Tools.out("\nMining of" + Map.map[Ship.drones.get(drone).x][Ship.drones.get(drone).y].name + " commencing... It should be finished in " + Map.map[Ship.drones.get(drone).x][Ship.drones.get(drone).y].extractionTime + "s...\n\n");
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
        int initialLength = GameConstants.COMMAND_COLD.length() - 1;

        if(input.length() > initialLength && input.toUpperCase().substring(0, initialLength + 1).equals(GameConstants.COMMAND_COLD)){
            if(input.length() > initialLength + 1){
                int drone;
                if (Integer.parseInt(input.substring(initialLength + 1, initialLength + 3).trim()) <= Ship.drones.size() && Integer.parseInt(input.substring(initialLength + 1, initialLength + 3).trim()) > 0){
                    drone = Integer.parseInt(input.substring(initialLength + 1, initialLength + 3).trim());
                    if(Ship.drones.get(drone - 1).extractionProgress == 0){
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        Ship.drones.get(drone - 1).collectDrone(drone - 1, Ship.calcHypotenuse(Ship.calcXDifference(Ship.drones.get(drone - 1).x, Ship.x), Ship.calcYDifference(Ship.drones.get(drone - 1).y, Ship.y)));
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
        int initialLength = GameConstants.COMMAND_AC.length() - 1;

        if(input.length() > initialLength && input.toUpperCase().substring(0, initialLength + 1).equals(GameConstants.COMMAND_AC)){
            if(input.length() > initialLength + 1){
                int component;
                if (Integer.parseInt(input.substring(initialLength + 1, initialLength + 3).trim()) <= Ship.components.length && Integer.parseInt(input.substring(initialLength + 1, initialLength + 3).trim()) > 0){
                    component = Integer.parseInt(input.substring(initialLength + 1, initialLength + 3).trim());
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
        int initialLength = GameConstants.COMMAND_DC.length() - 1;

        if(input.length() > initialLength && input.toUpperCase().substring(0, initialLength + 1).equals(GameConstants.COMMAND_DC)){
            if(input.length() > initialLength + 1){
                int component;
                if (Integer.parseInt(input.substring(initialLength + 1, initialLength + 3).trim()) <= Ship.components.length && Integer.parseInt(input.substring(initialLength + 1, initialLength + 3).trim()) > 0){
                    component = Integer.parseInt(input.substring(initialLength + 1, initialLength + 3).trim());
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
        int initialLength = GameConstants.COMMAND_UC.length() - 1;

        if(input.length() > initialLength && input.toUpperCase().substring(0, initialLength + 1).equals(GameConstants.COMMAND_UC)) {
            if (input.length() > initialLength + 1) {
                int component;
                if (Integer.parseInt(input.substring(initialLength + 1, initialLength + 3).trim()) <= Ship.components.length && Integer.parseInt(input.substring(initialLength + 1, initialLength + 3).trim()) > 0) {
                    component = Integer.parseInt(input.substring(initialLength + 1, initialLength + 3).trim());
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
                            case "Drone Modifier":
                                ((DroneModifier) Ship.components[component - 1]).upgrade(component - 1);
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
        int initialLength = GameConstants.COMMAND_BC.length() - 1;

        if(input.length() > initialLength && input.toUpperCase().substring(0, initialLength + 1).equals(GameConstants.COMMAND_BC)) {
            if (input.length() > initialLength + 1) {
                for (int i = 0; i < Ship.components.length; i++) {
                    if (Ship.components[i] == null) {
                        for(int a = 0; a < Component.components.length; a++){
                            if(input.toUpperCase().substring(initialLength + 2).replace(" ", "").equals(Component.components[a].name.toUpperCase().replace(" ", ""))){
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
        int initialLength = GameConstants.COMMAND_SC.length() - 1;

        if(input.length() > initialLength && input.toUpperCase().substring(0, initialLength + 1).equals(GameConstants.COMMAND_SC)){
            if(input.length() > initialLength + 1){
                int component;
                if (Integer.parseInt(input.substring(initialLength + 1, initialLength + 3).trim()) <= Ship.components.length && Integer.parseInt(input.substring(initialLength + 1, initialLength + 3).trim()) > 0){
                    component = Integer.parseInt(input.substring(initialLength + 1, initialLength + 3).trim());
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
    private static void mountc(String input) throws Exception {

    }
}