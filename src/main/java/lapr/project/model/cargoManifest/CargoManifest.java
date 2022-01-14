package lapr.project.model.cargoManifest;

public class CargoManifest {

    private String id;
    private String nextLocal;
    private String date;
    private String operationType;
    private String currentLocalId;
    private String vehicleId;
    private String cargo_recon;



    public CargoManifest(String nextLocal, String date, String operationType, String cargo_recon) {
        this.nextLocal = nextLocal;
        this.date = date;
        this.operationType = operationType;
        this.cargo_recon = cargo_recon;
    }

    public CargoManifest(String nextLocal, String date, String operationType) {
        this.nextLocal = nextLocal;
        this.date = date;
        this.operationType = operationType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNextLocal() {
        return nextLocal;
    }

    public void setNextLocal(String nextLocal) {
        this.nextLocal = nextLocal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getCurrentLocalId() {
        return currentLocalId;
    }

    public void setCurrentLocalId(String currentLocalId) {
        this.currentLocalId = currentLocalId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getCargo_recon() {
        return cargo_recon;
    }

    public void setCargo_recon(String cargo_recon) {
        this.cargo_recon = cargo_recon;
    }
}

