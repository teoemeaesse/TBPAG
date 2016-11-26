package world.map.entities;

import data.Event;
import data.commerce.Commerce;
import data.events.returnable_events.EntranceDAEvent;
import data.events.void_events.BrokeDockEvent;
import data.events.void_events.MissedDockEvent;
import game.GameConstants;
import tools.Tools;
import world.map.Tile;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Tomás on 08/10/2016.
 */
public class AutomatedTradingPost extends Tile {
    public static String icon = GameConstants.MAP_AMTP, name = GameConstants.NAME_ATP;
    public int tax;
    public int[] resources = new int[5];
    private static Scanner scanner = new Scanner(System.in);
    private static Random r = new Random();

    public AutomatedTradingPost(){
        super.name = name;
        super.icon = icon;
        tax = (r.nextInt(1000 - 1) + 1) / 100 + 1;
        generateResources();
    }

    @Override
    public void interact(){
        if(Event.fireEventB(new EntranceDAEvent(x, y))){
            Event.fireEvents(new MissedDockEvent(), new BrokeDockEvent(x, y));
            if(Event.fireEventB(new EntranceDAEvent(x, y))){
                boolean open = true;

                while(open){
                    try{
                        Tools.out("Hello, human, how may I help you?");
                        open = mainMenu();
                    }catch(Exception e){Tools.out("\n\n\nError processing request\n\n\n");}
                }

            }
        }
    }


    private boolean mainMenu() throws Exception {
        boolean open1 = true, open2 = true;
        Tools.cls();
        Tools.out("\n\n1. Purchase goods\n2. Sell goods\nx. Leave\n\n");

        String input = scanner.nextLine();

        if(input.toUpperCase().equals("X")){
            Tools.out("\n\nGoodbye, human (press enter).\n\n");
            scanner.nextLine();
            open1 = false;
        }else if(Integer.parseInt(input) == 1){
            while(open2){
                listResources(true);
                input = scanner.nextLine();
                int resourceID = Integer.parseInt(input) - 1;
                Tools.out("\nHow much?\n(space: " + (Ship.resourceCapacities[resourceID] - Ship.resources[resourceID]) + "; money: " + Ship.money + "$)\n\n");
                input = scanner.nextLine();
                if(Commerce.purchase(Integer.parseInt(input), resources[resourceID], resourceID, tax)) {
                    open2 = false;
                    resources[resourceID] -= Integer.parseInt(input);
                    Tools.out("\nSuccessfully processed order. Thank you for using our service.\n\n");
                }else Tools.out("\nError purchasing. Check if you have enough space/money.\n\n");
            }
        }else if(Integer.parseInt(input) == 2){
            while(open2){
                listResources(false);
                input = scanner.nextLine();
                int resourceID = Integer.parseInt(input) - 1;
                Tools.out("\nHow much?\n\n");
                input = scanner.nextLine();
                if(Commerce.sell(Integer.parseInt(input), resourceID, tax)) {
                    open2 = false;
                    resources[resourceID] += Integer.parseInt(input);
                    Tools.out("\nSuccessfully processed transaction. Thank you for using our service.\n\n");
                }else Tools.out("\nError selling.\n\n");
            }
        }


        return open1;
    }

    private void listResources(boolean purchasing){
        Tools.out("\nResources:\n\n");
        if(purchasing) for(int i = 0; i < resources.length; i++) Tools.out((i + 1) + ". " + GameConstants.NAMES_RESOURCES[i] + " (" + Math.floor(GameConstants.BASE_VALUE_RESOURCES[i] * tax) + "$/each; " + resources[i] + " in stock)" + "\n");
        else for(int i = 0; i < resources.length; i++) Tools.out((i + 1) + ". " + GameConstants.NAMES_RESOURCES[i] + " (" + Math.floor(GameConstants.BASE_VALUE_RESOURCES[i] * tax) + "$/each; " + Ship.resources[i] + " on-board)" + "\n");
        Tools.out("\n");
    }


    private void generateResources(){
        for(int i = 0; i < resources.length; i++){
            resources[i] = r.nextInt(30 - 2) + 2;
        }
    }
}