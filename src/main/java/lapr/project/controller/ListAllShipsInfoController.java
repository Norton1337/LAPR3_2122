package lapr.project.controller;

import lapr.project.controller.helper_classes.*;
import lapr.project.dtos.ShipPairsDTO;
import lapr.project.dtos.ShipsMovementDto;
import lapr.project.model.helper_classes.ShipAndData;
import lapr.project.model.ship_position_data.ShipPositonData;
import lapr.project.model.ships.Ship;

import java.util.*;

import static lapr.project.utils.Utils.stripC;

public class ListAllShipsInfoController {

    private final MostTravelledShipsController mostTravelledShips = new MostTravelledShipsController();


    /**
     * Creates a list for all the ships with their details, ordered by
     * travelled distance in descending order and by number of movements
     * in ascending order.
     * The method receives a list with all the ships and for each one
     * calculates their delta distance, travelled distance and number of movements.
     *
     * @param shipsAndDataList list with all ships
     * @return a list for all the ships describing their MMSI, number o movements,
     * distance travelled and delta distance.
     */
    public List<ShipsMovementDto> shipLog(List<ShipAndData> shipsAndDataList) {

        List<ShipsMovementDto> shipsMovementDtos = new ArrayList<>();

        for (ShipAndData ships : shipsAndDataList) {

            List<ShipPositonData> newArrayList = ships.getShipPositonData();

            String MMSI = ships.getShip().getMMSI();
            String numberOfMovements = String.valueOf(ships.getShipPositonData().size());
            String travelledDistance = String.valueOf(mostTravelledShips.getTotalPerShip(newArrayList));
            String deltaDistance = String.valueOf(mostTravelledShips.getDeltaDistance(newArrayList));

            shipsMovementDtos.add(new ShipsMovementDto(MMSI, travelledDistance, deltaDistance, numberOfMovements));


        }

        Collections.sort(shipsMovementDtos, Collections.reverseOrder(new SortByTravelledDistance()).thenComparing(new SortByNumberOfMovements()));
        return shipsMovementDtos;
    }

    public List<ShipPairsDTO> pairShips(List<ShipAndData> shipsAndDataList) {
        KMTravelledCalculator calculator = new KMTravelledCalculator();
        MostTravelledShipsController mostTravelledShipsController = new MostTravelledShipsController();

        List<ShipPairsDTO> listOfShipsLessThan5Kms = new ArrayList<>();


        Map<Ship, List<ShipPositonData>> shipsShipPositonData = new LinkedHashMap<>();

        for (ShipAndData elems : shipsAndDataList) {
            shipsShipPositonData.put(elems.getShip(), elems.getShipPositonData());
        }

        for (Map.Entry<Ship, List<ShipPositonData>> entry : shipsShipPositonData.entrySet()) {
            Double distanceTravelsShip1 = mostTravelledShipsController.getTotalPerShip(entry.getValue());

            for (Map.Entry<Ship, List<ShipPositonData>> entry1 : shipsShipPositonData.entrySet()) {
                int ship1Size = entry.getValue().size() - 1;
                String ship1CordinatesInitial = entry.getValue().get(0).getCoordinates();
                String ship1CordinatesEnd = entry.getValue().get(ship1Size).getCoordinates();

                int ship2Size = entry1.getValue().size() - 1;
                String ship2CordinatesInitial = entry1.getValue().get(0).getCoordinates();
                String ship2CordinatesEnd = entry1.getValue().get(ship2Size).getCoordinates();

                if (!entry.getKey().getMMSI().equals(entry1.getKey().getMMSI())) {
                    double initialDistanceInital = calculator.calculate(stripC(ship1CordinatesInitial)[0], stripC(ship1CordinatesInitial)[1],
                            stripC(ship2CordinatesInitial)[0], stripC(ship2CordinatesInitial)[1]);

                    double endDistanceInital = calculator.calculate(stripC(ship1CordinatesEnd)[0], stripC(ship1CordinatesEnd)[1],
                            stripC(ship2CordinatesEnd)[0], stripC(ship2CordinatesEnd)[1]);

                    double initialEnd = calculator.calculate(stripC(ship1CordinatesInitial)[0], stripC(ship1CordinatesInitial)[1],
                            stripC(ship2CordinatesEnd)[0], stripC(ship2CordinatesEnd)[1]);

                    double endInitial = calculator.calculate(stripC(ship2CordinatesInitial)[0], stripC(ship2CordinatesInitial)[1],
                            stripC(ship1CordinatesEnd)[0], stripC(ship1CordinatesEnd)[1]);

                    Double distanceTravelsShip2Get = entry1.getKey().getDistanceTravelled();
                    if (distanceTravelsShip2Get == 0) {
                        distanceTravelsShip2Get = mostTravelledShipsController.getTotalPerShip(entry1.getValue());
                        entry1.getKey().setDistanceTravelled(distanceTravelsShip2Get);
                    }


                    if (((initialEnd < 5 || endInitial < 5) || (initialDistanceInital < 5 || endDistanceInital < 5)) &&
                            (distanceTravelsShip1 > 10 && distanceTravelsShip2Get > 10)) {


                        listOfShipsLessThan5Kms.add(new ShipPairsDTO(entry.getKey().getMMSI(), entry1.getKey().getMMSI(),
                                String.valueOf(entry.getValue().size()), String.valueOf(entry1.getValue().size()),
                                String.valueOf(distanceTravelsShip1), String.valueOf(distanceTravelsShip2Get)));
                    }

                }
            }
        }
        listOfShipsLessThan5Kms.sort(new SortByMMSIPairs().thenComparing(new SortByTravelledDistancePairs()));

        return listOfShipsLessThan5Kms;

    }


}
