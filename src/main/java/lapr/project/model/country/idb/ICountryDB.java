package lapr.project.model.country.idb;

import lapr.project.model.country.Country;
import lapr.project.model.ships.Ship;

import java.util.List;

public interface ICountryDB {

    List<Country> getAllCountries();

    Country getCountryById(String id);

    boolean addCountry(Country elem);

    boolean updateCountry(Country elem);

    boolean removeCountry(String id);
}
