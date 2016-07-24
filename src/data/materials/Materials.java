package data.materials;

import data.Material;

/**
 * Created by Tomás on 07/20/2016.
 */
public enum Materials {
    FE(new Material("Metal", 0)),
    SL(new Material("Silicon", 0)),
    AU(new Material("Gold", 0)),
    CU(new Material("Copper", 0)),
    PL(new Material("Plastic", 0));

    private Material material;

    Materials(Material material){
        this.material = material;
    }

    public Material getMaterial(){
        return material;
    }
}
