package lapr.project.controller.HelperClasses;

import lapr.project.Dtos.ShipPairsDTO;
import lapr.project.Dtos.ShipsMovementDto;

import java.util.Comparator;

public class SortByTravelledDistancePairs implements Comparator<ShipPairsDTO> {

    @Override
    public int compare(ShipPairsDTO o1, ShipPairsDTO o2) {
        double ship1Diff = Math.abs(Double.parseDouble(o1.getShip1Traveldistance())-Double.parseDouble(o1.getShip2Trabeldistance()));
        double ship2Diff = Math.abs(Double.parseDouble(o2.getShip1Traveldistance())-Double.parseDouble(o2.getShip2Trabeldistance()));
        return String.valueOf(ship2Diff).compareTo(String.valueOf(ship1Diff));
    }

}





