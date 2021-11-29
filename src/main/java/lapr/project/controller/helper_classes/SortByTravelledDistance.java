package lapr.project.controller.helper_classes;

import java.util.Comparator;

import lapr.project.dtos.ShipsMovementDto;

public class SortByTravelledDistance implements Comparator<ShipsMovementDto> {

    @Override
    public int compare(ShipsMovementDto td1, ShipsMovementDto td2) {
        return td1.getTravelledDistance().compareTo(td2.getTravelledDistance());
    }
}





