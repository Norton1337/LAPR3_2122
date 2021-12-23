package lapr.project.model.locals;

public class Locals implements Comparable<Locals> {
    private String id;
    private String continent;

    private String country;

    private int code;
    private String name;
    private String coordinates;
    private String shipId;


    // TODO ACRESCENTAR
    private String portId;
    private int localCapacity;
    private String type;
    private String countryId;


    public Locals(String continent, String country, int code,
                              String portName, String coordinates) {
        this.continent = continent;
        this.country = country;
        this.code = code;
        this.name = portName;
        this.coordinates = coordinates;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getPortName() {
        return name;
    }

    public void setPortName(String portName) {
        this.name = portName;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getShipId() {
        return shipId;
    }

    public void setShipId(String shipId) {
        this.shipId = shipId;
    }


    @Override
    public String toString() {
        return "PortsAndWarehouses{" +
                "id='" + id + '\'' +
                //", continent='" + continent + '\'' +
               // ", country='" + country + '\'' +
                ", code=" + code +
                //", portName='" + portName + '\'' +
                //", coordinates='" + coordinates + '\'' +
                //", shipId='" + shipId + '\'' +
                '}';
    }



    @Override
    public int compareTo(Locals o) {
        return 0;
    }
}
