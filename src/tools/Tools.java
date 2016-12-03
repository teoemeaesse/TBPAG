package tools;

import game.Settings;

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

        for(int i = 0; i < Settings.commands.length; i++){
            out(Settings.commands[i] + " - " + Settings.commandDescriptions[i] + "\n");
        }

        out("\n\n");
    }
    public static void drawComponentsHelp(){
        out("\nList of all components:\n\n");

        for(int i = 0; i < Settings.components.length; i++){
            out(Settings.components[i] + " - " + Settings.componentDescriptions[i] + "\n");
        }

        out("\n\n");
    }
    public static String trimSpaces(String untrimmed){
        return untrimmed.replace(" ", "");
    }
    public static void cls(){
        try{new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();}catch(Exception e){}
    }
    public static boolean intToBool(int i){
        return i != 0;
    }
}
