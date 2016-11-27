package thread;

import java.util.ArrayList;

/**
 * Created by Tomás on 26/11/2016.
 */
public class GameThread implements Runnable {
    public Thread thread;
    public double fps = 1;
    public double timePerTick = 1000000000 / fps;

    public static ArrayList<GameThread> gameThreads = new ArrayList<>();

    public void run(){
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;

        while(thread != null){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            setFps();

            if(delta >= 1){
                action();
                delta--;
            }

            if(timer >= 1000000000){
                timer = 0;
            }
        }

        stop();
    }

    public synchronized void start(){
        if (thread != null) return;
        thread = new Thread(this);
        thread.start();
    }

    public void stop(){
        thread = null;
    }

    public void action(){}
    public void setFps(){}
}