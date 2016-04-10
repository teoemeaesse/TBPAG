package world.map.entities;

import world.map.Tile;

import java.util.Random;

/**
 * Created by Tomás on 30/03/2016.
 */
public class LargeAsteroid extends Tile {
    private static Random r = new Random();
    public static String icon = "o", name = " large asteroid";
    public int water = r.nextInt(12), fuel = r.nextInt(16 - 4) + 4, scrap = r.nextInt(10 - 3) + 3, extractionTime = r.nextInt(200 - 20) + 20;

    public LargeAsteroid(){
        super.name = name;
        super.water = water;
        super.fuel = fuel;
        super.scrap = scrap;
        super.extractionTime = extractionTime;
    }
}