package lapr.project.controller.ModelControllers;

import lapr.project.model.Ships.Generator;
import lapr.project.model.Ships.IDB.IGeneratorDB;
import lapr.project.model.Ships.IDB.IShipsDB;
import lapr.project.model.Ships.Ship;

import java.util.List;

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

    public List<Generator> getAllGenerator(){
        return generatorDB.getAllGenerator();
    }
}
