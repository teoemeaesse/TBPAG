package tools;

/**
 * Created by Tomás on 29/03/2016.
 */
public class Tools {
    public static void out(String arg){
        System.out.print(arg);
    }
    public static Object[] increaseArraySize(Object[] array){
        Object[] temp = new Object[array.length + 1];
        for(int i = 0; i < array.length; i++){
            temp[i] = array[i];
        }
        return temp;
    }
    public static boolean[] increaseArraySize(boolean[] array){
        boolean[] temp = new boolean[array.length + 1];
        for(int i = 0; i < array.length; i++){
            temp[i] = array[i];
        }
        return temp;
    }
}
