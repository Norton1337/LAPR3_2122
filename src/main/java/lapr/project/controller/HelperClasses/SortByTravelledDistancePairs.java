package lapr.project.controller.HelperClasses;

import lapr.project.Dtos.ShipPairsDTO;
import lapr.project.Dtos.ShipsMovementDto;

import java.util.Comparator;

public class SortByTravelledDistancePairs implements Comparator<ShipPairsDTO> {

    @Override
    public int compare(ShipPairsDTO o1, ShipPairsDTO o2) {
        return o1.getShip1Traveldistance().compareTo(o2.getShip1Traveldistance());
    }

}





