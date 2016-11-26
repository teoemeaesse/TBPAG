package data.events.void_events;

import data.Event;
import tools.Tools;
import world.map.Map;
import world.map.entities.Ship;

import java.util.Scanner;

/**
 * Created by Tomás on 11/10/2016.
 */
public class DemandPayEvent extends Event {
    private int pay, x = -1, y;
    private String query, yes, no, notEnough;

    public DemandPayEvent(int pay, String query, String yes, String no, String notEnough){
        this.query = query;
        this.pay = pay;
        this.yes = yes;
        this.no = no;
        this.notEnough = notEnough;
    }

    public DemandPayEvent(int pay, String query, String yes, String no, String notEnough, int x, int y){
        this.query = query;
        this.pay = pay;
        this.yes = yes;
        this.no = no;
        this.notEnough = notEnough;
        this.x = x;
        this.y = y;
    }

    @Override
    public void event(){
        Scanner scanner = new Scanner(System.in);

        Tools.out(query);

        String input = scanner.nextLine();

        if(input.toUpperCase().equals("Y")){
            if(Ship.money >= pay){
                if(x != -1) Ship.money -= pay;
                Tools.out(yes);
            }else{
                if(x != -1) Map.map[x][y].allowedIn = false;
                Tools.out(notEnough);
            }
        }else{
            if(x != -1) Map.map[x][y].allowedIn = false;
            Tools.out(no);
        }
    }
}
