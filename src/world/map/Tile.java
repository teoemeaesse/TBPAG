package world.map;

/**
 * Created by Tomás on 30/03/2016.
 */
public class Tile {
    public int x, y, miningTime = 1;
    public int[] resources = {0, 0, 0, 0, 0}; //water, food, fuel, medequip, scrap
    public boolean allowedIn = true, mineable = false;
    public String icon, name;

    public void interact(){}
}
