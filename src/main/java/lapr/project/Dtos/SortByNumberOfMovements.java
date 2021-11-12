package lapr.project.Dtos;

import java.util.Comparator;

public class SortByNumberOfMovements implements Comparator<ShipsMovementDto> {

    @Override
    public int compare(ShipsMovementDto nm1, ShipsMovementDto nm2) {
        return nm1.getNumberOfMovements().compareTo(nm2.getNumberOfMovements());
    }
}
