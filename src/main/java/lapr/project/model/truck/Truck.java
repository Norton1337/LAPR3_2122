package lapr.project.model.truck;

public class Truck {

    private String truckId;
    private String plate;

    public Truck(String plate) {
        this.plate = plate;
    }


    public String getTruckId() {
        return truckId;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "truckId='" + truckId + '\'' +
                ", plate='" + plate + '\'' +
                '}';
    }
}
