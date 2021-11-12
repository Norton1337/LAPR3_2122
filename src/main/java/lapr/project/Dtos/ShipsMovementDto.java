package lapr.project.Dtos;

public class ShipsMovementDto  implements Comparable<ShipsMovementDto> {

    public String MMSI;
    public String travelledDistance;
    public String deltaDistance;
    public String numberOfMovements;

    public ShipsMovementDto(String MMSI, String travelledDistance, String deltaDistance, String numberOfMovements) {
        this.MMSI = MMSI;
        this.travelledDistance = travelledDistance;
        this.deltaDistance = deltaDistance;
        this.numberOfMovements = numberOfMovements;
    }

    public String getMMSI() {
        return MMSI;
    }

    public void setMMSI(String MMSI) {
        this.MMSI = MMSI;
    }

    public String getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(String travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public String getDeltaDistance() {
        return deltaDistance;
    }

    public void setDeltaDistance(String deltaDistance) {
        this.deltaDistance = deltaDistance;
    }

    public String getNumberOfMovements() {
        return numberOfMovements;
    }

    public void setNumberOfMovements(String numberOfMovements) {
        this.numberOfMovements = numberOfMovements;
    }

    @Override
    public String toString() {
        return "ShipsMovementDto{" +
                "MMSI='" + MMSI + '\'' +
                ", travelledDistance='" + travelledDistance + '\'' +
                ", deltaDistance='" + deltaDistance + '\'' +
                ", numberOfMovements='" + numberOfMovements + '\'' +
                '}';
    }

    @Override
    public int compareTo(ShipsMovementDto o) {
        return 0;
    }

}

