package lapr.project.controller;

import lapr.project.Dtos.ShipsMovementDto;
import lapr.project.controller.HelperClasses.SortByNumberOfMovements;
import lapr.project.controller.HelperClasses.SortByTravelledDistance;
import lapr.project.model.HelperClasses.ShipAndData;
import lapr.project.model.ShipPositionData.ShipPositonData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lapr.project.utils.Utils.orderedByTime;

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


}
