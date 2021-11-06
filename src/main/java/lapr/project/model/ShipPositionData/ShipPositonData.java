package lapr.project.model.ShipPositionData;


public class ShipPositonData {

    private int id;
    private String baseDateTime;
    private String coordinates;
    private float sog;
    private float cog;
    private int heading;
    private String positon;
    private String transcieverClass;
    private int shipId;


    public ShipPositonData(int id, String baseDateTime, String coordinates, float sog, float cog,
                           int heading, String positon, String transcieverClass, int shipId) {
        this.id = id;
        this.baseDateTime = baseDateTime;
        this.coordinates = coordinates;
        this.sog = sog;
        this.cog = cog;
        this.heading = heading;
        this.positon = positon;
        this.transcieverClass = transcieverClass;
        this.shipId = shipId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public float getSog() {
        return sog;
    }

    public void setSog(float sog) {
        this.sog = sog;
    }

    public float getCog() {
        return cog;
    }

    public void setCog(float cog) {
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

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }
}
