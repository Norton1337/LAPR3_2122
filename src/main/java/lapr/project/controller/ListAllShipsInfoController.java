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
     *
     * The list is ordered by time to calculate the travelled distance, and
     * then ordered by travelled distance in decreasing order
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
