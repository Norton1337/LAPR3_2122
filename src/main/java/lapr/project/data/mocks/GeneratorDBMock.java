package lapr.project.data.mocks;

import lapr.project.model.Ships.Generator;
import lapr.project.model.Ships.IDB.IGeneratorDB;

import java.util.LinkedList;
import java.util.List;

public class GeneratorDBMock implements IGeneratorDB {

    List<Generator> generators = new LinkedList<>();


    @Override
    public boolean addGenerator(int shipID, Generator gen) {
        gen.setShipId(shipID);
        return generators.add(gen);
    }

    @Override
    public Generator getGeneratorData(int id) {
        for(Generator elems : generators){
            if(elems.getId() == id){
                return elems;
            }
        }
        return null;
    }

    @Override
    public List<Generator> getAllGenerator() {
        return new LinkedList<>(generators);
    }

    @Override
    public List<Generator> getAllGeneratorsFromShip(int shipID) {
        return null;
    }
}
