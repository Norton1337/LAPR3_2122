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

import static lapr.project.utils.Utils.*;


public class ToMatrixController {

    private final AdjacencyMatrixGraph<Locals, Double> freightNetworkMatrix;
    private final AdjacencyMatrixGraph<String, Double> matrixToColour;
    private final AdjacencyMatrixGraph<Locals, Double> landMatrix;
    private final AdjacencyMatrixGraph<Locals, Double> maritimeMatrix;

    private final CountryController countryController;
    private final LocalsController localsController;
    private final SeadistController seadistController;


    public ToMatrixController(ILocals localsDB, ISeadist seadistDB, ICountryDB countryDB, IBordersDB bordersDB) {
        this.freightNetworkMatrix = new AdjacencyMatrixGraph<>();
        this.matrixToColour = new AdjacencyMatrixGraph<>();
        this.landMatrix = new AdjacencyMatrixGraph<>();
        this.maritimeMatrix = new AdjacencyMatrixGraph<>();

        this.countryController = new CountryController(countryDB, bordersDB, localsDB);
        this.localsController = new LocalsController(countryDB, localsDB);
        this.seadistController = new SeadistController(localsDB, seadistDB);
    }

    public Map<Locals, Double> getNClosenessPlaces(AdjacencyMatrixGraph<Locals, Double> matrix, List<Locals> localsList, Locals local, int nClosest) {
        TreeMap<Locals, Double> closestMap = new TreeMap<>();
        Map<Locals, Double> returnMap = new LinkedHashMap<>();

        /**
         * Vai buscar tudo e poe no mapa
         */
        for (Locals elems : localsList) {


            String portCountryId = local.getCountryId();
            String portCountry = countryController.findById(portCountryId).getCountryName();
            String portContinent = countryController.findById(portCountryId).getContinent();

            String elemCountryId = elems.getCountryId();
            String elemCountry = countryController.findById(elemCountryId).getCountryName();
            String elemContinent = countryController.findById(elemCountryId).getContinent();


            if (elemContinent.equals(portContinent) && !portCountry.equals(elemCountry)) {

                LinkedList<Locals> path = new LinkedList<>();
                double weight = EdgeAsDoubleGraphAlgorithms.shortestPath(matrix, local, elems, path);
                if (weight > 0) {

                    //System.out.printf("%s   %s  %s  %s\n", port.getName(), elems.getName(), weight, path );
                    closestMap.put(elems, weight);
                }
            }

        }

        LinkedHashMap<Locals, Double> sortedMap = sortMapByValue(closestMap);

        return getNFromMap(sortedMap, nClosest);
    }


    public AdjacencyMatrixGraph<Locals, Double> buildFreightNetwork(int nClosestPorts) {

        for (Locals elem : localsController.getAllPorts()) {
            freightNetworkMatrix.insertVertex(elem);
            maritimeMatrix.insertVertex(elem);
        }

        for (Locals elem : localsController.getAllCapitals()) {
            freightNetworkMatrix.insertVertex(elem);
            landMatrix.insertVertex(elem);
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
                landMatrix.insertEdge(locals1, locals2, weight);

            }
        }


        /**
         * Insert Edge relative to ports from seadist with same country
         */

        for (Seadist elem : seadistController.getAllSeadist()) {

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
                maritimeMatrix.insertEdge(locals1, locals2, (double) weight);
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
            Map<Locals, Double> nearestPorts = getNClosenessPlaces(this.freightNetworkMatrix, localsController.getAllPorts(), elems, nClosestPorts);
            if (nearestPorts.size() != 0) {

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

        for (int i = 0; i < 1000; i++) {
            if (!colours.contains(i)) {
                return i;
            }
        }

        return -1;
    }


    public List<CountryColour> colorMatrix() {
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

                if (minColor <= 3) {
                    CountryColour countryColour = findCountryColourWithName(allVert, last.getCountry());
                    countryColour.setColour(minColor);
                    last.setColour(minColor);

                    finalResult.add(last);
                } else {
                    colourStack.add(last);
                }


                /**
                 * Adicionar restantes bordas a Stack
                 */

                for (Object elems : matrixToColour.outgoingVertices(last.getCountry())) {
                    colourStack.add(new CountryColour((String) elems, -1));
                }
            }

            alreadyUsed.add(last.getCountry());
        }

        return finalResult;
    }


    public Map<Locals, Double> centralPorts(AdjacencyMatrixGraph<Locals, Double> localsMatrix, int nLocals) {


        Map<Locals, Double> localsMap = new LinkedHashMap<>();
        Map<Locals, Double> portsMap = new LinkedHashMap<>();
        LinkedHashMap<Locals, Double> centralPorts = new LinkedHashMap<>();


        /*
         * Add all existing Ports to map and initialize their
         * centrality value to 0.
         */
        for (Locals ports : localsController.getAllLocals()) {
            localsMap.put(ports, 0.0);
        }


        for (Locals port1 : localsMap.keySet()) {

            for (Locals port2 : localsMap.keySet()) {

                LinkedList<Locals> path = new LinkedList<>();

                String port1Continent = countryController.findById(port1.getCountryId()).getContinent();
                String port2Continent = countryController.findById(port2.getCountryId()).getContinent();


                if (!port1.getName().equals(port2.getName()) && (port1Continent.equals(port2Continent))) {

                    double weight = EdgeAsDoubleGraphAlgorithms.shortestPath(localsMatrix, port1, port2, path);
                    if (!path.isEmpty()) {

                        path.remove(0);
                        path.remove(path.size() - 1);

                        for (Locals elem : path) {

                            double portValue = localsMap.get(elem);
                            localsMap.put(elem, portValue + 1.0);
                        }
                    }
                    //System.out.println("port1 "+port1.getName() + "  \nports2 "+port2.getName());
                    //System.out.println("path "+path);
                }
            }
        }

        for (Locals ports : localsMap.keySet()) {
            if (ports.getType().equals("Port")) {
                portsMap.put(ports, localsMap.get(ports));

            }
        }

        int count = 0;
        Map<Locals, Double> finalMap = new LinkedHashMap<>(sortMapByValue(portsMap));
        finalMap = reverseMap(finalMap);

        return getNFromMap(finalMap, nLocals);

        //printMap(finalMap);
    }


    public void printLandMaritime() {
        //printList(landMatrix.getVertices());
        //printList(maritimeMatrix.getVertices());
        //System.out.println(maritimeMatrix);
    }


    public List<List<Locals>> shortestPaths(AdjacencyMatrixGraph<Locals, Double> localsMatrix, String orig, String dest, List<String> portsToPass) {
        /**
         * To search for origin and destiny by name, to not insert a Local
         */
        if (orig == null || dest == null) {
            return null;
        }
        Locals origLocal = localsController.getLocalWithName(orig);
        Locals destLocal = localsController.getLocalWithName(dest);

        List<List<Locals>> allPaths = new LinkedList<>();
        LinkedList<Locals> shortestPath = new LinkedList<>();

        /**
         * shortest path, whether is port or city (using original matrix)
         */
        EdgeAsDoubleGraphAlgorithms.shortestPath(localsMatrix, origLocal, destLocal, shortestPath);
        allPaths.add(shortestPath);

        /**
         *  shortest maritime path (only using ports matrix)
         */
        allPaths.add(maritimePath(maritimeMatrix ,origLocal, destLocal));
        /**
         * Shortest land path (using land matrix)
         */
        List<Locals> landPath = landPath(landMatrix, origLocal,destLocal);
        allPaths.add(landPath);


        return allPaths;

    }

    /**
     * Land path only includes capitals, except for origin and
     * destiny that can be capitals or ports
     */
    public LinkedList<Locals> landPath(AdjacencyMatrixGraph<Locals, Double> matrix, Locals orig, Locals dest) {

        LinkedList<Locals> path = new LinkedList<>();
        Map<Locals, Double> closestCapitalsMap = new LinkedHashMap<>();
        Map<Locals, Double> closestCapitalsMapAux = new LinkedHashMap<>();


        if (orig.getType().equals("Capital") && dest.getType().equals("Capital")) {

            EdgeAsDoubleGraphAlgorithms.shortestPath(matrix, orig, dest, path);
            return path;
        }

        if (orig.getType().equals("Port") && dest.getType().equals("Capital")){

            closestCapitalsMap = getNClosenessPlaces(matrix, localsController.getAllCapitals(), orig, 1);
            Locals local = setToList(closestCapitalsMap.keySet()).get(0);

            EdgeAsDoubleGraphAlgorithms.shortestPath(matrix, local, dest, path);
            path.add(0, orig);
            return path;
        }

        else if (orig.getType().equals("Capital") && dest.getType().equals("Port")) {

           closestCapitalsMap = getNClosenessPlaces(matrix, localsController.getAllCapitals(), dest, 1);
           Locals local = setToList(closestCapitalsMap.keySet()).get(0);

           EdgeAsDoubleGraphAlgorithms.shortestPath(matrix, orig, local, path);
           path.add(dest);
           return path;
        }
        else {

            closestCapitalsMap = getNClosenessPlaces(matrix, localsController.getAllCapitals(), orig, 1);
            closestCapitalsMapAux = getNClosenessPlaces(matrix,localsController.getAllCapitals(), dest, 1);

            Locals origLocal = setToList(closestCapitalsMap.keySet()).get(0);
            Locals destLocal = setToList(closestCapitalsMapAux.keySet()).get(0);

            EdgeAsDoubleGraphAlgorithms.shortestPath(landMatrix, origLocal, destLocal, path);


            EdgeAsDoubleGraphAlgorithms.shortestPath(matrix, origLocal, destLocal, path);
            path.add(0,orig);
            path.add(dest);

            return path;
        }

    }

    /**
     * Maritime path only includes ports. If no ports
     * are passed by parameter there is no maritime
     * path, returns null
     */
    public List<Locals> maritimePath(AdjacencyMatrixGraph<Locals, Double> matrix, Locals orig, Locals dest) {

        LinkedList<Locals> path = new LinkedList<>();

        if (orig.getType().equals("Port") && dest.getType().equals("Port")) {

            EdgeAsDoubleGraphAlgorithms.shortestPath(matrix, orig, dest, path);
            return path;
        }
        //System.out.println("No maritime path found");
        return null;
    }

    public List<Locals> shortestThroughNPlaces(AdjacencyMatrixGraph<Locals, Double> matrix, Locals origLocal,
                                               Locals destLocal, List<String> placesToPass){

        int countPermutations = 0;
        double finalWeight = Double.MAX_VALUE;

        LinkedList<Locals> path = new LinkedList<>();
        List<Locals> finalPath = new LinkedList<>();
        List<Locals> elemsList = new LinkedList<>();
        List<List<String>> localsPermutationsList = new ArrayList<>();

        //Locals origLocal = localsController.getLocalWithName(orig);
        //Locals destLocal = localsController.getLocalWithName(dest);

        /**
         * List of lists with all possible combinations of locals to pass through
         */
        localsPermutationsList = generatePermutation(placesToPass);


        for (int i =0 ; i<localsPermutationsList.size(); i++){

            double weight = 0.0;

            List<String> newList = localsPermutationsList.get(i);
            //System.out.println("Permutation list:  " + newList);

            countPermutations++;

            // Calculate distance between origin and first element on permutation list
            weight = EdgeAsDoubleGraphAlgorithms.shortestPath(matrix, origLocal,
                    localsController.getLocalWithName(newList.get(0)), path);


            elemsList.addAll(addElementsToList(path));
            //System.out.println("Path from origin to 1element of permutation list");
            //printList(elemsList);
            elemsList.remove(elemsList.size()-1);

            //System.out.println("Path from origin to 1element of permutation list");
            //printList(elemsList);

            // Calculate distance between elements to permute
            for (int j = 0; j < newList.size()-1; j++){

                weight+= EdgeAsDoubleGraphAlgorithms.shortestPath(matrix,
                        localsController.getLocalWithName(newList.get(j)),
                        localsController.getLocalWithName(newList.get(j+1)), path);

                elemsList.addAll(addElementsToList(path));
                //System.out.println("Path between elements in permutation list");
                //printList(elemsList);
                elemsList.remove(elemsList.size()-1);

                //System.out.println("Path between elements in permutation list");
                //printList(elemsList);
            }
            // Calculate distance between last element on permutation list and destiny
            weight+= EdgeAsDoubleGraphAlgorithms.shortestPath(matrix,
                    localsController.getLocalWithName(newList.get(newList.size()-1)), destLocal, path);

            elemsList.addAll(addElementsToList(path));

            //System.out.println("Path between last element of permutation list and destiny");
            //printList(elemsList);

            //System.out.println("Path weight "+weight);

            if (weight < finalWeight){

                finalPath = elemsList;
                finalWeight = weight;
            }
            elemsList = new LinkedList<>();

        }

        //printList(finalPath);

        //System.out.println("Number of possible combinations: "+countPermutations + "\n");
        //System.out.println("Number of possible combinations: "+countPermutations);
        return finalPath;
    }



}
