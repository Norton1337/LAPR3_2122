package lapr.project.controller.model_controllers;

import lapr.project.model.borders.Borders;
import lapr.project.model.borders.idb.IBordersDB;
import lapr.project.model.country.Country;
import lapr.project.model.country.idb.ICountryDB;

import java.util.List;

public class CountryController {

    private final ICountryDB countryDB;
    private final IBordersDB borderDB;

    public CountryController(ICountryDB countryDB, IBordersDB borderDB) {
        this.countryDB = countryDB;
        this.borderDB = borderDB;
    }

    public boolean addCountry(Country country){
        return countryDB.addCountry(country);
    }

    public boolean addBorder(Borders borders){
        return borderDB.addBorder(borders);
    }

    public List<Country> getAllCountries(){
        return countryDB.getAllCountries();
    }


    public Country findById(String id){
        return countryDB.getCountryById(id);
    }

    public String findByName(String name){
        return countryDB.getCountryIdByName(name);
    }

}
