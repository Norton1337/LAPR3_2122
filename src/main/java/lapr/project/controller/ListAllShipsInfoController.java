package lapr.project.controller;

import lapr.project.Dtos.ShipsMovementDto;
import lapr.project.controller.HelperClasses.KMTravelledCalculator;
import lapr.project.controller.HelperClasses.SortByNumberOfMovements;
import lapr.project.controller.HelperClasses.SortByTravelledDistance;
import lapr.project.model.HelperClasses.ShipAndData;
import lapr.project.model.ShipPositionData.ShipPositonData;
import lapr.project.model.Ships.Ship;

import java.util.*;

import static lapr.project.utils.Utils.orderedByTime;
import static lapr.project.utils.Utils.stripC;

public class ListAllShipsInfoController {

    private MostTravelledShipsController mostTravelledShips = new MostTravelledShipsController();


    /**
     * Creates a list for all the ships with their details, ordered by
     *  travelled distance in descending order and by number of movements
     *  in ascending order.
     * The method receives a list with all the ships and for each one
     * calculates their delta distance, travelled distance and number of movements.
     * @param shipsAndDataList list with all ships
     * @return a list for all the ships describing their MMSI, number o movements,
     *  distance travelled and delta distance.
     */
    public List<ShipsMovementDto> shipLog(List<ShipAndData> shipsAndDataList){

        List<ShipsMovementDto> shipsMovementDtos = new ArrayList<>();

        for (ShipAndData ships: shipsAndDataList){

            List<ShipPositonData> newArrayList = ships.getShipPositonData();
            orderedByTime(newArrayList);

            String MMSI = ships.getShip().getMMSI();
            String numberOfMovements = String.valueOf(ships.getShipPositonData().size());
            String travelledDistance = String.valueOf(mostTravelledShips.getTotalPerShip(newArrayList));
            String deltaDistance = String.valueOf(mostTravelledShips.getDeltaDistance(newArrayList));

            shipsMovementDtos.add(new ShipsMovementDto(MMSI, travelledDistance, deltaDistance, numberOfMovements));


        }
        //Collections.sort(shipsMovementDtos);
        //Collections.reverse(shipsMovementDtos);

        Collections.sort(shipsMovementDtos, Collections.reverseOrder(new SortByTravelledDistance()).thenComparing(new SortByNumberOfMovements()));
        return shipsMovementDtos;
    }

    public void pairShips(List<ShipAndData> shipsAndDataList){
        KMTravelledCalculator calculator = new KMTravelledCalculator();
        MostTravelledShipsController mostTravelledShipsController = new MostTravelledShipsController();

        List<Ship> listOfShipsLessThan5Kms = new ArrayList<>();


        Map<Ship, List<ShipPositonData>> shipsShipPositonData  = new LinkedHashMap<>();

        for (ShipAndData elems : shipsAndDataList) {
            shipsShipPositonData.put(elems.getShip(), elems.getShipPositonData());
        }

        for (Map.Entry<Ship, List<ShipPositonData>> entry : shipsShipPositonData.entrySet()) {
            for (Map.Entry<Ship, List<ShipPositonData>> entry1 : shipsShipPositonData.entrySet()) {
                int ship1Size = entry.getValue().size()-1;
                String ship1CordinatesInitial = entry.getValue().get(0).getCoordinates();
                String ship1CordinatesEnd = entry.getValue().get(ship1Size).getCoordinates();

                int ship2Size = entry1.getValue().size()-1;
                String ship2CordinatesInitial = entry1.getValue().get(0).getCoordinates();
                String ship2CordinatesEnd = entry1.getValue().get(ship2Size).getCoordinates();

                if(!entry.getKey().getMMSI().equals(entry1.getKey().getMMSI())){
                    double initialDistanceInital =  calculator.calculate(stripC(ship1CordinatesInitial)[0], stripC(ship1CordinatesInitial)[1],
                            stripC(ship2CordinatesInitial)[0],stripC(ship2CordinatesInitial)[1]);

                    double endDistanceInital =  calculator.calculate(stripC(ship1CordinatesEnd)[0], stripC(ship1CordinatesEnd)[1],
                            stripC(ship2CordinatesEnd)[0],stripC(ship2CordinatesEnd)[1]);


                    Double distanceTravelsShip1 = mostTravelledShipsController.getTotalPerShip(entry.getValue());
                    Double distanceTravelsShip2 = mostTravelledShipsController.getTotalPerShip(entry1.getValue());


                    if(initialDistanceInital < 5 || endDistanceInital < 5 &&
                            distanceTravelsShip1 > 10 && distanceTravelsShip2 > 10){

                        //System.out.printf("KEY: %s  %s   KEY:%s   %s\n", entry.getKey().getMMSI(), entry.getValue().size(), entry1.getKey().getMMSI(), entry1.getValue().size());
                    }



                }
            }
        }







            /*
            for(ShipAndData ship2: shipsAndDataList){
                Ship shipN = ship2.getShip();

                //System.out.printf("%s %s\n",ship.getMMSI(), shipN.getMMSI());

                //System.out.println(ship1.getShipPositonData().get(1));
                //System.out.println(ship1.getShipPositonData().get(2));
                //System.out.println(ship1.getShipPositonData().get(ship1.getShipPositonData().size()-1));

            }


             */

            //List<ShipPositonData> ship1Data = elems.getShipPositonData();

            //System.out.println(ships.ge);
        }



}
