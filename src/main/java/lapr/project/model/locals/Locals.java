package lapr.project.model.locals;

public class Locals implements Comparable<Locals> {
    private String id;
    private String countryId;
    private String portId;
    private int localCode;
    private String name;
    private String coordinates;
    private String vehicleId;
    private String type;

    // TODO ACRESCENTAR
    private int localCapacity;


    public Locals(String country, int code,
                  String portName, String coordinates) {
        this.countryId = country;
        this.localCode = code;
        this.name = portName;
        this.coordinates = coordinates;
    }

    public String getPortId() {
        return portId;
    }

    public void setPortId(String portId) {
        this.portId = portId;
    }

    public int getLocalCapacity() {
        return localCapacity;
    }

    public void setLocalCapacity(int localCapacity) {
        this.localCapacity = localCapacity;
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

    public int getLocalCode() {
        return localCode;
    }

    public void setLocalCode(int localCode) {
        this.localCode = localCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String portName) {
        this.name = portName;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Locals{" +
                "portId='" + portId + '\'' +
                ", localCode=" + localCode +
                ", name='" + name + '\'' +
                ", coordinates='" + coordinates + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", type='" + type + '\'' +
                ", localCapacity=" + localCapacity +
                '}';
    }

    @Override
    public int compareTo(Locals o) {
        return (this.getId().compareTo(o.getId()));
    }
}
