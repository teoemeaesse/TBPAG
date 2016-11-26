package data;

/**
 * Created by Tomás on 08/10/2016.
 */
public class Event {
    public void event(){}
    public boolean eventB(){return false;}


    public static void fireEvents(Event ... e1){
        for(Event e2 : e1) e2.event();
    }

    public static boolean fireEventB(Event e1){
        return e1.eventB();
    }
}
