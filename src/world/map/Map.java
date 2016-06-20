package world.map;

import tools.Tools;
import world.map.entities.Ship;

/**
 * Created by Tomás on 30/03/2016.
 */
public class Map {
    public static Tile[][] map = new Tile[20][20];

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
                        for(int i = 0; i < Ship.drones.length; i++){
                            if(x - 2 == Ship.drones[i].x && y == Ship.drones[i].y) icon = "D";
                        }
                    }
                    Tools.out(icon);
                }
            }
            Tools.out("\n");
        }
        Tools.out("\n\n");
    }
}
