package lapr.project.model;

public class Vehicle {

    private String vehicleID;
    private String type;

    Vehicle(String type){
        this.type = type;
    }
    
    public String getVehicleID() {
        return this.vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
    


}
