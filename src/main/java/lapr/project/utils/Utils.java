package lapr.project.utils;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Utils {

    public static Integer ToInt(String string){
        if(string.contains("NA")){
            return 0;
        }
        return Integer.parseInt(string.trim());
    }

    public static Double ToDouble(String string){
        if(string.contains("NA")){
            return 0.0;
        }
        return Double.parseDouble(string.trim());
    }

    public static <T> void printList(List<T> list) {

        for(T elems : list){
            System.out.println(elems);
        }
    }

    public static Integer randomInt(){
        Random ran = new Random();
        return ran.nextInt(1000) + 5;
    }

    public static String coordinates (String latitude, String longitude){
        return new StringBuilder().append(latitude).append("/").append(longitude).toString();
    }

    public static String randomUUID(){
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        return uuidAsString;
    }
}
