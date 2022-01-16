package lapr.project.model.vehicle;

public class Vehicles {
    private String id;
    private String vehicle_recon;
    private String type;

    public Vehicles(String type, String vehicle_recon) {
        this.type = type;
        this.vehicle_recon = vehicle_recon;
    }

    public String getVehicle_recon() {
        return vehicle_recon;
    }

    public void setVehicle_recon(String vehicle_recon) {
        this.vehicle_recon = vehicle_recon;
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
