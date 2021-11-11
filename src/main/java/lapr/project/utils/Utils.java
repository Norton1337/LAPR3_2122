package lapr.project.utils;

import lapr.project.model.ShipPositionData.ShipPositonData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public static List<ShipPositonData> orderedByTime(List<ShipPositonData> list){
        Collections.sort(list, new Comparator<ShipPositonData>() {
            @Override
            public int compare(ShipPositonData date1, ShipPositonData date2) {
                return date1.getBaseDateTime().compareTo(date2.getBaseDateTime());
            }
        });

        return list;
    }

    public static Date convertDate(String dateTime) throws ParseException{
        SimpleDateFormat format1 =new SimpleDateFormat("dd/MM/yyyy HH:mm");  
        Date date = format1.parse(dateTime); 

        return date;
    }

    public static String[] convertCoordinates(String coords){

        String[] splitCoords = coords.split(",");
        String c1 = splitCoords[0]; //lat
        String c2 = splitCoords[1]; //long
        return splitCoords;
    }


}
