package lapr.project.model.client;

public class Client {

    public String id;
    public String userId;
    public String name;
    public String address;
    public String cellphone;


    public Client(String name, String address, String cellphone) {
        this.name = name;
        this.address = address;
        this.cellphone = cellphone;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", cellphone='" + cellphone + '\'' +
                '}';
    }
}
