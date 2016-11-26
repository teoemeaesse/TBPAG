package threads;

import tools.Tools;
import world.map.entities.Drone;
import world.map.entities.Ship;
import world.map.Map;
import world.map.entities.Space;

/**
 * Created by Tomás on 02/04/2016.
 */
public class MiningThread implements Runnable {
    private static Thread thread;
    private static boolean running = false;
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
                Ship.resources[0] += Map.map[Ship.x][Ship.y].water;
                Ship.resources[1] += Map.map[Ship.x][Ship.y].food;
                Ship.resources[2] += Map.map[Ship.x][Ship.y].fuel;
                Ship.resources[3] += Map.map[Ship.x][Ship.y].medicalEquipment;
                Ship.resources[4] += Map.map[Ship.x][Ship.y].scrap;

                Tools.out("\n - Successfully extracted:\n\t" + Map.map[Ship.x][Ship.y].water + " water;\n\t" + Map.map[Ship.x][Ship.y].food + " food;\n\t" + Map.map[Ship.x][Ship.y].fuel + " fuel;\n\t" + Map.map[Ship.x][Ship.y].medicalEquipment + " medical equipment;\n\t" + Map.map[Ship.x][Ship.y].scrap + " scrap;\n\n");

                Map.map[Ship.x][Ship.y] = new Space();
                Ship.extractionProgress = 0;
                shipMining = false;
            }
        }
        for(int i = 0; i < dronesToCheck.length; i++){
            if(dronesToCheck[i]){
                Ship.drones.get(i).extractionProgress++;
                if(Ship.drones.get(i).extractionProgress == Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].extractionTime){
                    Ship.drones.get(i).water += Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].water;
                    Ship.drones.get(i).food += Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].food;
                    Ship.drones.get(i).fuel += Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].fuel;
                    Ship.drones.get(i).medicalEquipment += Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].medicalEquipment;
                    Ship.drones.get(i).scrap += Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].scrap;

                    if(Ship.drones.get(i).water > Drone.waterCapacity) Ship.drones.get(i).water = Drone.waterCapacity;
                    if(Ship.drones.get(i).food > Drone.foodCapacity) Ship.drones.get(i).food = Drone.foodCapacity;
                    if(Ship.drones.get(i).fuel > Drone.fuelCapacity) Ship.drones.get(i).fuel = Drone.fuelCapacity;
                    if(Ship.drones.get(i).medicalEquipment > Drone.medicalEquipmentCapacity) Ship.drones.get(i).medicalEquipment = Drone.medicalEquipmentCapacity;
                    if(Ship.drones.get(i).scrap > Drone.scrapCapacity) Ship.drones.get(i).scrap = Drone.scrapCapacity;

                    Tools.out("\n - Successfully extracted:\n\t" + Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].water + " water;\n\t" + Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].food + " food;\n\t" + Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].fuel + " fuel;\n\t" + Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].medicalEquipment + " medical equipment;\n\t" + Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y].scrap + " scrap. Some may or may not been left behind due to missing storage space.\n\n");

                    Map.map[Ship.drones.get(i).x][Ship.drones.get(i).y] = new Space();
                    Ship.drones.get(i).extractionProgress = 0;
                    dronesToCheck[i] = false;
                }
            }
        }
    }

    public void run() {
        double fps = 1;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                extract();
                delta--;
            }

            if (timer >= 1000000000) {
                timer = 0;
            }
        }

        stop();

    }

    public static synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(new MiningThread());
        thread.start();
    }

    public synchronized void stop() {
        if (!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
