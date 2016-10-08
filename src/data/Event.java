package data;

/**
 * Created by Tomás on 08/10/2016.
 */
public class Event {
    public void event(){}


    public static void fireEvents(Event ... e1){
        for(Event e2 : e1) e2.event();
    }
}
