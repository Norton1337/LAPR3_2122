package lapr.project.model.borders;

public class Borders {

    private String Id;

    private String country1Id;
    private String country2Id;


    public Borders(String country1, String country2) {
        this.country1Id = country1;
        this.country2Id = country2;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCountry1Id() {
        return country1Id;
    }

    public void setCountry1Id(String country1Id) {
        this.country1Id = country1Id;
    }

    public String getCountry2Id() {
        return country2Id;
    }

    public void setCountry2Id(String country2Id) {
        this.country2Id = country2Id;
    }

    @Override
    public String toString() {
        return "Borders{" +
                "Id='" + Id + '\'' +
                ", country1Id='" + country1Id + '\'' +
                ", country2Id='" + country2Id + '\'' +
                '}';
    }
}
