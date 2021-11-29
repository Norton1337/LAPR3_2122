package lapr.project.utils;

import lapr.project.model.ShipPositionData.ShipPositonData;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utils {

    private Utils(){}

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

    public static <T> boolean printList(List<T> list) {

        for(T elems : list){
            System.out.println(elems);
        }

        return true;
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

    public static List<ShipPositonData> orderedByTime(List<ShipPositonData> list){
        Collections.sort(list, Comparator.comparing(ShipPositonData::getBaseDateTime));

        return list;
    }

    public static Date convertDate(String dateTime){
        SimpleDateFormat format1 =new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = null;
        try {
            date = format1.parse(dateTime);
        } catch (ParseException e) {
            return null;
        }

        return date;
    }

    public static String[] convertCoordinates(String coords){
        return coords.split("[,/]");
    }


    public static String[] stripC(String coordinate){
        return coordinate.split("/");
    }


    public static String readFromProp(String prop, String newReader){
        FileReader reader= null;
        try {
            reader = new FileReader(newReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Properties p=new Properties();
        try {
            p.load(reader);
            return p.getProperty(prop);
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return null;
    }


}
