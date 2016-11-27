package thread.threads;

import thread.GameThread;
import tools.Tools;
import world.map.entities.Drone;
import world.map.entities.Ship;
import world.map.Map;
import world.map.entities.Space;

/**
 * Created by Tomás on 02/04/2016.
 */
public class MiningThread extends GameThread {
    public static boolean shipMining = false;
    public static boolean[] dronesToCheck = new boolean[Ship.drones.size()];

    public static void insertDrone(int drone){
        if(!dronesToCheck[drone]){
            dronesToCheck[drone] = true;
        }else{
            Tools.out("\nDrone is already mining...\n\n");
        }
    }

    private static void extract(){
        if(shipMining){
            Ship.extractionProgress++;
            if(Ship.extractionProgress == Map.map[Ship.x][Ship.y].extractionTime){
                for(int i = 0; i < Ship.resources.length; i++) Ship.resources[i] += Map.map[Ship.x][Ship.y].resources[i];

                Tools.out("\n - Successfully extracted:\n\t" + Map.map[Ship.x][Ship.y].resources[0] + " water;\n\t" + Map.map[Ship.x][Ship.y].resources[1] + " food;\n\t" + Map.map[Ship.x][Ship.y].resources[2] + " fuel;\n\t" + Map.map[Ship.x][Ship.y].resources[3] + " medical equipment;\n\t" + Map.map[Ship.x][Ship.y].resources[4] + " scrap;\n\n");

                Map.map[Ship.x][Ship.y] = new Space();
                Ship.extractionProgress = 0;
                shipMining = false;
            }
        }
        for(int i = 0; i < dronesToCheck.length; i++){
            if(dronesToCheck[i]){
                Ship.drones.get(i).extractionProgress++;
                if(Ship.drones.get(i).extractionProgress == Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].extractionTime){
                    for(int a = 0; a < Ship.drones.get(i).resources.length; a++) Ship.drones.get(i).resources[a] += Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].resources[a];
                    for(int a = 0; a < Ship.drones.get(i).resources.length; a++) if(Ship.drones.get(i).resources[a] > Drone.resourceCapacities[a]) Ship.drones.get(i).resources[a] = Drone.resourceCapacities[a];

                    Tools.out("\n - Successfully extracted:\n\t" + Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].resources[0] + " water;\n\t" + Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].resources[1] + " food;\n\t" + Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].resources[2] + " fuel;\n\t" + Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].resources[3] + " medical equipment;\n\t" + Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].resources[4] + " scrap. Some may or may not been left behind due to missing storage space.\n\n");

                    Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y] = new Space();
                    Ship.drones.get(i).extractionProgress = 0;
                    dronesToCheck[i] = false;
                }
            }
        }
    }

    @Override
    public void action(){
        extract();
    }
}
