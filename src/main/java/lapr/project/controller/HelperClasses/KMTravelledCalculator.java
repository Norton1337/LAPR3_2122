package lapr.project.controller.HelperClasses;

/**
 * @author Paulo Norton
 */
public class KMTravelledCalculator {
    
    private double earthRadius = 6371;

    /**
     * Calculates the distance between two coordinates
     * @param latitude1 latitude of the first coordinate
     * @param longitude1 longitude of the first coordinate
     * @param latitude2 latitude of the second coordinate
     * @param longitude2 latitude of the second cooridante
     * @return returns the distance between coordinate nº1 and coordinate nº2
     */
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

    /**
     * Converts degrees to radians
     * @param degree a coordinate in degrees
     * @return return the coordiante in radians
     */
    public double convertToRadians(double degree){
        return degree/(180/Math.PI);
    }

}
