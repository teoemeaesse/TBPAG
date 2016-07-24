package data.drone_parts;

import data.DronePart;
import data.Material;

/**
 * Created by Tomás on 07/20/2016.
 */
public enum DroneParts {
    SMALL_DRILL(new DronePart("Small drill", new Material[]{new Material("Metal", 50), new Material("Silicon", 5), new Material("Gold", 0), new Material("Copper", 0), new Material("Plastic", 5)})),
    LARGE_DRILL(new DronePart("Large drill", new Material[]{new Material("Metal", 150), new Material("Silicon", 15), new Material("Gold", 0), new Material("Copper", 30), new Material("Plastic", 15)})),
    WATER_STORAGE(new DronePart("Water storage", new Material[]{new Material("Metal", 10), new Material("Silicon", 20), new Material("Gold", 0), new Material("Copper", 0), new Material("Plastic", 50)})),
    FOOD_STORAGE(new DronePart("Food storage", new Material[]{new Material("Metal", 10), new Material("Silicon", 20), new Material("Gold", 0), new Material("Copper", 0), new Material("Plastic", 50)})),
    FUEL_STORAGE(new DronePart("Fuel storage", new Material[]{new Material("Metal", 10), new Material("Silicon", 20), new Material("Gold", 0), new Material("Copper", 0), new Material("Plastic", 50)})),
    MEDICAL_STORAGE(new DronePart("Medical storage", new Material[]{new Material("Metal", 10), new Material("Silicon", 20), new Material("Gold", 0), new Material("Copper", 0), new Material("Plastic", 50)})),
    SCRAP_STORAGE(new DronePart("Scrap storage", new Material[]{new Material("Metal", 10), new Material("Silicon", 20), new Material("Gold", 0), new Material("Copper", 0), new Material("Plastic", 50)}));

    private DronePart dronePart;

    DroneParts(DronePart dronePart){
        this.dronePart = dronePart;
    }

    public DronePart getDronePart(){
        return dronePart;
    }
}
