package world.map.entities;

import game.Settings;
import world.map.Tile;

import java.util.Random;

/**
 * Created by Tomás on 30/03/2016.
 */
public class LargeAsteroid extends Tile {
    public LargeAsteroid(){
        Random r = new Random();
        icon = Settings.MAP_LARGE_ASTEROID;
        name = Settings.NAME_LARGE_ASTEROID;
        resources[0] = r.nextInt(12);
        resources[2] = r.nextInt(16 - 4) + 4;
        resources[4] = r.nextInt(10 - 3) + 3;
        miningTime = r.nextInt(140 - 20) + 20;
        mineable = true;
    }
}
