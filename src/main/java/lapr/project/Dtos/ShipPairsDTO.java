package lapr.project.dtos;

public class ShipPairsDTO {

    private String ship1MMSI;

    private String ship2MMSI;

    private String ship1Mov;

    private String ship2Mov;

    private String ship1Traveldistance;

    private String ship2Trabeldistance;


    public ShipPairsDTO(String ship1MMSI, String ship2MMSI, String ship1Mov,
                        String ship2Mov, String ship1Traveldistance, String ship2Trabeldistance) {
        this.ship1MMSI = ship1MMSI;
        this.ship2MMSI = ship2MMSI;
        this.ship1Mov = ship1Mov;
        this.ship2Mov = ship2Mov;
        this.ship1Traveldistance = ship1Traveldistance;
        this.ship2Trabeldistance = ship2Trabeldistance;
    }

    public String getShip1MMSI() {
        return ship1MMSI;
    }

    public void setShip1MMSI(String ship1MMSI) {
        this.ship1MMSI = ship1MMSI;
    }

    public String getShip2MMSI() {
        return ship2MMSI;
    }

    public void setShip2MMSI(String ship2MMSI) {
        this.ship2MMSI = ship2MMSI;
    }

    public String getShip1Mov() {
        return ship1Mov;
    }

    public void setShip1Mov(String ship1Mov) {
        this.ship1Mov = ship1Mov;
    }

    public String getShip2Mov() {
        return ship2Mov;
    }

    public void setShip2Mov(String ship2Mov) {
        this.ship2Mov = ship2Mov;
    }

    public String getShip1Traveldistance() {
        return ship1Traveldistance;
    }

    public void setShip1Traveldistance(String ship1Traveldistance) {
        this.ship1Traveldistance = ship1Traveldistance;
    }

    public String getShip2Trabeldistance() {
        return ship2Trabeldistance;
    }

    public void setShip2Trabeldistance(String ship2Trabeldistance) {
        this.ship2Trabeldistance = ship2Trabeldistance;
    }

    @Override
    public String toString() {
        return String.format("%s    %s      %s      %.3f      %s      %.3f", ship1MMSI, ship2MMSI, ship1Mov,
                Float.parseFloat(ship1Traveldistance), ship2Mov, Float.parseFloat(ship2Trabeldistance)).replace(",",".");
    }
}


