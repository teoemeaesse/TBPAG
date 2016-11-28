package data.commerce;

import game.Settings;
import world.map.entities.Ship;

/**
 * Created by Tomás on 24/11/2016.
 */
public class Commerce {
    public static boolean purchase(int amount, int amountTP, int id, double tax) {
        boolean canPurchase = true;
        if(Ship.money >= Math.floor((amount * Settings.BASE_VALUE_RESOURCES[id]) * tax) && Ship.resourceCapacities[id] - Ship.resources[id] >= amount && amountTP >= amount){
            Ship.money -= Math.floor((amount * Settings.BASE_VALUE_RESOURCES[id]) * tax);
            Ship.resources[id] += amount;
        }else canPurchase = false;
        return canPurchase;
    }
    public static boolean sell(int amount, int id, double tax){
        boolean canSell = true;
        if(Ship.resources[id] >= amount){
            Ship.money += Math.floor((amount * Settings.BASE_VALUE_RESOURCES[id]) * tax);
            Ship.resources[id] -= amount;
        }else canSell = false;
        return canSell;
    }
}
