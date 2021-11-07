package lapr.project.data;

import lapr.project.model.Ships.Generator;
import lapr.project.model.Ships.IDB.IGeneratorDB;

import java.util.List;

public class GeneratorDB implements IGeneratorDB {

    @Override
    public boolean addGenerator(int ship, Generator gen) {
        return false;
    }

    @Override
    public Integer getAvailableGeneratorId() {
        return null;
    }


    @Override
    public Generator getGeneratorData(int id) {
        return null;
    }

    @Override
    public List<Generator> getAllGenerator() {
        return null;
    }

    @Override
    public List<Generator> getAllGeneratorsFromShip(int shipID) {
        return null;
    }
}
