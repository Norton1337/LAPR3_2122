package lapr.project.controller;

import lapr.project.controller.helper_classes.CountryColour;
import lapr.project.controller.helper_classes.KMTravelledCalculator;
import lapr.project.controller.model_controllers.CountryController;
import lapr.project.controller.model_controllers.LocalsController;
import lapr.project.controller.model_controllers.SeadistController;
import lapr.project.data.graph_files.AdjacencyMatrixGraph;
import lapr.project.data.graph_files.EdgeAsDoubleGraphAlgorithms;
import lapr.project.model.borders.Borders;
import lapr.project.model.borders.idb.IBordersDB;
import lapr.project.model.country.Country;
import lapr.project.model.country.idb.ICountryDB;
import lapr.project.model.locals.Locals;
import lapr.project.model.locals.idb.ILocals;
import lapr.project.model.seadists.Seadist;
import lapr.project.model.seadists.idb.ISeadist;

import java.util.*;

import static lapr.project.utils.Utils.convertCoordinates;
import static lapr.project.utils.Utils.sortMapByValue;


public class ToMatrixController {

    private final AdjacencyMatrixGraph<Locals, Double> freightNetworkMatrix;
    private final AdjacencyMatrixGraph<String, Double> matrixToColour;

    private final CountryController countryController;
    private final LocalsController localsController;
    private final SeadistController seadistController;


    public ToMatrixController(ILocals localsDB, ISeadist seadistDB, ICountryDB countryDB, IBordersDB bordersDB) {
        this.freightNetworkMatrix = new AdjacencyMatrixGraph<>();
        this.matrixToColour = new AdjacencyMatrixGraph<>();

        this.countryController = new CountryController(countryDB, bordersDB, localsDB);
        this.localsController = new LocalsController(countryDB, localsDB);
        this.seadistController = new SeadistController(localsDB, seadistDB);
    }

    public Map<Locals, Double> getNClosenessPlaces(List<Locals> localsList, Locals port, int nClosest) {
        TreeMap<Locals, Double> closestMap = new TreeMap<>();
        Map<Locals, Double> returnMap = new LinkedHashMap<>();

        /**
         * Vai buscar tudo e poe no mapa
         */
        for (Locals elems : localsList) {


            String portCountryId = port.getCountryId();
            String portCountry = countryController.findById(portCountryId).getCountryName();
            String portContinent = countryController.findById(portCountryId).getContinent();

            String elemCountryId = elems.getCountryId();
            String elemCountry = countryController.findById(elemCountryId).getCountryName();
            String elemContinent = countryController.findById(elemCountryId).getContinent();


            if (elemContinent.equals(portContinent) && !portCountry.equals(elemCountry)) {

                LinkedList<Locals> path = new LinkedList<>();
                double weight = EdgeAsDoubleGraphAlgorithms.shortestPath(this.freightNetworkMatrix, port, elems, path);
                if (weight > 0) {

                    //System.out.printf("%s   %s  %s  %s\n", port.getName(), elems.getName(), weight, path );
                    closestMap.put(elems, weight);
                }
            }

        }

        LinkedHashMap<Locals, Double> sortedMap = sortMapByValue(closestMap);

        int count = 0;
        for (Locals locals : sortedMap.keySet()) {

            if (count < nClosest) {
                returnMap.put(locals, sortedMap.get(locals));
                count++;
            }

        }

        return returnMap;
    }


    public AdjacencyMatrixGraph<Locals, Double> buildFreightNetwork(int nClosestPorts) {

        for (Locals elem : localsController.getAllLocals()) {
            freightNetworkMatrix.insertVertex(elem);
        }


        /**
         * Insert Edge relative to borders
         */


        for (Locals elems : localsController.getAllCapitals()) {
            String countryName = countryController.getCountryWithCapital(elems.getName()).getCountryName();

            for (Borders borders : countryController.getAllBordersOfCountry(countryName)) {


                Country country1 = countryController.findById(borders.getCountry1Id());
                Country country2 = countryController.findById(borders.getCountry2Id());

                Locals locals1 = localsController.getLocalWithCapital(country1.getCapital());
                Locals locals2 = localsController.getLocalWithCapital(country2.getCapital());

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
         * Insert Edge relative to ports from seadist with same country
         */

        for (Seadist elem : seadistController.getAllSeadist()) {

            //TODO Connect only ports of the same country,

            //buscar fromPortId e tranformar em local
            Locals locals1 = localsController.getLocalWithPortId(elem.getFromPortId());
            String portCountry1 = "";

            if (locals1 != null) portCountry1 = countryController.findById(locals1.getCountryId()).getCountryName();


            //buscar portid e transformar em local
            Locals locals2 = localsController.getLocalWithPortId(elem.getToPortId());
            String portCountry2 = "";

            if (locals2 != null) portCountry2 = countryController.findById(locals2.getCountryId()).getCountryName();


            //pegar distancia
            float weight = elem.getDistance();


            if (portCountry1.equals(portCountry2) && locals1 != null && locals2 != null) {
                freightNetworkMatrix.insertEdge(locals1, locals2, (double) weight);
            }
        }


        /**
         * Insert Edge relative port closest to capital
         */

        for (Locals capitals : localsController.getAllCapitals()) {
            Map<Locals, Double> smallElem = new TreeMap<>();
            for (Locals ports : localsController.getAllPorts()) {

                String countryIdCapital = capitals.getCountryId();
                String countryName = countryController.findById(countryIdCapital).getCountryName();

                String portsIdCapital = ports.getCountryId();
                String portsCountryName = countryController.findById(portsIdCapital).getCountryName();


                if (countryName.equals(portsCountryName)) {
                    KMTravelledCalculator cal = new KMTravelledCalculator();

                    String capitalLat = convertCoordinates(capitals.getCoordinates())[0];
                    String capitalLon = convertCoordinates(capitals.getCoordinates())[1];

                    String portLat = convertCoordinates(ports.getCoordinates())[0];
                    String portLon = convertCoordinates(ports.getCoordinates())[1];

                    double weight = cal.calculate(capitalLat, capitalLon, portLat, portLon);
                    smallElem.put(ports, weight);
                }
            }

            if (smallElem.size() > 0) {
                Locals nearest = sortMapByValue(smallElem).keySet().iterator().next();
                //System.out.printf("%s   %s  weight=%s\n",capitals.getName(), nearest.getName(), smallElem.get(nearest));
                freightNetworkMatrix.insertEdge(capitals, nearest, smallElem.get(nearest));
            }
        }


        /**
         * Insert Edge relative to n closest
         */

        for (Locals elems : localsController.getAllPorts()) {
            Map<Locals, Double> nearestPorts = getNClosenessPlaces(localsController.getAllPorts(), elems, nClosestPorts);
            if (nearestPorts.size() != 0) {
                /* Show that's working
                if(elems.getName().equals("Leixoes")){
                    //System.out.printf("%s   %s\n",elems.getName(), nearestPorts);
                }
                 */


                for (Locals elemsOfNearest : nearestPorts.keySet()) {
                    freightNetworkMatrix.insertEdge(elems, elemsOfNearest, nearestPorts.get(elemsOfNearest));
                    //System.out.printf("%s   %s  %s\n",elems.getName(), elemsOfNearest.getName(),nearestPorts.get(elemsOfNearest) );
                }
            }
        }


        return freightNetworkMatrix;
    }


    public void printFreightNetworkMatrix() {
        System.out.println(freightNetworkMatrix);
    }

    public void printMatrixToColour() {
        System.out.println(matrixToColour);
    }


    public AdjacencyMatrixGraph<Locals, Double> buildMatrixToColour() {

        /**
         * Insert all vertices, all Countries
         */

        for (Country elems : countryController.getAllCountries()) {
            List<Borders> borders = countryController.getAllBordersOfCountry(elems.getCountryName());
            if (borders.size() > 0) {
                matrixToColour.insertVertex(elems.getCountryName());
            }
        }


        /**
         * Insert all edges, all Country borders
         */

        for (Country elems : countryController.getAllCountries()) {
            String country = elems.getCountryName();
            List<Borders> countryBorders = countryController.getAllBordersOfCountry(elems.getCountryName());

            for (Borders borders : countryBorders) {
                String country1 = countryController.findById(borders.getCountry1Id()).getCountryName();
                String country2 = countryController.findById(borders.getCountry2Id()).getCountryName();

                if (country.equals(country1) && !country.equals(country2)) {
                    matrixToColour.insertEdge(country, country2, 0.0);
                }

                if (country.equals(country2) && !country.equals(country1)) {
                    matrixToColour.insertEdge(country, country1, 0.0);
                }
            }
        }


        return null;
    }

    public CountryColour findCountryColourWithName(List<CountryColour> allC, String name) {
        for (CountryColour elems : allC) {
            if (elems.getCountry().equals(name)) {
                return elems;
            }
        }
        return null;
    }

    public int returnColoursFromGoingVert(List<CountryColour> allC, List<Object> borders) {
        List<Integer> colours = new LinkedList<>();

        for (Object elems : borders) {
            String country = (String) elems;
            CountryColour countryColour = findCountryColourWithName(allC, country);
            colours.add(findCountryColourWithName(allC, countryColour.getCountry()).getColour());
        }

        for (int i = 0; i < borders.size(); i++) {
            if (!colours.contains(i)) {
                return i;
            }
        }

        System.out.println(colours);

        return -1;
    }


    public void colorMatrix() {
        Stack<CountryColour> colourStack = new Stack<>();
        List<CountryColour> allVert = new LinkedList<>();
        List<String> alreadyUsed = new LinkedList<>();

        List<CountryColour> finalResult = new LinkedList<>();


        for (String elems : matrixToColour.vertices()) {
            allVert.add(new CountryColour(elems, -1));
        }


        /**
         * Adicionar a stack o primeiro elemento a nossa escolha, neste caso Portugal
         */

        CountryColour countryColourWithName = findCountryColourWithName(allVert, "Portugal");
        countryColourWithName.setColour(0);
        colourStack.add(countryColourWithName);

        while (!colourStack.isEmpty()) {
            /**
             * Remover ultimo elemento a entrar LIFO
             */

            CountryColour last = colourStack.pop();
            // V^3
            if (!alreadyUsed.contains(last.getCountry())) {
                /**
                 * Ver todas as bordas os valores das cores
                 */
                int minColor = returnColoursFromGoingVert(allVert, matrixToColour.outgoingVertices(last.getCountry()));

                CountryColour countryColour = findCountryColourWithName(allVert, last.getCountry());
                countryColour.setColour(minColor);
                last.setColour(minColor);

                System.out.printf("%s   %s\n", last, minColor);


                /**
                 * Adicionar restantes bordas a Stack
                 */

                for (Object elems : matrixToColour.outgoingVertices(last.getCountry())) {
                    colourStack.add(new CountryColour((String) elems, -1));
                }
            }

            alreadyUsed.add(last.getCountry());
        }
    }


}
