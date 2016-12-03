package world.map;

import tools.Tools;
import world.map.entities.*;

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

    public static void generateSector(/*boolean firstTime, USE ONCE THE SAVE FEATURE HAS BEEN IMPLEMENTED*/){
        boolean hasATP = Tools.intToBool(r.nextInt(2));

        for(int x = 0; x < 20; x++){
            for(int y = 0; y < 20; y++){
                Map.map[x][y] = new Space();
            }
        }

        for(int x = 0; x < 20; x++){
            for(int y = 0; y < 20; y++){
                if(r.nextInt(20) == 0){
                    Map.map[x][y] = new Asteroid();
                }
                else if(r.nextInt(50) == 0){
                    Map.map[x][y] = new LargeAsteroid();
                }
                else{
                    Map.map[x][y] = new Space();
                }
                Map.map[x][y].x = x;
                Map.map[x][y].y = y;
            }
        }

        if(hasATP) Map.map[r.nextInt(20)][r.nextInt(20)] = new AutomatedTradingPost();
    }

    public static void generateTravelGFX(){
        for(int x = 0; x < 20; x++){
            for(int y = 0; y < 20; y++){
                Map.map[x][y] = new Space();
            }
        }

        for(int x = 0; x < 20; x++){
            for(int y = 0; y < 20; y++){
                if(r.nextInt(3) == 0) Map.map[x][y] = new TravelGFX();
                Map.map[x][y].x = x;
                Map.map[x][y].y = y;
            }
        }
    }
}