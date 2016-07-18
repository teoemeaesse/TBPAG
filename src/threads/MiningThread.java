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
    public static boolean[] dronesToCheck = new boolean[Ship.drones.length];

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
                Ship.water += Map.map[Ship.x][Ship.y].water;
                Ship.food += Map.map[Ship.x][Ship.y].food;
                Ship.fuel += Map.map[Ship.x][Ship.y].fuel;
                Ship.medicalEquipment += Map.map[Ship.x][Ship.y].medicalEquipment;
                Ship.scrap += Map.map[Ship.x][Ship.y].scrap;

                Tools.out("\n - Successfully extracted:\n\t" + Map.map[Ship.x][Ship.y].water + " water;\n\t" + Map.map[Ship.x][Ship.y].food + " food;\n\t" + Map.map[Ship.x][Ship.y].fuel + " fuel;\n\t" + Map.map[Ship.x][Ship.y].medicalEquipment + " medical equipment;\n\t" + Map.map[Ship.x][Ship.y].scrap + " scrap;\n\n");

                Map.map[Ship.x][Ship.y] = new Space();
                Ship.extractionProgress = 0;
                shipMining = false;
            }
        }
        for(int i = 0; i < dronesToCheck.length; i++){
            if(dronesToCheck[i]){
                Ship.drones[i].extractionProgress++;
                if(Ship.drones[i].extractionProgress == Map.map[Ship.drones[i].x][Ship.drones[i].y].extractionTime){
                    Ship.drones[i].water += Map.map[Ship.drones[i].x][Ship.drones[i].y].water;
                    Ship.drones[i].food += Map.map[Ship.drones[i].x][Ship.drones[i].y].food;
                    Ship.drones[i].fuel += Map.map[Ship.drones[i].x][Ship.drones[i].y].fuel;
                    Ship.drones[i].medicalEquipment += Map.map[Ship.drones[i].x][Ship.drones[i].y].medicalEquipment;
                    Ship.drones[i].scrap += Map.map[Ship.drones[i].x][Ship.drones[i].y].scrap;

                    if(Ship.drones[i].water > Drone.waterCapacity) Ship.drones[i].water = Drone.waterCapacity;
                    if(Ship.drones[i].food > Drone.foodCapacity) Ship.drones[i].food = Drone.foodCapacity;
                    if(Ship.drones[i].fuel > Drone.fuelCapacity) Ship.drones[i].fuel = Drone.fuelCapacity;
                    if(Ship.drones[i].medicalEquipment > Drone.medicalEquipmentCapacity) Ship.drones[i].medicalEquipment = Drone.medicalEquipmentCapacity;
                    if(Ship.drones[i].scrap > Drone.scrapCapacity) Ship.drones[i].scrap = Drone.scrapCapacity;

                    Tools.out("\n - Successfully extracted:\n\t" + Map.map[Ship.drones[i].x][Ship.drones[i].y].water + " water;\n\t" + Map.map[Ship.drones[i].x][Ship.drones[i].y].food + " food;\n\t" + Map.map[Ship.drones[i].x][Ship.drones[i].y].fuel + " fuel;\n\t" + Map.map[Ship.drones[i].x][Ship.drones[i].y].medicalEquipment + " medical equipment;\n\t" + Map.map[Ship.drones[i].x][Ship.drones[i].y].scrap + " scrap. Some may or may not been left behind due to missing storage space.\n\n");

                    Map.map[Ship.drones[i].x][Ship.drones[i].y] = new Space();
                    Ship.drones[i].extractionProgress = 0;
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
