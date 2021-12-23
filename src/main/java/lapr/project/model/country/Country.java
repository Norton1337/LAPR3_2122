package lapr.project.model.country;

public class Country {

    private String id;

    private String continent;
    private String alpha2Code;
    private String alpha3Code;
    private String countryName;
    private String population;
    private String capital;
    private String coordinates;


    public Country(String continent, String alpha2Code, String alpha3Code, String countryName, String population, String capital, String coordinates) {

        this.continent = continent;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.countryName = countryName;
        this.population = population;
        this.capital = capital;
        this.coordinates = coordinates;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}
