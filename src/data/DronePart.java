package data;

/**
 * Created by Tomás on 07/20/2016.
 */
public class DronePart {
    public String name;
    public Material materialsToBuild[] = new Material[5]; //fe, sl, au, cu, pl

    public DronePart(String name, Material[] materialsToBuild){
        this.name = name;
        this.materialsToBuild = materialsToBuild;
    }
}
