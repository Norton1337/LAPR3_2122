package lapr.project.controller.model_controllers;

import lapr.project.model.country.Country;
import lapr.project.model.country.idb.ICountryDB;

import java.util.List;

public class CountryController {

    private final ICountryDB countryDB;

    public CountryController(ICountryDB countryDB) {
        this.countryDB = countryDB;
    }


    public boolean addCountry(Country country){
        return countryDB.addCountry(country);
    }


    public List<Country> getAllCountries(){
        return countryDB.getAllCountries();
    }


    public Country findById(String id){
        return countryDB.getCountryById(id);
    }

}
