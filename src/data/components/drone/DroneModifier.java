package data.components.drone;

import data.Component;
import game.GameConstants;
import tools.Tools;
import world.map.entities.Ship;

import java.util.Scanner;

/**
 * Created by Tomás on 07/20/2016.
 */
public class DroneModifier extends Component {
    private Scanner scanner = new Scanner(System.in);

    public DroneModifier(){
        name = GameConstants.COMPONENT_DRONEMODIFIER;
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

    private void modificationMenu() {
        boolean open = true;

        while(open){
            Tools.out("\n\nHello, sir/ma'am, and welcome to the beautiful menu developed by Gr8UI specifically for this machine. Just enter 'x' to exit this delightful UI.\n\nWhich drone do you want to modify? (ID)\n\n");

            String input = scanner.nextLine();

            if(input.toUpperCase().equals("X")){
                Tools.out("\n\nThank you for using our service. Come back soon for more beautiful UIs. (press enter)");
                scanner.nextLine();
                open = false;
            }
            else if(!input.equals("")){
                int id = Integer.parseInt(input);

                if(id <= Ship.droneCapacity && id >= 0){
                    Tools.out("\n\nDo you wish to...\n\n1. Install a part\n2. Remove a part\n\n");
                    input = scanner.nextLine();

                    switch(input){
                        case "1":
                            installPart(id);
                            break;
                        case "2":
                            removePart(id);
                            break;
                    }
                }else{
                    Tools.out("\nDrone does not exist. (press enter)\n\n");
                    scanner.nextLine();
                }
            }
        }
    }

    private void installPart(int drone) {
        boolean open = true;

        while(open){
            Tools.out("\nWhich of the following parts do you want to install on this drone?\n\n");

            for(int i = 0; i < Ship.dronePartsStorage.size(); i++){
                Tools.out((i + 1) + ". " + Ship.dronePartsStorage.get(i).name + "\n");
            }

            Tools.out("x. Return\n\n");

            String input = scanner.nextLine();

            if(input.toUpperCase().equals("X")) open = false;
            else{
                int id = Integer.parseInt(input) - 1;

                if(id <= Ship.dronePartsStorage.size()){
                    Ship.drones.get(drone - 1).parts.add(Ship.dronePartsStorage.get(id));

                    Tools.out("\n" + Ship.dronePartsStorage.get(id).name + " added to drone. (press enter)\n\n");

                    Ship.dronePartsStorage.remove(id);
                    scanner.nextLine();
                }else{
                    Tools.out("\nSelect a part you have. (press enter)\n\n");
                    scanner.nextLine();
                }
            }
        }
    }

    private void removePart(int drone){
        boolean open = true;

        while(open){
            Tools.out("\nWhich of the following parts do you want to remove from this drone?\n\n");

            for(int i = 0; i < Ship.drones.get(drone - 1).parts.size(); i++){
                Tools.out((i + 1) + ". " + Ship.drones.get(drone - 1).parts.get(i).name + "\n");
            }

            Tools.out("x. Return\n\n");

            String input = scanner.nextLine();

            if(input.toUpperCase().equals("X")) open = false;
            else{
                int id = Integer.parseInt(input) - 1;

                if(id <= Ship.dronePartsStorage.size()){
                    Ship.dronePartsStorage.add(Ship.drones.get(drone - 1).parts.get(id));

                    Tools.out("\n" + Ship.drones.get(drone - 1).parts.get(id).name + " removed from drone. (press enter)\n\n");

                    Ship.drones.get(drone - 1).parts.remove(id);
                    scanner.nextLine();
                }else{
                    Tools.out("\nSelect a part you have. (press enter)\n\n");
                    scanner.nextLine();
                }
            }
        }
    }
}
