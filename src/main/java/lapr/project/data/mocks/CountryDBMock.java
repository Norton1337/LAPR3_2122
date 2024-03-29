package lapr.project.data.mocks;

import lapr.project.model.country.Country;
import lapr.project.model.country.idb.ICountryDB;

import java.util.LinkedList;
import java.util.List;

import static lapr.project.utils.Utils.randomUUID;

public class CountryDBMock implements ICountryDB {

    List<Country> allContries = new LinkedList<>();

    @Override
    public List<Country> getAllCountries() {
        return new LinkedList<>(allContries);
    }

    @Override
    public Country getCountryById(String id) {
        for (Country elems : allContries) {
            if (elems.getId().equals(id)) {
                return elems;
            }
        }
        return null;
    }


    @Override
    public String getCountryIdByName(String name) {
        for (Country elems : allContries) {
            if (elems.getCountryName().trim().equals(name.trim())) {
                return elems.getId();
            }
        }
        return "null";
    }


    @Override
    public boolean addCountry(Country country) {
        country.setId(randomUUID());
        return allContries.add(country);
    }

}
