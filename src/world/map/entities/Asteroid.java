package world.map.entities;

import game.GameConstants;
import world.map.Tile;

import java.util.Random;

/**
 * Created by Tomás on 30/03/2016.
 */
public class Asteroid extends Tile {
    private static Random r = new Random();
    public static String icon = GameConstants.MAP_ASTEROID, name = GameConstants.NAME_ASTEROID;
    public int water = r.nextInt(5), fuel = r.nextInt(8 - 3) + 3, scrap = r.nextInt(4 - 1) + 1, extractionTime = r.nextInt(40 - 5) + 5;

    public Asteroid(){
        super.name = name;
        super.icon = icon;
        super.water = water;
        super.fuel = fuel;
        super.scrap = scrap;
        super.extractionTime = extractionTime;
    }
}
