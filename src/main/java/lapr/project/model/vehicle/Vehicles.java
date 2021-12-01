package lapr.project.model.vehicle;

import lapr.project.model.vehicle.idb.IVehicle;

public class Vehicles {
    private String vehiclesId;
    private String type;

    public Vehicles(String type) {
        this.type = type;
    }

    public String getVehiclesId() {
        return vehiclesId;
    }

    public void setVehiclesId(String vehiclesId) {
        this.vehiclesId = vehiclesId;
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
                "vehiclesId='" + vehiclesId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
