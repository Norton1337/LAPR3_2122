package lapr.project.ui;

import lapr.project.controller.model_controllers.CountryController;
import lapr.project.model.country.Country;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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


    public void importCountries(String filepath) {

        Country newCountry;

        try (BufferedReader input = new BufferedReader(new FileReader(filepath))) {
            String line;

            while ((line = input.readLine()) != null) {

                List<String> list = new ArrayList<>(Arrays.asList(line.split(",")));

                if (!list.get(0).equals("Continent")) {

                    /**
                     * Country Object Created
                     */
                    newCountry =  new Country(list.get(0), list.get(1), list.get(2), list.get(3),
                            list.get(4), list.get(5), coordinates(list.get(6), list.get(7)));


                    /**
                     *  Add to DB
                     */

                    countryController.addCountry(newCountry);
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


}
