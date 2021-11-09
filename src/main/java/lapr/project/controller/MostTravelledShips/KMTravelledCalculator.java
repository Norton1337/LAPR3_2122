package lapr.project.controller.MostTravelledShips;

public class KMTravelledCalculator {
    
    private double earthRadius = 6371;


    public double calculate(String latitude1, String longitude1, String latitude2, String longitude2){
        double lat1 = convertToRadians(Double.parseDouble(latitude1));
        double lat2 = convertToRadians(Double.parseDouble(latitude2));
        double long1 = convertToRadians(Double.parseDouble(longitude1));
        double long2 = convertToRadians(Double.parseDouble(longitude2));
        double dLong = long2 - long1;
        

        return earthRadius * (Math.acos(
            (Math.sin(lat1) * Math.sin(lat2)) +
            (Math.cos(lat1) * Math.cos(lat2)) * Math.cos(dLong)
            ));
    }

    public double convertToRadians(double degree){
        return degree/(180/Math.PI);
    }

}
