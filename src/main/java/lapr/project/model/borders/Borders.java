package lapr.project.model.borders;

public class Borders {

    private String Id;

    private String country1;
    private String country2;


    public Borders(String country1, String country2) {
        this.country1 = country1;
        this.country2 = country2;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCountry1() {
        return country1;
    }

    public void setCountry1(String country1) {
        this.country1 = country1;
    }

    public String getCountry2() {
        return country2;
    }

    public void setCountry2(String country2) {
        this.country2 = country2;
    }
}
