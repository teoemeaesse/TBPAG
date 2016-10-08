package threads;

import data.components.JumpDrive;
import tools.Tools;
import world.map.Map;
import world.map.entities.Ship;

import java.util.Random;

/**
 * Created by Tomás on 08/10/2016.
 */
public class JumpThread implements Runnable {
    private static Thread thread;
    private static Random r = new Random();
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
                if(Ship.components[i] != null && Ship.components[i].name.equals("Jump Drive")){
                    fps = 1.0 / Ship.components[i].timeTaken;
                    timePerTick = 1000000000 / fps;
                    JumpDrive.jumping = true;
                }
                else JumpDrive.jumping = false;
            }

            if (delta >= 1) {
                Map.generateSector(false, Tools.intToBool(r.nextInt(2)), Tools.intToBool(r.nextInt(2)));
                Tools.out("\nSuccessfully jumped to another sector. Check map to scan it.\n\n");
                stop();
                delta--;
            }else{
                Map.generateTravelGFX();
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
        thread = new Thread(new JumpThread());
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
