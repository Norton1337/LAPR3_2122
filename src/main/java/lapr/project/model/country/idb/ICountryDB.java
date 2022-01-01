package lapr.project.model.country.idb;

import lapr.project.model.country.Country;

import java.util.List;

public interface ICountryDB {

    List<Country> getAllCountries();

    Country getCountryById(String id);

    String getCountryIdByName(String name);

    boolean addCountry(Country elem);

}
