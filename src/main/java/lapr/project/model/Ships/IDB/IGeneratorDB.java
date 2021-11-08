package lapr.project.model.Ships.IDB;

import lapr.project.model.Ships.Generator;

import java.util.List;

public interface IGeneratorDB {

    boolean addGenerator(String shipId, Generator gen);


    Generator getGeneratorData(String id);

    List<Generator> getAllGenerator();

    List<Generator> getAllGeneratorsFromShip(String shipID);
}
