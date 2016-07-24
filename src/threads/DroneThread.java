package threads;

import tools.Tools;
import world.map.entities.Drone;
import world.map.entities.Ship;
import world.map.Map;
import world.map.entities.Space;

/**
 * Created by Tomás on 02/04/2016.
 */
public class DroneThread implements Runnable {
    private static Thread thread;
    private static boolean running = false;
    public static boolean shipMining = false;
    public static Drone[] dronesToCheck = new Drone[Ship.drones.length];

    public static void insertDrone(Drone drone){
        for(int i = 0; i < dronesToCheck.length; i++){
            if(dronesToCheck[i] == null){
                dronesToCheck[i] = drone;
                break;
            }
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
            if(dronesToCheck[i] != null){
                dronesToCheck[i].extractionProgress++;
                if(dronesToCheck[i].extractionProgress == Map.map[dronesToCheck[i].x][dronesToCheck[i].y].extractionTime){
                    dronesToCheck[i].water += Map.map[dronesToCheck[i].x][dronesToCheck[i].y].water;
                    dronesToCheck[i].food += Map.map[dronesToCheck[i].x][dronesToCheck[i].y].food;
                    dronesToCheck[i].fuel += Map.map[dronesToCheck[i].x][dronesToCheck[i].y].fuel;
                    dronesToCheck[i].medicalEquipment += Map.map[dronesToCheck[i].x][dronesToCheck[i].y].medicalEquipment;
                    dronesToCheck[i].scrap += Map.map[dronesToCheck[i].x][dronesToCheck[i].y].scrap;
                    Tools.out("\n - Successfully extracted:\n\t" + Map.map[dronesToCheck[i].x][dronesToCheck[i].y].water + " water;\n\t" + Map.map[dronesToCheck[i].x][dronesToCheck[i].y].food + " food;\n\t" + Map.map[dronesToCheck[i].x][dronesToCheck[i].y].fuel + " fuel;\n\t" + Map.map[dronesToCheck[i].x][dronesToCheck[i].y].medicalEquipment + " medical equipment;\n\t" + Map.map[dronesToCheck[i].x][dronesToCheck[i].y].scrap + " scrap;\n\n");
                    Map.map[dronesToCheck[i].x][dronesToCheck[i].y] = new Space();
                    dronesToCheck[i].extractionProgress = 0;
                    Ship.drones[i] = dronesToCheck[i];
                    dronesToCheck[i] = null;
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
        thread = new Thread(new DroneThread());
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
