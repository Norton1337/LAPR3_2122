package lapr.project.controller.helper_classes;

import java.util.Comparator;

import lapr.project.dtos.ShipsMovementDto;

public class SortByNumberOfMovements implements Comparator<ShipsMovementDto> {

    @Override
    public int compare(ShipsMovementDto nm1, ShipsMovementDto nm2) {
        return nm1.getNumberOfMovements().compareTo(nm2.getNumberOfMovements());
    }
}
