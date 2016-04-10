package tools;

import java.io.*;
import java.net.URL;

/**
 * Created by Tomás on 01/04/2016.
 */
public class IO {
    private static URL location = IO.class.getProtectionDomain().getCodeSource().getLocation();
    public static void save(Object obj, String file){
        try{
            FileOutputStream fileOut = new FileOutputStream(location + "tmp/" + file + ".sav");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
        }catch(IOException i){i.printStackTrace();}
    }

    public static Object load(String file){
        Object obj = new Object();
        try{
            FileInputStream fileIn = new FileInputStream(location + "tmp/" + file +".sav");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            obj = (Object) in.readObject();
            in.close();
            fileIn.close();
        }
        catch(IOException e){e.printStackTrace();}
        catch(ClassNotFoundException e){e.printStackTrace();}

        return obj;
    }
}
