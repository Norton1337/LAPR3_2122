package lapr.project.ui;

import lapr.project.controller.model_controllers.CountryController;
import lapr.project.model.borders.Borders;
import lapr.project.model.country.Country;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lapr.project.utils.Utils.coordinates;

public class CountryUI {

    private final CountryController countryController;

    public CountryUI(CountryController countryController) {
        this.countryController = countryController;
    }


    public void importCountriesAndBorders(String countryFile, String borderFile) {

        Country newCountry;

        try (BufferedReader inputCountry = new BufferedReader(new FileReader(countryFile))) {
            String line;

            while ((line = inputCountry.readLine()) != null) {

                List<String> list = new ArrayList<>(Arrays.asList(line.split(",")));


                if (!list.get(0).equals("Continent")) {

                    /**
                     * Country Object Created
                     */
                    newCountry = new Country(list.get(0), list.get(1), list.get(2), list.get(3),
                            list.get(4), list.get(5), coordinates(list.get(6), list.get(7)));


                    /**
                     *  Add to DB
                     */

                    countryController.addCountry(newCountry);
                }
            }


        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        try (BufferedReader inputBorders = new BufferedReader(new FileReader(borderFile))) {
            String line;
            Borders newBorder;

            while ((line = inputBorders.readLine()) != null) {

                List<String> list = new ArrayList<>(Arrays.asList(line.split(",")));

                if (!list.get(0).equals("Country1")) {

                    /**
                     * Creat Border Object
                     */
                    String country1Id = countryController.findByName(list.get(0));
                    String country2Id = countryController.findByName(list.get(1));

                    newBorder = new Borders(country1Id, country2Id);
                    countryController.addBorder(newBorder);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}