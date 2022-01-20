package lapr.project.utils;

import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.ship_position_data.ShipPositonData;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utils {

    private static Random ran = new Random();

    private Utils() {
    }

    public static Date toDate (String string){
        try{
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return date.parse(string);
        }
        catch(ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    public static Integer toInt(String string) {
        if (string.contains("NA")) {
            return 0;
        }
        return Integer.parseInt(string.trim());
    }

    public static Double toDouble(String string) {
        if (string.contains("NA")) {
            return 0.0;
        }
        return Double.parseDouble(string.trim());
    }

    public static <T> boolean printList(List<T> list) {

        for (T elems : list) {
            System.out.println(elems);
        }


        return true;
    }

    public static <K, V> boolean printMap(Map<K, V> map) {
        System.out.printf("[");
        map.forEach((key, value) -> System.out.println(key + ":" + value));
        System.out.println("]\n\n\n\n\n\n\n");

        return true;
    }


//    public static <K,V> void sortMap(Map<K,V> map){
//        try (Stream<Map.Entry<K, V>> sorted = map.entrySet().stream()
//                .sorted(Map.Entry.<K, V>comparingByValue())) {
//        }
//    }

    public static Integer randomInt() {
        return ran.nextInt(1000) + 5;
    }

    public static String coordinates(String latitude, String longitude) {
        return new StringBuilder().append(latitude).append("/").append(longitude).toString();
    }

    public static String randomUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static List<ShipPositonData> orderedByTime(List<ShipPositonData> list) {
        Collections.sort(list, Comparator.comparing(ShipPositonData::getBaseDateTime));

        return list;
    }

    public static List<CargoManifest> cargosOrderedByTime(List<CargoManifest> list) {
        Collections.sort(list, Comparator.comparing(CargoManifest::getDate));

        return list;
    }

    public static Date convertDate(String dateTime) {
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = null;
        try {
            date = format1.parse(dateTime);
        } catch (ParseException e) {
            return null;
        }

        return date;
    }

    public static String[] convertCoordinates(String coords) {
        return coords.split("[,/]");
    }


    public static String[] stripC(String coordinate) {
        return coordinate.split("/");
    }


    public static String readFromProp(String prop, String newReader) {

        try (FileReader reader = new FileReader(newReader)) {
            Properties p = new Properties();
            p.load(reader);
            return p.getProperty(prop);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <K> LinkedHashMap<K, Double> sortMapByValue(Map<K, Double> paramMap) {
        LinkedHashMap<K, Double> sortedMap = new LinkedHashMap<>();
        paramMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        return sortedMap;
    }

    public static <K,V> Map<K,V> reverseMap(Map<K,V> paramMap){
        Map<K,V> reverseMap = new LinkedHashMap<>();
        List<K> keys = new ArrayList<>(paramMap.keySet());
        for (int i = paramMap.size()-1; i>=0; i--){
            reverseMap.put(keys.get(i),paramMap.get(keys.get(i)));
        }
        return reverseMap;
    }

    public static <K, V> Map<K,V> getNFromMap(Map<K,V> paramMap, int n){

        Map<K,V> returnMap = new LinkedHashMap<>();
        int count = 0;

        for (K locals : paramMap.keySet()) {

            if (count < n) {
                returnMap.put(locals, paramMap.get(locals));
                count++;
            }
        }
        return returnMap;
    }

}
