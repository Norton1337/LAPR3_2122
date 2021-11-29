package lapr.project.controller.model_controllers;

import java.util.List;

import lapr.project.model.ships.Generator;
import lapr.project.model.ships.Ship;
import lapr.project.model.ships.idb.IGeneratorDB;
import lapr.project.model.ships.idb.IShipsDB;

public class GeneratorController {

    private final IShipsDB shipDB;
    private final IGeneratorDB generatorDB;

    public GeneratorController(IShipsDB shipDB, IGeneratorDB generatorDB) {
        this.shipDB = shipDB;
        this.generatorDB = generatorDB;
    }

    public boolean addGenartor(Ship ship, Generator gen){
        generatorDB.addGenerator(ship.getId(), gen);

        return true;
    }

    /*
    public List<Generator> getAllGenerator(){
        return generatorDB.getAllGenerator();
    }
     */


}
