package data.components.drone;

import data.Component;
import tools.Tools;
import world.map.entities.Ship;

import java.util.Scanner;

/**
 * Created by Tomás on 07/20/2016.
 */
public class DroneModifier extends Component {
    private Scanner scanner = new Scanner(System.in);

    public DroneModifier(){
        name = "Drone Modifier";
        description = "This turns inactive drones into active drones.\n";
        buildCost = 40;
        baseUpgradeCost = 25;
        timeTaken = 180;
        level = 1;
    }

    public void upgrade(int index){
        if(Ship.scrap >= baseUpgradeCost * (level + 0.5)){
            Ship.scrap -= baseUpgradeCost * (level + 0.5);
            Ship.components[index].level++;
            Tools.out("\nDrone Modifier component successfully upgraded, having spent " + baseUpgradeCost * (level + 0.5) + " scrap.\n\n");
        }else{
            Tools.out("\nNot enough scrap to upgrade this component. You need " + baseUpgradeCost * (level + 0.5) + " scrap.");
        }
    }

    @Override
    public void activate(){
        modificationMenu();
    }

    private void modificationMenu() throws NumberFormatException {
        boolean open = true;

        while(open){
            Tools.out("\n\nHello, sir/ma'am, and welcome to the beautiful menu developed by Gr8UI specifically for this machine. Just enter 'x' to exit this delightful UI.\n\nWhich drone do you want to modify? (ID)\n\n");

            String input = scanner.nextLine();

            if(input.equals("x")) open = false;

            int id = Integer.parseInt(input);

            if(id <= Ship.droneCapacity){
                Tools.out("\n\nDo you wish to...\n\n1. Install a part\n2. Remove a part\n\n");
                input = scanner.nextLine();

                switch(input){
                    case "1":
                        installPart(id);
                        break;
                    case "2":
                        removePart();
                        break;
                }
            }else{
                Tools.out("\nDrone does not exist. (press enter)\n\n");
                scanner.nextLine();
            }
        }
    }

    private void installPart(int drone) throws NumberFormatException {
        boolean open = true;

        while(open){
            Tools.out("\nWhich of the following parts do you want to install on this drone?\n\n");

            for(int i = 0; i < Ship.dronePartsStorage.size(); i++){
                Tools.out((i + 1) + ". " + Ship.dronePartsStorage.get(i).name + "\n");
            }

            Tools.out("x. Return\n\n");

            String input = scanner.nextLine();

            if(input.equals("x")) open = false;

            int id = Integer.parseInt(input);

            if(id <= Ship.dronePartsStorage.size()){
                Ship.drones[drone - 1].parts.add(Ship.dronePartsStorage.get(id - 1));
                Ship.dronePartsStorage.remove(id - 1);

                Tools.out("\n" + Ship.dronePartsStorage.get(id - 1).name + " added to drone. (press enter)\n\n");
                scanner.nextLine();
            }else{
                Tools.out("\nSelect a part you have. (press enter)\n\n");
                scanner.nextLine();
            }
        }
    }

    private void removePart(){

    }
}
