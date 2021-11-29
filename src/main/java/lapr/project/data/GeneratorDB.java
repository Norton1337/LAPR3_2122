package lapr.project.data;

import java.util.List;

import lapr.project.model.ships.Generator;
import lapr.project.model.ships.idb.IGeneratorDB;

public class GeneratorDB implements IGeneratorDB {


    @Override
    public boolean addGenerator(String shipId, Generator gen) {
        return false;
    }


    @Override
    public Generator getGeneratorData(String id) {
        return null;
    }

    @Override
    public List<Generator> getAllGenerator() {
        return null;
    }

    @Override
    public List<Generator> getAllGeneratorsFromShip(String shipID) {
        return null;
    }
}
