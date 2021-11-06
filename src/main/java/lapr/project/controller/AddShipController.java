package lapr.project.controller;

import lapr.project.model.Ships.Generator;
import lapr.project.model.Ships.IDB.IGeneratorDB;
import lapr.project.model.Ships.IDB.IShipsDB;
import lapr.project.model.Ships.Ship;

public class AddShipController {

    private final IShipsDB shipDB;
    private final IGeneratorDB generatorDB;

    public AddShipController(IShipsDB shipDB, IGeneratorDB generatorDB) {
        this.shipDB = shipDB;
        this.generatorDB =generatorDB;
    }

    public boolean addShip(Ship newShip, Generator gen) {
        generatorDB.addGenerator(newShip.getId(), gen);
        shipDB.addShip(newShip);

        return true;
    }
}
