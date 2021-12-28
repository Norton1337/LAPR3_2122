package lapr.project.data.mocks;

import lapr.project.model.borders.Borders;
import lapr.project.model.borders.idb.IBordersDB;
import lapr.project.model.ships.Ship;

import java.util.LinkedList;
import java.util.List;

import static lapr.project.utils.Utils.randomUUID;

public class BordersDBMock implements IBordersDB {

    private final List<Borders> borders = new LinkedList<>();


    @Override
    public List<Borders> getAllBorders() {
        return new LinkedList<>(borders);
    }


    @Override
    public Borders getBorderById(String id) {
        for(Borders elems : getAllBorders()){
            if(elems.getId().equals(id)){
                return elems;
            }
        }

        return null;
    }

    @Override
    public boolean addBorder(Borders elem) {
        elem.setId(randomUUID());
        return borders.add(elem);
    }
}
