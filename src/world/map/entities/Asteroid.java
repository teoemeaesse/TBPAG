package world.map.entities;

import game.Settings;
import world.map.Tile;

import java.util.Random;

/**
 * Created by Tomás on 30/03/2016.
 */
public class Asteroid extends Tile {
    public Asteroid(){
        Random r = new Random();
        this.icon = Settings.MAP_ASTEROID;
        this.name = Settings.NAME_ASTEROID;
        this.resources[0] = r.nextInt(5);
        this.resources[2] = r.nextInt(8 - 3) + 3;
        this.resources[4] = r.nextInt(4 - 1) + 1;
        this.extractionTime = r.nextInt(40 - 5) + 5;
    }
}
