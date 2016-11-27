package world.map.entities;

import game.GameConstants;
import world.map.Tile;

import java.util.Random;

/**
 * Created by Tomás on 30/03/2016.
 */
public class LargeAsteroid extends Tile {
    public LargeAsteroid(){
        Random r = new Random();
        this.icon = GameConstants.MAP_LARGE_ASTEROID;
        this.name = GameConstants.NAME_LARGE_ASTEROID;
        this.resources[0] = r.nextInt(12);
        this.resources[2] = r.nextInt(16 - 4) + 4;
        this.resources[4] = r.nextInt(10 - 3) + 3;
        this.extractionTime = r.nextInt(140 - 20) + 20;
    }
}
