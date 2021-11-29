package lapr.project.model;

import lapr.project.model.locals.Locals;

public class CargoManifest {
    
    private String cargoManifestID;
    private Locals currentLocal;
    private Vehicle vehicle;
    private Locals nextLocal;
    private String date;
    private String operationType;


    CargoManifest(Locals currentLocal, Vehicle vehicle, Locals nextLocal, String date, String operationType){
        this.currentLocal = currentLocal;
        this.vehicle = vehicle;
        this.nextLocal = nextLocal;
        this.date = date;
        this.operationType = operationType;

    }
    

    public String getCargoManifestID() {
        return this.cargoManifestID;
    }

    public void setCargoManifestID(String cargoManifestID) {
        this.cargoManifestID = cargoManifestID;
    }

    public Locals getCurrentLocal() {
        return this.currentLocal;
    }

    public void setCurrentLocal(Locals currentLocal) {
        this.currentLocal = currentLocal;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Locals getNextLocal() {
        return this.nextLocal;
    }

    public void setNextLocal(Locals nextLocal) {
        this.nextLocal = nextLocal;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOperationType() {
        return this.operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String toString(){
        return "CargoManifest{cargoManifestID:"+getCargoManifestID()+"\n"
                +"currentLocal:"+getCurrentLocal()+"\n"
                +"vehicle:"+getVehicle()+"\n"
                +"nextLocal:"+getNextLocal()+"\n"
                +"date:"+getDate()+"\n"
                +"operationType:"+getOperationType()+"}";

    }
}
