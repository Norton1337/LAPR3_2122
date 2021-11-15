package lapr.project.model.ShipPositionData;


public class ShipPositonData{

    private String id;
    private String baseDateTime;
    private String coordinates;
    private double sog;
    private double cog;
    private int heading;
    private String positon;
    private String transcieverClass;
    private String shipId;



    public ShipPositonData(String baseDateTime, String coordinates, double sog, double cog,
                           int heading, String positon, String transcieverClass) {
        this.baseDateTime = baseDateTime;
        this.coordinates = coordinates;
        this.sog = sog;
        this.cog = cog;
        this.heading = heading;
        this.positon = positon;
        this.transcieverClass = transcieverClass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBaseDateTime() {
        return baseDateTime;
    }

    public void setBaseDateTime(String baseDateTime) {
        this.baseDateTime = baseDateTime;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public double getSog() {
        return sog;
    }

    public void setSog(double sog) {
        this.sog = sog;
    }

    public double getCog() {
        return cog;
    }

    public void setCog(double cog) {
        this.cog = cog;
    }

    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public String getPositon() {
        return positon;
    }

    public void setPositon(String positon) {
        this.positon = positon;
    }

    public String getTranscieverClass() {
        return transcieverClass;
    }

    public void setTranscieverClass(String transcieverClass) {
        this.transcieverClass = transcieverClass;
    }

    public String getShipId() {
        return shipId;
    }

    public void setShipId(String shipId) {
        this.shipId = shipId;
    }

    @Override
    public String toString() {
        return "ShipPositonData{" +
                "id=" + id +
                ", baseDateTime='" + baseDateTime + '\'' +
                ", coordinates='" + coordinates + '\'' +
                ", sog=" + sog +
                ", cog=" + cog +
                ", heading=" + heading +
                ", positon='" + positon + '\'' +
                ", transcieverClass='" + transcieverClass + '\'' +
                ", shipId=" + shipId +
                '}';
    }

}
