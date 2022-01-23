package lapr.project.data.database;

import lapr.project.model.ships.Generator;
import lapr.project.model.ships.idb.IGeneratorDB;

import java.util.ArrayList;
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
        return new ArrayList<>();
    }

    @Override
    public List<Generator> getAllGeneratorsFromShip(String shipID) {
        return new ArrayList<>();
    }
}
