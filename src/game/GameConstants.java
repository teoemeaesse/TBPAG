package game;

/**
 * Created by Tomás on 05-Sep-16.
 */
public class GameConstants {
    public static final String MAP_LARGE_ASTEROID = "o", MAP_ASTEROID = ".", MAP_AMTP = "$";
    public static final String NAME_LARGE_ASTEROID = " large asteroid", NAME_ASTEROID = " asteroid", NAME_ATP = " automated trading post", NAME_MTP = " manned trading post";
    public static final int BASE_VALUE_CPU = 12, BASE_VALUE_RAM = 9, BASE_VALUE_SSD = 1;
    public static final int MISSED_DOCK_DAMAGE_MIN = 5, MISSED_DOCK_DAMAGE_MAX = 20;
    public static final String
            COMMAND_SHWS = "SHOW S",
            COMMAND_SHWD = "SHOW D",
            COMMAND_SHWM = "SHOW M",
            COMMAND_NAVS = "NAV S",
            COMMAND_NAVD = "NAV D",
            COMMAND_MINES = "MINE S",
            COMMAND_MINED = "MINE D",
            COMMAND_COLD = "COL D",
            COMMAND_AC = "ACT C",
            COMMAND_DC = "DACT C",
            COMMAND_UC = "UP C",
            COMMAND_BC = "BUILD C",
            COMMAND_SC = "SCRAP C",
            COMMAND_EXAMH = "EXAM H",
            COMMAND_INTR = "INTR",
            COMMAND_HELPCOMMANDS = "HELP CMD",
            COMMAND_HELPCOMPONENTS = "HELP CMP";
    public static final String[] commands = {
            COMMAND_SHWS,
            COMMAND_SHWD,
            COMMAND_SHWM,
            COMMAND_NAVS,
            COMMAND_NAVD,
            COMMAND_MINES,
            COMMAND_MINED,
            COMMAND_COLD,
            COMMAND_AC,
            COMMAND_DC,
            COMMAND_UC,
            COMMAND_BC,
            COMMAND_SC,
            COMMAND_EXAMH,
            COMMAND_INTR,
            COMMAND_HELPCOMMANDS,
            COMMAND_HELPCOMPONENTS
    };
    public static final String[] commandDescriptions = {
            "Displays all ship information.",
            "Displays all drone information. Usage: 'show d [drone ID]'.",
            "Display current sector.",
            "Moves ship to selected coordinates. Usage: 'nav s [x coordinates(00, 05, 13...)] [y coordinates(00, 05, 12)]'.",
            "Moves drone to selected coordinates. Usage: 'nav d [drone ID] [x coordinates(00, 05, 13...)] [y coordinates(00, 05, 13...)]'.",
            "Orders the ship to mine the asteroid at its coordinates.",
            "Orders the selected drone to mine the asteroid at its coordinates. Usage: 'mine d [drone ID]'.",
            "Sends the selected drone back to ship to deposit collected resources. Usage: 'col d [drone ID]'.",
            "Activates selected component. Usage: 'act c [component ID]'.",
            "Deactivates selected component. Usage: 'dact c [component ID]'.",
            "Upgrades selected component. Usage: 'up c [component ID]'.",
            "Builds designated component. Usage: 'build c [component name]'.",
            "Scrap selected component. Usage: 'scrap c [component ID]'.",
            "Examines the ship's hardware storage.",
            "Interact with a map object.",
            "Commands help.",
            "Components help."
    };

    public static final String
            COMPONENT_FOODSTORAGE = "Food Storage",
            COMPONENT_FUELSTORAGE = "Fuel Storage",
            COMPONENT_MEDICALSTORAGE = "Medical Storage",
            COMPONENT_SCRAPSTORAGE = "Scrap Storage",
            COMPONENT_WATERSTORAGE = "Water Storage",
            COMPONENT_DRONEMODIFIER = "Drone Modifier",
            COMPONENT_HYDROPONICS = "Hydroponics",
            COMPONENT_SCRAPPROCESSOR = "Scrap Processor",
            COMPONENT_DRONEHANGAR = "Drone Hangar",
            COMPONENT_JUMPDRIVE = "Jump Drive",
            COMPONENT_MAINCOMPUTER = "Main Computer";
    public static final String[] components = {
            COMPONENT_FOODSTORAGE,
            COMPONENT_FUELSTORAGE,
            COMPONENT_MEDICALSTORAGE,
            COMPONENT_SCRAPSTORAGE,
            COMPONENT_WATERSTORAGE,
            COMPONENT_DRONEMODIFIER,
            COMPONENT_HYDROPONICS,
            COMPONENT_SCRAPPROCESSOR,
            COMPONENT_DRONEHANGAR,
            COMPONENT_JUMPDRIVE,
            COMPONENT_MAINCOMPUTER
    };
    public static final String[] componentDescriptions = {
            "This stores food.",
            "This stores fuel.",
            "This stores medical supplies.",
            "This stores scrap.",
            "This stores water.",
            "This upgrades drones.",
            "This transforms water into food via plant growing.",
            "This processes scrap into materials that can be used to build various hardware.",
            "This holds all your drones. Upgrading will increase drone capacity.",
            "Used for jumping between sectors.",
            "Used for... eh, just don't deconstruct it ok?"
    };
}
