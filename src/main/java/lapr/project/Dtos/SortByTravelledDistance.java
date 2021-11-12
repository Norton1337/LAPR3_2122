package lapr.project.Dtos;

import java.util.Comparator;

public class SortByTravelledDistance implements Comparator<ShipsMovementDto> {


    @Override
    public int compare(ShipsMovementDto td1, ShipsMovementDto td2) {
        return td1.getTravelledDistance().compareTo(td2.getTravelledDistance());
    }
}
