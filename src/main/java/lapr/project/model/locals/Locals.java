package lapr.project.model.locals;

public class Locals implements Comparable<Locals> {
    private String id;
    private String countryId;
    private int portId;
    private String name;
    private String coordinates;
    private String vehicleId;
    private String type;

    // TODO ACRESCENTAR
    private int localCapacity;



    public Locals(String country, int code,
                              String portName, String coordinates) {
        this.countryId = country;
        this.portId = code;
        this.name = portName;
        this.coordinates = coordinates;
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

    public int getPortId() {
        return portId;
    }

    public void setPortId(int portId) {
        this.portId = portId;
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
                //"id='" + id + '\'' +
                //", countryId='" + countryId + '\'' +
                " name='" + name + '\'' +
                ", coordinates='" + coordinates + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", portId='" + portId + '\'' +
                ", localCapacity=" + localCapacity +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public int compareTo(Locals o) {
        return (this.getId().compareTo(o.getId()));
    }
}
