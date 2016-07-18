package data.components.production;

import data.main_computer.hardware.CPU;
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

    private int fe, sl, au, cu, pl;

    private Scanner scanner = new Scanner(System.in);

    public ScrapProcessor(){
        name = "Scrap Processor";
        description = "This processes scrap into materials that can be used to build various hardware.\n";
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
    public void activate() throws Exception{
        productionMenu();
    }

    private void productionMenu() throws Exception{
        boolean open = true;

        while(open){
            Tools.out("\n\nWelcome to the S.P.'s v1.3 Menus4U's trademarked menu.\n\nWhat can we do for you?\n\n1. Check material storage\n2. Separate scrap into materials\n3. Produce something\nx. Exit menu\n\n");

            String input = scanner.nextLine();

            switch(input.toUpperCase()){
                case "1":
                    materialStorage();
                    break;
                case "2":
                    separate();
                    break;
                case "3":
                    produce();
                    break;
                case "X":
                    open = false;
                    Tools.out("\nThank you for using our service. Come back soon for more top-notch graphical menus.\n(press enter)\n\n");
                    scanner.nextLine();
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    break;
            }
        }
    }

    private void materialStorage(){
        Tools.out("\nMetal: " + fe + ";\nSilicon: " + sl + ";\nGold: " + au + ";\nCopper: " + cu + ";\nPlastic: " + pl + ". (press enter)\n\n");
        scanner.nextLine();
    }
    private void separate(){
        Random r = new Random();

        Tools.out("\nHow much scrap do you want to process?\n");
        String input = scanner.nextLine();

        try{
            int scrap = Integer.parseInt(input);

            if(Ship.scrap >= scrap){

                Ship.scrap -= scrap;
                fe += r.nextInt(12) * scrap + r.nextInt(5);
                sl += r.nextInt(6) * scrap + r.nextInt(5);
                au += r.nextInt(4) * scrap + r.nextInt(5);
                cu += r.nextInt(16) * scrap + r.nextInt(5);
                pl += r.nextInt(8) * scrap + r.nextInt(5);

                Tools.out("\nMaterials added to storage. (press enter)\n\n");
                scanner.nextLine();

            }else{
                Tools.out("\nNot enough scrap. Go away you lower-middle class citizen! Shoo! (press enter)\n\n");
                scanner.nextLine();
            }
        }catch(NumberFormatException e){
            Tools.out("\nNumbers please. (press enter)\n\n");
            scanner.nextLine();
        }
    }
    private void produce() throws Exception {
        boolean open = true;

        while(open){
            Tools.out("\nWelcome to the S.P.'s v.13 Menus4U's trademarked menu's produce hardware submenu.\n\nWhat hardware do you want to build?\n\n1. CPU\n2. SSD\n3. RAM\n4. Safety hardware\nx. Return\n\n");

            String input = scanner.nextLine();

            switch(input.toUpperCase()){
                case "1":
                    produceCPU();
                    break;
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

            if(input.equals("x")) break;

            try{
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
                        Tools.out("\nYou're about to spend " + Math.floor(frequency * cores * 30) + " iron, " + Math.floor(frequency * cores * 25) + " silicon, " + cores * 10 + ".0 gold, " + cores * 35 + ".0 copper and " + Math.floor(frequency * cores * 5) + " plastic.\nAre you sure you want to build this CPU? (y/anything else)\n\n");

                        input = scanner.nextLine();

                        if(input.toUpperCase().equals("Y")){
                            if(fe >= Math.floor(frequency * cores * 30) && sl >= Math.floor(frequency * cores * 25) && au >= cores * 10 && cu > cores * 35 && pl >= Math.floor(frequency * cores * 5)) {
                                fe -= Math.floor(frequency * cores * 30);
                                sl -= Math.floor(frequency * cores * 25);
                                au -= cores * 10;
                                cu -= cores * 35;
                                pl -= Math.floor(frequency * cores * 5);

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

            }catch (NumberFormatException e){
                Tools.out("\nError processing request. (press enter)\n\n");
                scanner.nextLine();
            }
        }
    }
}
