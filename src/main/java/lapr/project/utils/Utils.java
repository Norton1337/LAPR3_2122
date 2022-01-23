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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try{
            date = format.parse(string);
        }
        catch(ParseException e){
            return null;
        }
        return date;
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
        if(map.size() == 0){
            return false;
        }

        System.out.print("[");
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
        list.sort(Comparator.comparing(ShipPositonData::getBaseDateTime));
        return list;
    }

    public static List<CargoManifest> cargosOrderedByTime(List<CargoManifest> list) {
        list.sort(Comparator.comparing(CargoManifest::getDate));
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
            // e.printStackTrace();
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

    public static <K> List<K> iterateToList(Iterable<K> iterable){
        List<K> result = new ArrayList<>();
        iterable.forEach(result::add);

        return result;
    }

    public static <K> List<K> mergeLists(List<K> list1, List<K> list2 ){
        List<K> result = new ArrayList<>();

        result.addAll(list1);
        result.addAll(list2);

        return result;
    }


    public static <E> List<List<E>> generatePermutation(List<E> original) {
        if (original.isEmpty()) {
            List<List<E>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        E firstElement = original.remove(0);
        List<List<E>> returnValue = new ArrayList<>();
        List<List<E>> permutations = generatePermutation(original);
        for (List<E> smallerPermutated : permutations) {
            for (int index = 0; index <= smallerPermutated.size(); index++) {
                List<E> temp = new ArrayList<>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }

    public static <E> List<E> addElementsToList(List<E> elementsList){
        List<E> returnList = new LinkedList<>();

        for (E elem : elementsList){
            returnList.add(elem);
        }
        return returnList;
        // returnList.addAll(elementsList);
    }

    public static <K> List<K> setToList(Set<K> set){
        return new ArrayList<>(set);
    }


}
