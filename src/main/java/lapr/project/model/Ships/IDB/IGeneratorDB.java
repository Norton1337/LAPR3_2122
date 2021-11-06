package lapr.project.model.Ships.IDB;

import lapr.project.model.Ships.Generator;

import java.util.List;

public interface IGeneratorDB {

    boolean addGenerator(int ship, Generator gen);

    Generator getGeneratorData(int id);

    List<Generator> getAllGenerator();

    List<Generator> getAllGeneratorsFromShip(int shipID);
}
