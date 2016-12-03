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
        icon = Settings.MAP_ASTEROID;
        name = Settings.NAME_ASTEROID;
        resources[0] = r.nextInt(5);
        resources[2] = r.nextInt(8 - 3) + 3;
        resources[4] = r.nextInt(4 - 1) + 1;
        miningTime = r.nextInt(40 - 5) + 5;
        mineable = true;
    }
}
