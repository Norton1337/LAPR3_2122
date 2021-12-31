package lapr.project.controller;

import lapr.project.controller.helper_classes.KMTravelledCalculator;
import lapr.project.controller.model_controllers.CountryController;
import lapr.project.controller.model_controllers.PortsAndWarehousesController;
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
    private final ILocals localsDB;
    private final ISeadist seadistDB;
    private final ICountryDB countryDB;
    private final IBordersDB bordersDB;

    private final CountryController countryController;
    private final PortsAndWarehousesController portsAndWarehousesController;


    public ToMatrixController(ILocals localsDB, ISeadist seadistDB, ICountryDB countryDB, IBordersDB bordersDB) {
        this.freightNetworkMatrix = new AdjacencyMatrixGraph<>();
        this.localsDB = localsDB;
        this.seadistDB = seadistDB;
        this.countryDB = countryDB;
        this.bordersDB = bordersDB;
        this.countryController = new CountryController(countryDB, bordersDB, localsDB);
        this.portsAndWarehousesController = new PortsAndWarehousesController(countryDB, localsDB);
    }

    public void getNClosenessPlaces(Locals port, int nClosest){
        TreeMap<Locals, Double> closestMap = new TreeMap<>();

        List<Locals> nClosestPorts = new LinkedList<>();

        //vai buscar tudo e poe no mapa
        for(Locals elems : portsAndWarehousesController.getAllPortsAndWharehouse()){

            //TODO if que verifica se e do mesmo continente e se o pais e diferente
            String portCountryId = port.getCountryId();
            String portContinent = countryController.findById(portCountryId).getContinent();

            String elemCountryId = elems.getCountryId();
            String elemContinent = countryController.findById(elemCountryId).getContinent();

            if (elemContinent.equals(portContinent)){
                LinkedList<Locals> path = new LinkedList<>();
                double weight = EdgeAsDoubleGraphAlgorithms.shortestPath(this.freightNetworkMatrix, port, elems, path);
                if(weight > 0){

                    //System.out.printf("%s   %s  %s  %s\n", port.getName(), elems.getName(), weight, path );
                    closestMap.put(elems, weight);
                }
            }

        }

        LinkedHashMap<Locals, Double> sortedMap = new LinkedHashMap<>();
        closestMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));


        //TODO pegar no numero do nClosest e adicionar para a lista as keys
        Set<Locals> entry = sortedMap.keySet();

        int count = 0;
        for (Locals locals : entry){

            if (count < nClosest){
                nClosestPorts.add(locals);
                count++;
            }

        }

        printList(nClosestPorts);
        //printMap(sortedMap);
    }


    public void buildMatrix(int nClosestPorts) {

        for (Locals elem : portsAndWarehousesController.getAllPorts()) {
            freightNetworkMatrix.insertVertex(elem);
        }


        /**
         * Insert Edge relative to borders
         */


        for (Locals elems : portsAndWarehousesController.getAllCapitals()) {
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

        for(Seadist elem: seadistDB.getAllSeadist()){

            //TODO Connect only ports of the same country,

            //buscar fromPortId e tranformar em local
            Locals locals1 = localsDB.getLocalWithPortId(elem.getFromPortId());

            //buscar portid e transformar em local
            Locals locals2 = localsDB.getLocalWithPortId(elem.getToPortId());


            //pegar distancia
            float weight = elem.getDistance();


            if(locals1 != null && locals2!=null){
                //adicionar a matriz
                freightNetworkMatrix.insertEdge(locals1, locals2, (double) weight);


                if(locals1.getName().equals("Leixoes")){
                    getNClosenessPlaces(locals1, 3);
                    break;
                }
            }


        }



        /**
         * Insert Edge relative to n closest
         */


    }


    public void printMatrix() {
        System.out.println(freightNetworkMatrix.toString());
    }
}
