package lapr.project.data;

import lapr.project.model.Ships.Generator;
import lapr.project.model.Ships.IDB.IGeneratorDB;

import java.util.List;

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
