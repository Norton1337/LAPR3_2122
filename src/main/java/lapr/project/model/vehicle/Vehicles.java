package lapr.project.model.vehicle;

public class Vehicles {
    private String id;
    private String type;

    public Vehicles(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Vehicles{" +
                "vehiclesId='" + id + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
