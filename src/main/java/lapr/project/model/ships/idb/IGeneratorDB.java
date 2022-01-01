package lapr.project.model.ships.idb;

import lapr.project.model.ships.Generator;

import java.util.List;

public interface IGeneratorDB {

    boolean addGenerator(String shipId, Generator gen);


    Generator getGeneratorData(String id);

    List<Generator> getAllGenerator();

    List<Generator> getAllGeneratorsFromShip(String shipID);
}
