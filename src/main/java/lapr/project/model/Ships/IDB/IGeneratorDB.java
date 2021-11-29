package lapr.project.model.ships.idb;

import java.util.List;

import lapr.project.model.ships.Generator;

public interface IGeneratorDB {

    boolean addGenerator(String shipId, Generator gen);


    Generator getGeneratorData(String id);

    List<Generator> getAllGenerator();

    List<Generator> getAllGeneratorsFromShip(String shipID);
}
