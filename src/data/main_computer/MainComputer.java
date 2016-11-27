package data.main_computer;

import data.Component;
import data.Hardware;
import data.main_computer.hardware.CPU;
import data.main_computer.hardware.RAM;
import data.main_computer.hardware.SSD;
import game.GameConstants;
import tools.Tools;
import world.map.entities.Ship;

import java.util.Scanner;

/**
 * Created by Tomás on 07/10/2016.
 */
public class MainComputer extends Component {
    private static Scanner scanner = new Scanner(System.in);
    public static Hardware[] hardware = new Hardware[3];

    public MainComputer(){
        name = GameConstants.COMPONENT_MAINCOMPUTER;
        level = 1;

        hardware[0] = new CPU(0.2, 1);
        hardware[1] = new RAM(1);
        hardware[2] = new SSD(1);
    }

    @Override
    public void activate(){
        menu();
    }

    @Override
    public void scrap(){
        Tools.out("\nYou really shouldn't scrap this. It's just... no, just don't scrap it.\n\n");
    }

    public void upgrade(int index){
        Tools.out("\nYou can't upgrade this component\n\n");
    }

    private void menu(){
        boolean open = true;
        String[] names = {"CPU", "RAM", "SSD"};

        while(open){
            Tools.out("\nWhat part do you want to replace/add?\n\n1. CPU\n2. RAM\n3. SSD\nx. Exit\n\n");

            String input = scanner.nextLine();

            if(input.toUpperCase().equals("X")){
                open = false;
                Tools.out("\n\nCome back soon! (press enter)\n\n");
                scanner.nextLine();
            }else{
                try{
                    int installedComponent = Integer.parseInt(input) - 1;

                    Tools.out("\nCurrently installed component:");

                    if(hardware[installedComponent] == null) Tools.out("\n\nNo component installed in this slot...\n\n");
                    else hardware[installedComponent].describe();

                    Tools.out("\n\nAvailable component(s):\n\n");

                    int a = 0;
                    for(int i = 0; i < Ship.hardwareStorage.size(); i++){
                        if(Ship.hardwareStorage.get(i).name.equals(names[installedComponent])){
                            a++;
                            Tools.out(a + ". " + Ship.hardwareStorage.get(i).tag + "\n");

                            input = scanner.nextLine();

                            int cargoComponent = Integer.parseInt(input) - 1;

                            if(hardware[installedComponent] == null){
                                hardware[installedComponent] = Ship.hardwareStorage.get(cargoComponent);
                                Ship.hardwareStorage.remove(cargoComponent);
                            }
                            else{
                                Ship.hardwareStorage.add(hardware[installedComponent]);
                                hardware[installedComponent] = Ship.hardwareStorage.get(cargoComponent);
                                Ship.hardwareStorage.remove(cargoComponent);
                            }

                            Tools.out("\n\nReplacement successful. (press enter)\n\n");
                            scanner.nextLine();
                            open = false;
                            break;
                        }
                    }
                }catch(Exception e){Tools.out("\nFatal error. Returning...\n");}
            }
        }
    }
}
