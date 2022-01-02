package lapr.project.controller.helper_classes;

public class CountryColour {

    private String country;
    private int colour;

    public CountryColour(String country, int colour) {
        this.country = country;
        this.colour = colour;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "CountryColour{" +
                "country='" + country + '\'' +
                ", colour=" + colour +
                '}';
    }
}
