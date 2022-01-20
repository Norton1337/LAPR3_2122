package lapr.project.model.leasing;

public class Leasing {

    public String id;
    public String clientId;
    public String containerId;
    public String startDate;
    public String endDate;

    public Leasing(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Leasing{" +
                "id='" + id + '\'' +
                ", clientId='" + clientId + '\'' +
                ", containerId='" + containerId + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}

