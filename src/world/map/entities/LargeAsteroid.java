package world.map.entities;

import game.GameConstants;
import world.map.Tile;

import java.util.Random;

/**
 * Created by Tomás on 30/03/2016.
 */
public class LargeAsteroid extends Tile {
    private static Random r = new Random();
    public static String icon = GameConstants.MAP_LARGE_ASTEROID, name = GameConstants.NAME_LARGE_ASTEROID;
    public int water = r.nextInt(12), fuel = r.nextInt(16 - 4) + 4, scrap = r.nextInt(10 - 3) + 3, extractionTime = r.nextInt(140 - 20) + 20;

    public LargeAsteroid(){
        super.name = name;
        super.icon = icon;
        super.water = water;
        super.fuel = fuel;
        super.scrap = scrap;
        super.extractionTime = extractionTime;
    }
}
