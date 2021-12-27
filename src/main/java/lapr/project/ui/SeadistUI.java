package lapr.project.ui;

import lapr.project.controller.model_controllers.CountryController;
import lapr.project.controller.model_controllers.SeadistController;
import lapr.project.model.borders.Borders;
import lapr.project.model.country.Country;
import lapr.project.model.seadists.Seadist;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lapr.project.utils.Utils.coordinates;

public class SeadistUI {

    private final SeadistController seadistController;

    public SeadistUI(SeadistController seadistController) {
        this.seadistController = seadistController;
    }


    public void importSeadist(String file) {

        Seadist newSeadist;

        try (BufferedReader inputCountry = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = inputCountry.readLine()) != null) {

                List<String> list = new ArrayList<>(Arrays.asList(line.split(",")));

                if (!list.get(0).equals("from_country")) {

                    /**
                     * Seadist Object Created
                     */
                    newSeadist = new Seadist(list.get(1), list.get(4), Float.parseFloat(list.get(6)));


                    /**
                     *  Add to DB
                     */

                    seadistController.addElemToSeaDist(newSeadist);
                }
            }


        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }
}