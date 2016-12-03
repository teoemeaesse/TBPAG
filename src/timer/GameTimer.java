package timer;

import data.Component;
import data.components.production.Hydroponics;
import game.Settings;
import tools.Tools;
import world.map.Map;
import world.map.entities.Drone;
import world.map.entities.Ship;
import world.map.entities.Space;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Tomás on 03/12/2016.
 */
public class GameTimer {
    public Timer timer;
    public static ArrayList<Boolean> dronesToCheck = new ArrayList<>();

    public GameTimer(){
        timer = new Timer();
        start();
    }

    private void start(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                action();
                start();
            }
        }, 1000);
    }


    private void action(){
        //MINING
        tick();
        extract();

        //COMPONENTS
        components();
    }

    //MINING
    public static void insertDrone(int drone){
        if(!dronesToCheck.get(drone)){
            dronesToCheck.add(drone, true);
        }else{
            Tools.out("\nDrone is already mining...\n\n");
        }
    }
    private static void extract(){
        if(Ship.miningProgress == Map.map[Ship.x][Ship.y].miningTime && Ship.mining){
            for(int i = 0; i < Ship.resources.length; i++) Ship.resources[i] += Map.map[Ship.x][Ship.y].resources[i];

            Tools.out("\n - Successfully extracted:\n\t" + Map.map[Ship.x][Ship.y].resources[0] + " water;\n\t" + Map.map[Ship.x][Ship.y].resources[1] + " food;\n\t" + Map.map[Ship.x][Ship.y].resources[2] + " fuel;\n\t" + Map.map[Ship.x][Ship.y].resources[3] + " medical equipment;\n\t" + Map.map[Ship.x][Ship.y].resources[4] + " scrap;\n\n");

            Map.map[Ship.x][Ship.y] = new Space();
            Ship.mining = false;
        }

        for(int i = 0; i < dronesToCheck.size(); i++){
            if(dronesToCheck.get(i) != null && dronesToCheck.get(i)){
                if(Ship.drones.get(i).miningProgress == Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].miningTime){
                    for(int a = 0; a < Ship.drones.get(i).resources.length; a++){
                        Ship.drones.get(i).resources[a] += Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].resources[a];
                        if(Ship.drones.get(i).resources[a] > Drone.resourceCapacities[a]) Ship.drones.get(i).resources[a] = Drone.resourceCapacities[a];
                    }

                    Tools.out("\n - Successfully extracted:\n\t" + Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].resources[0] + " water;\n\t" + Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].resources[1] + " food;\n\t" + Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].resources[2] + " fuel;\n\t" + Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].resources[3] + " medical equipment;\n\t" + Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].resources[4] + " scrap. Some may or may not been left behind due to missing storage space.\n\n");

                    Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y] = new Space();
                    Ship.drones.get(i).stopMining();
                    dronesToCheck.remove(i);
                }
            }
        }
    }
    private void tick(){
        if(Ship.mining) Ship.miningProgress++;
        for(int i = 0; i < dronesToCheck.size(); i++) if(Ship.drones.get(i).mining) Ship.drones.get(i).miningProgress++;
    }

    //COMPONENTS
    private void components(){
        for(Component i : Ship.components) if(i != null) i.action();
    }
}
