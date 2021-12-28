package lapr.project.controller;

import lapr.project.controller.helper_classes.KMTravelledCalculator;
import lapr.project.controller.model_controllers.CountryController;
import lapr.project.data.graph_files.AdjacencyMatrixGraph;
import lapr.project.data.graph_files.GraphAlgorithms;
import lapr.project.model.borders.Borders;
import lapr.project.model.borders.idb.IBordersDB;
import lapr.project.model.country.Country;
import lapr.project.model.country.idb.ICountryDB;
import lapr.project.model.locals.Locals;
import lapr.project.model.locals.idb.ILocals;
import lapr.project.model.seadists.idb.ISeadist;

import static lapr.project.utils.Utils.convertCoordinates;
import static lapr.project.utils.Utils.printList;


public class ToMatrixController {

    private final AdjacencyMatrixGraph<Locals, Double> freightNetworkMatrix;
    private final ILocals localsDB;
    private final ISeadist seadistDB;
    private final ICountryDB countryDB;
    private final IBordersDB bordersDB;

    private final CountryController countryController;


    public ToMatrixController(ILocals localsDB, ISeadist seadistDB, ICountryDB countryDB, IBordersDB bordersDB) {
        this.freightNetworkMatrix = new AdjacencyMatrixGraph<>();
        this.localsDB = localsDB;
        this.seadistDB = seadistDB;
        this.countryDB = countryDB;
        this.bordersDB = bordersDB;
        this.countryController = new CountryController(countryDB, bordersDB, localsDB);
    }


    public void buildMatrix(int nClosestPorts) {
        for (Locals elem : localsDB.getAllPortsAndWarehouses()) {
            freightNetworkMatrix.insertVertex(elem);
        }


        /**
         * Insert Edge relative to borders
         */


        for (Locals elems : localsDB.getAllCapitals()) {
            String countryName = countryDB.getCountryWithCapital(elems.getName()).getCountryName();

            for (Borders borders : countryController.getAllBordersOfCountry(countryName)) {


                Country country1 = countryDB.getCountryById(borders.getCountry1Id());
                Country country2 = countryDB.getCountryById(borders.getCountry2Id());

                Locals locals1 = localsDB.getLocalWithCapital(country1.getCapital());
                Locals locals2 = localsDB.getLocalWithCapital(country2.getCapital());

                String lat1 = convertCoordinates(locals1.getCoordinates())[0];
                String long1 = convertCoordinates(locals1.getCoordinates())[1];

                String lat2 = convertCoordinates(locals2.getCoordinates())[0];
                String long2 = convertCoordinates(locals2.getCoordinates())[1];

                KMTravelledCalculator kmTravelledCalculator = new KMTravelledCalculator();
                Double weight = kmTravelledCalculator.calculate(lat1, long1, lat2, long2);

                //System.out.printf("DEBUG MATRIX: %s %s  %s\n", locals1.getName(), locals2.getName(), weight);
                freightNetworkMatrix.insertEdge(locals1, locals2, weight);
            }
        }


        /**
         * Insert Edge relative to ports from seadist
         */

        //TODO



        /**
         * Insert Edge relative to n closest
         */


    }


    public void printMatrix() {
        System.out.println(freightNetworkMatrix.toString());
    }
}
