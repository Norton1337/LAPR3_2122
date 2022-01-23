package lapr.project.data.database;

import lapr.project.data.db_scripts.DataHandler;
import lapr.project.model.country.Country;
import lapr.project.model.country.idb.ICountryDB;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDB extends DataHandler implements ICountryDB {

    @Override
    public List<Country> getAllCountries() {
        return new ArrayList<>();
    }

    @Override
    public Country getCountryById(String id) {
        return null;
    }


    @Override
    public String getCountryIdByName(String name) {
        return null;
    }

    @Override
    public boolean addCountry(Country elem) {
        if (elem == null) {
            return false;
        }
        try (CallableStatement result = getConnection().prepareCall("{call insertCountry(?,?,?,?,?,?,?,?,?)}")) {
            String[] coordinates = elem.getCoordinates().split(",");
            result.setString(1, elem.getId());
            result.setString(2, elem.getContinent());
            result.setString(3, elem.getAlpha2Code());
            result.setString(4, elem.getAlpha3Code());
            result.setString(5, elem.getCountryName());
            result.setFloat(6, Float.parseFloat(elem.getPopulation()));
            result.setString(7, elem.getCapital());
            result.setFloat(8, Float.parseFloat(coordinates[0]));
            result.setFloat(9, Float.parseFloat(coordinates[1]));
            result.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}
