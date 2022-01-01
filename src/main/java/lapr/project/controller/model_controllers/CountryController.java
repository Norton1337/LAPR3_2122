package lapr.project.controller.model_controllers;

import lapr.project.model.borders.Borders;
import lapr.project.model.borders.idb.IBordersDB;
import lapr.project.model.country.Country;
import lapr.project.model.country.idb.ICountryDB;
import lapr.project.model.locals.Locals;
import lapr.project.model.locals.idb.ILocals;

import java.util.LinkedList;
import java.util.List;


public class CountryController {

    private final ICountryDB countryDB;
    private final IBordersDB borderDB;
    private final ILocals localsDB;

    public CountryController(ICountryDB countryDB, IBordersDB borderDB, ILocals localsDB) {
        this.countryDB = countryDB;
        this.borderDB = borderDB;
        this.localsDB = localsDB;
    }

    public boolean addCountry(Country country){
        Locals newLocal = new Locals("", -1, country.getCapital(), country.getCoordinates());
        newLocal.setType("Capital");
        boolean result = countryDB.addCountry(country);
        newLocal.setCountryId(country.getId());
        localsDB.addPortsAndWarehouses(newLocal);
        return result;
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


    public List<Borders> getAllBordersOfCountry(String countryName){
        List<Borders> bordersOfCountry = new LinkedList<>();
        String idCountry = countryDB.getCountryIdByName(countryName);

        for(Borders elems : borderDB.getAllBorders()){
            if((!(countryDB.getCountryById(elems.getCountry1Id()) == null)) && (!(countryDB.getCountryById(elems.getCountry2Id()) == null ))){
                if(elems.getCountry1Id().contains(idCountry) || elems.getCountry2Id().contains(idCountry) ){
                    bordersOfCountry.add(elems);
                }
            }

        }

        return bordersOfCountry;
    }

    public Country getCountryWithCapital(String capital) {
        for(Country elems: getAllCountries()){
            if(elems.getCapital().equals(capital)){
                return elems;
            }
        }
        return null;
    }

}
