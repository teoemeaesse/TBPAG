package threads;

import data.components.production.Hydroponics;
import world.map.entities.Ship;

/**
 * Created by Tomás on 07/05/2016.
 */
public class HydroponicsThread implements Runnable {
    private static Thread thread;
    private static boolean running = false;
    private static double fps = 1;

    public void run() {
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

            for(int i = 0; i < Ship.components.length; i++){
                if(Ship.components[i] != null && Ship.components[i].name.equals("Hydroponics")){
                    fps = 1.0 / Ship.components[i].timeTaken;
                    timePerTick = 1000000000 / fps;
                }
            }

            if (delta >= 1) {
                for(int i = 0; i < Ship.components.length; i++){
                    if(Ship.components[i] != null && Ship.components[i].name.equals("Hydroponics") && Ship.components[i].active){
                        ((Hydroponics) Ship.components[i]).hydroponics();
                    }
                }
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
        thread = new Thread(new HydroponicsThread());
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
