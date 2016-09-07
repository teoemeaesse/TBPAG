package data.components.production;

import data.DronePart;
import data.Material;
import data.drone_parts.DroneParts;
import data.main_computer.hardware.CPU;
import data.materials.Materials;
import game.GameConstants;
import tools.Tools;
import data.Component;
import world.map.entities.Ship;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Tomás on 07/18/2016.
 */
public class ScrapProcessor extends Component {
    private final int PRODUCTION_STAGE = 0, ASSEMBLING_STAGE = 1;
    private int currentStage = 0;

    private Material[] materials = new Material[]{Materials.FE.getMaterial(), Materials.SL.getMaterial(), Materials.AU.getMaterial(), Materials.CU.getMaterial(), Materials.PL.getMaterial()};

    private DronePart[] dronePartList = new DronePart[]{DroneParts.SMALL_DRILL.getDronePart(), DroneParts.LARGE_DRILL.getDronePart(), DroneParts.WATER_STORAGE.getDronePart(), DroneParts.FOOD_STORAGE.getDronePart(), DroneParts.FUEL_STORAGE.getDronePart(), DroneParts.MEDICAL_STORAGE.getDronePart(), DroneParts.SCRAP_STORAGE.getDronePart()};

    private Scanner scanner = new Scanner(System.in);

    public ScrapProcessor(){
        name = GameConstants.COMPONENT_SCRAPPROCESSOR;
        buildCost = 40;
        baseUpgradeCost = 25;
        level = 1;
    }

    public void upgrade(int index){
        if(Ship.scrap >= baseUpgradeCost * (level + 0.5)){
            Ship.scrap -= baseUpgradeCost * (level + 0.5);
            Ship.components[index].level++;
            Tools.out("\nScrap Processor component successfully upgraded, having spent " + baseUpgradeCost * (level + 0.5) + " scrap.\n\n");
        }else{
            Tools.out("\nNot enough scrap to upgrade this component. You need " + baseUpgradeCost * (level + 0.5) + " scrap.");
        }
    }

    @Override
    public void activate() throws Exception {
        productionMenu();
    }

    private void productionMenu() throws Exception {
        boolean open = true;

        while(open){
            Tools.out("\n\nWelcome to the S.P.'s v1.3 Menus4U's trademarked menu.\n\nWhat can we do for you?\n\n1. Check material storage\n2. Separate scrap into materials\n3. Produce something\nx. Exit menu\n\n");

            String input = scanner.nextLine();

            switch(input){
                case "1":
                    materialStorage();
                    break;
                case "2":
                    separate();
                    break;
                case "3":
                    produce();
                    break;
                case "x":
                    open = false;
                    Tools.out("\nThank you for using our service. Come back soon for more top-notch graphical menus.\n(press enter)\n\n");
                    scanner.nextLine();
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    break;
            }
        }
    }

    private void materialStorage(){
        Tools.out("\nMetal: " + materials[0].amount + ";\nSilicon: " + materials[1].amount + ";\nGold: " + materials[2].amount + ";\nCopper: " + materials[3].amount + ";\nPlastic: " + materials[4].amount + ". (press enter)\n\n");
        scanner.nextLine();
    }
    private void separate() throws NumberFormatException {
        Random r = new Random();

        boolean open = true;

        while(open){
            Tools.out("\nHow much scrap do you want to process? (you have " + Ship.scrap + "; x to return)\n");

            String input = scanner.nextLine();

            if(input.toUpperCase().equals("X")) open = false;
            else{
                int scrap = Integer.parseInt(input);

                if(Ship.scrap >= scrap){
                    Ship.scrap -= scrap;

                    materials[0].amount += (r.nextInt(12 - 4) + 4) * scrap;
                    materials[1].amount += (r.nextInt(6 - 2) + 2) * scrap;
                    materials[2].amount += (r.nextInt(4 - 1) + 1) * scrap;
                    materials[3].amount += (r.nextInt(16 - 5) + 5) * scrap;
                    materials[4].amount += (r.nextInt(8 - 2) + 2) * scrap;

                    Tools.out("\nMaterials added to storage. (press enter)\n\n");
                    open = false;
                    scanner.nextLine();
                }else{
                    Tools.out("\nNot enough scrap. Go away you lower-middle class citizen! Shoo! (press enter)\n\n");
                    scanner.nextLine();
                }
            }
        }
    }
    private void produce() throws Exception {
        boolean open = true;

        while(open){
            Tools.out("\nWelcome to the S.P.'s v.13 Menus4U's trademarked menu's produce submenu.\n\nWhat hardware do you want to build?\n\n1. CPU\n2. SSD\n3. RAM\n4. Safety hardware\n5. Drone parts\nx. Return\n\n");

            String input = scanner.nextLine();

            switch(input.toUpperCase()){
                case "1":
                    produceCPU();
                    break;
                case "5":
                    dronePartsSubmenu();
                case "X":
                    open = false;
                    Tools.out("\nReturning...\n\n");
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
        }
    }
    private void produceCPU() throws NumberFormatException {
        boolean open = true;
        double frequency;
        int cores;

        Tools.out("\nWelcome to the S.P.'s v.13 Menus4U's trademarked menu's produce hardware submenu's CPU submenu. Enter x to return.\n\n");


        while(open){
            Tools.out("\nFrequency (i.e. 3.0, 14.2, 1.2...; current maximum is " + Double.parseDouble(String.valueOf(level * 3)) + "): ");

            String input = scanner.nextLine();

            if(input.toUpperCase().equals("X")) break;

            frequency = Double.parseDouble(input);
            if(frequency > level * 3){
                Tools.out("\n\nWoa! Calm down! Too high frequency for this machine's level. Try upgrading it. (press enter)\n\n");
                frequency = 0;
                open = false;
                scanner.nextLine();
            }else{
                Tools.out("\n\nAmount of cores (i.e. 2, 3, 6...; maximum is always 32): ");

                input = scanner.nextLine();

                cores = Integer.parseInt(input);

                if(cores > 32){
                    Tools.out("\n\nWhy don't you obey the rules? (press enter)\n\n");
                    cores = 0;
                    open = false;
                    scanner.nextLine();
                }else{
                    Tools.out("\nYou're about to spend " + Math.floor(frequency * cores * 30) + " iron, " + Math.floor(frequency * cores * 25) + " silicon, " + Math.floor(cores * 10) + " gold, " + Math.floor(cores * 35) + " copper and " + Math.floor(frequency * cores * 5) + " plastic.\nAre you sure you want to build this CPU? (y/anything else)\n\n");

                    input = scanner.nextLine();

                    if(input.toUpperCase().equals("Y")){
                         if(enoughMaterials(Math.floor(frequency * cores * 30), Math.floor(frequency * cores * 25), cores * 10, cores * 35, Math.floor(frequency * cores * 5))){
                            materials[0].amount -= Math.floor(frequency * cores * 30);
                            materials[1].amount -= Math.floor(frequency * cores * 25);//fe, sl, au, cu, pl
                            materials[2].amount -= cores * 10;
                            materials[3].amount -= cores * 35;
                            materials[4].amount -= Math.floor(frequency * cores * 5);

                            Ship.hardwareStorage.add(new CPU(frequency, cores));

                            Tools.out("\nCPU added to ship storage. (press enter)\n\n");
                            scanner.nextLine();
                        }else{
                            Tools.out("\nNot enough materials. (press enter)\n\n");
                            scanner.nextLine();
                        }
                        open = false;
                    }else{
                        break;
                    }
                }
            }
        }
    }

    private void dronePartsSubmenu() throws NumberFormatException {
        boolean open = true;

        while(open) {
            Tools.out("\nWelcome to the S.P.'s v.13 Menus4U's trademarked menu's produce submenu's drone parts submenu.\n\nWhat drone part do you want to build?\n\n");

            for(int i = 0; i < dronePartList.length; i++){
                Tools.out((i + 1) + ". " + dronePartList[i].name + "\n");
            }

            Tools.out("x. Return\n\n");

            String input = scanner.nextLine();

            if(input.toUpperCase().equals("X")) open = false;
            else{
                for(int i = 0; i < dronePartList.length; i++){
                    if(input.equals(i + 1 + "")){
                        Tools.out("\nYou will need:\n\n");

                        for(int a = 0; a < dronePartList[i].materialsToBuild.length; a++){
                            Tools.out(dronePartList[i].materialsToBuild[a].name + ": " + Math.floor(dronePartList[i].materialsToBuild[a].amount) + "\n");
                        }

                        Tools.out("\nDo you want to build this? (y/anything else)\n\n");

                        input = scanner.nextLine();

                        if(input.toUpperCase().equals("Y")){
                            if(enoughMaterials(dronePartList[i].materialsToBuild[0].amount, dronePartList[i].materialsToBuild[1].amount, dronePartList[i].materialsToBuild[2].amount, dronePartList[i].materialsToBuild[3].amount, dronePartList[i].materialsToBuild[4].amount)){

                                for(int j = 0; j < materials.length; j++) materials[j].amount -= dronePartList[i].materialsToBuild[j].amount;

                                Ship.dronePartsStorage.add(dronePartList[i]);
                                Tools.out("\n" + dronePartList[i].name + " added to ship storage for further use. (press enter)\n\n");
                                scanner.nextLine();
                            }else{
                                Tools.out("\nNot enough materials. (press enter)\n\n");
                                scanner.nextLine();
                            }
                        }else{
                            break;
                        }
                    }
                }
            }
        }
    }


    private boolean enoughMaterials(double fe, double sl, double au, double cu, double pl){
        if(materials[0].amount >= fe && materials[1].amount >= sl && materials[2].amount >= au && materials[3].amount > cu && materials[4].amount >= pl) {
            return true;
        }else{
            return false;
        }
    }
}
