package lapr.project.model.seadists;

public class Seadist {

    private String id;
    private String fromPortId;
    private String toPortId;
    private float distance;


    public Seadist(String fromPortId, String toPortId, float distance) {
        this.fromPortId = fromPortId;
        this.toPortId = toPortId;
        this.distance = distance;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromPortId() {
        return fromPortId;
    }

    public void setFromPortId(String fromPortId) {
        this.fromPortId = fromPortId;
    }

    public String getToPortId() {
        return toPortId;
    }

    public void setToPortId(String toPortId) {
        this.toPortId = toPortId;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Seadist{" +
                "id='" + id + '\'' +
                ", fromPortId='" + fromPortId + '\'' +
                ", toPortId='" + toPortId + '\'' +
                ", distance=" + distance +
                '}';
    }
}
