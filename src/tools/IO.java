package tools;

/**
 * Created by Tomás on 01/04/2016.
 */
public class IO {
    //private static URL location = IO.class.getProtectionDomain().getCodeSource().getLocation();

    /*public static void loadComponents(){
        try{
            InputStream is = IO.class.getResourceAsStream("components.list");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String file = br.readLine();

            Component.components = new Component[Integer.parseInt(file)];
            for(int i = 0; i < Component.components.length; i++){
                Component.components[i] = new Component();
            }

            String componentName = "";
            int componentIndex = 0;
            String componentThingyTag = "";
            String componentThingyDescription = "";


            while((file = br.readLine()) != null){
                boolean readName = false;
                boolean readThingyTag = false;
                boolean readThingyDescription = false;

                for(int i = 0; i < file.length() - 1; i++){
                    //COMPONENT NAME
                    if(file.substring(i, i + 1).equals("<")) readName = true;
                    else if(file.substring(i + 1, i + 2).equals(">")){
                        readName = false;
                        Component.components[componentIndex].name = componentName;
                        componentName = "";
                    }
                    if(readName) componentName += file.substring(i + 1, i + 2);
                    //COMPONENT NAME

                    //COMPONENT... THINGIES
                    if(file.substring(i, i + 1).equals("[")) readThingyTag = true;
                    else if(file.substring(i + 1, i + 2).equals("]")){
                        readThingyTag = false;
                    }
                    if(readThingyTag) componentThingyTag += file.substring(i + 1, i + 2);

                    if(file.substring(i, i + 1).equals("{")) readThingyDescription = true;
                    else if(file.substring(i + 1, i + 2).equals("}")){
                        readThingyDescription = false;

                        switch(componentThingyTag){
                            case "DESCRIPTION":
                                Component.components[componentIndex].description = componentThingyDescription;
                                break;
                            case "PRODUCE_TYPE":
                                Component.components[componentIndex].produceType = componentThingyDescription;
                                break;
                            case "BUILD_COST":
                                Component.components[componentIndex].buildCost = Double.parseDouble(componentThingyDescription);
                                break;
                            case "BASE_UPGRADE_COST":
                                Component.components[componentIndex].baseUpgradeCost = Double.parseDouble(componentThingyDescription);
                                break;
                            case "CONSUMPTION":
                                Component.components[componentIndex].consumption = Double.parseDouble(componentThingyDescription);
                                break;
                            case "PRODUCE":
                                Component.components[componentIndex].produce = Double.parseDouble(componentThingyDescription);
                                break;
                            case "TIME_TAKEN":
                                Component.components[componentIndex].timeTaken = Double.parseDouble(componentThingyDescription);
                                break;
                            case "LEVEL":
                                Component.components[componentIndex].level = Double.parseDouble(componentThingyDescription);
                                break;
                        }

                        componentThingyTag = "";
                        componentThingyDescription = "";
                    }
                    if(readThingyDescription) componentThingyDescription += file.substring(i + 1, i + 2);
                    //COMPONENT... THINGIES

                    //FINISH COMPONENT
                    if(file.substring(i + 1, i + 2).equals("#")){
                        componentIndex++;
                    }
                    //FINISH COMPONENT
                }
            }
        }catch(IOException e){}
    }*/
}