package lapr.project.model.borders.idb;

import lapr.project.model.borders.Borders;

import java.util.List;

public interface IBordersDB {

    List<Borders> getAllBorders();

    Borders getBorderById(String id);

    boolean addBorder(Borders elem);
}
