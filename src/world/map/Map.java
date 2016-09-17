package world.map;

import tools.Tools;
import world.map.entities.Asteroid;
import world.map.entities.LargeAsteroid;
import world.map.entities.Ship;
import world.map.entities.Space;

import java.util.Random;

/**
 * Created by Tomás on 30/03/2016.
 */
public class Map {
    public static Tile[][] map = new Tile[20][20];
    private static Random r = new Random();

    public static void displayMap(){
        Tools.out("\n            1\n");
        Tools.out("  01234567890123456789\n");
        for(int y = 0; y < 20; y++){
            for(int x = 0; x < 22; x++){
                if(x == 0 && y != 10){Tools.out(" ");}
                if(y == 10 && x == 0){Tools.out("1");}
                if(x == 1 && y < 10){Tools.out(y + "");}
                if(y >= 10 && x == 1){Tools.out((y - 10) + "");}
                if(x > 1){
                    String icon = map[x - 2][y].icon;
                    if(x - 2 == Ship.x && y == Ship.y) icon = "@";
                    else{
                        for(int i = 0; i < Ship.drones.size(); i++){
                            if(x - 2 == Ship.drones.get(i).x && y == Ship.drones.get(i).y) icon = "D";
                        }
                    }
                    Tools.out(icon);
                }
            }
            Tools.out("\n");
        }
        Tools.out("\n\n");
    }

    public static void generateSector(){
        Ship.x = r.nextInt(20);
        Ship.y = r.nextInt(20);

        for(int x = 0; x < 20; x++){
            for(int y = 0; y < 20; y++){
                Map.map[x][y] = new Space();
            }
        }

        for(int x = 0; x < 20; x++){
            for(int y = 0; y < 20; y++){
                if(r.nextInt(20) == 0){
                    Map.map[x][y] = new Asteroid();
                    Map.map[x][y].icon = Asteroid.icon;
                }
                else if(r.nextInt(50) == 0){
                    Map.map[x][y] = new LargeAsteroid();
                    Map.map[x][y].icon = LargeAsteroid.icon;
                }
                else{
                    Map.map[x][y] = new Space();
                    Map.map[x][y].icon = Space.icon;
                }
                Map.map[x][y].x = x;
                Map.map[x][y].y = y;
            }
        }
    }
}