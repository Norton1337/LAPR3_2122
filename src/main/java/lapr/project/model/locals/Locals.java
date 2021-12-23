package lapr.project.model.locals;

public class Locals implements Comparable<Locals> {
    private String id;

    private String countryId;

    private int code;
    private String name;
    private String coordinates;
    private String shipId;


    // TODO ACRESCENTAR
    private String portId;
    private int localCapacity;
    private String type;


    public Locals(String country, int code,
                              String portName, String coordinates) {
        this.countryId = country;
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

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
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
                ", country='" + countryId + '\'' +
                ", code=" + code +
                ", name ='" + name + '\'' +
                ", coordinates='" + coordinates + '\'' +
                ", shipId='" + shipId + '\'' +
                '}';
    }



    @Override
    public int compareTo(Locals o) {
        return 0;
    }
}
