package tools;

import game.GameConstants;

/**
 * Created by Tomás on 29/03/2016.
 */
public class Tools {
    public static void out(String arg){
        System.out.print(arg);
    }
    /*public static Drone[] increaseArraySize(Drone[] array){
        Drone[] temp = new Drone[array.length + 1];
        for(int i = 0; i < array.length; i++){
            temp[i] = array[i];
        }
        return temp;
    }
    public static boolean[] increaseArraySize(boolean[] array){
        boolean[] temp = new boolean[array.length + 1];
        for(int i = 0; i < array.length; i++){
            temp[i] = array[i];
        }
        return temp;
    }*/
    public static void drawCommandsHelp(){
        out("\nList of all commands:\n\n");

        for(int i = 0; i < GameConstants.commands.length; i++){
            out(GameConstants.commands[i] + " - " + GameConstants.commandDescriptions[i] + "\n");
        }

        out("\n\n");
    }
    public static void drawComponentsHelp(){
        out("\nList of all components:\n\n");

        for(int i = 0; i < GameConstants.components.length; i++){
            out(GameConstants.components[i] + " - " + GameConstants.componentDescriptions[i] + "\n");
        }

        out("\n\n");
    }
    public static void cls(){
        try{new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();}catch(Exception e){}
    }
    public static boolean intToBool(int i){
        return i != 0;
    }
}
