package lapr.project.controller.model_controllers;

import java.util.ArrayList;
import java.util.List;

import lapr.project.model.ships.Generator;
import lapr.project.model.ships.Ship;
import lapr.project.model.ships.idb.IGeneratorDB;
import lapr.project.model.ships.idb.IShipsDB;

public class ShipController {

    private final IShipsDB shipDB;
    private final IGeneratorDB generatorDB;

    public ShipController(IShipsDB shipDB, IGeneratorDB generatorDB) {
        this.shipDB = shipDB;
        this.generatorDB = generatorDB;
    }

    public boolean addShip(Ship newShip) {
        shipDB.addShip(newShip);

        return true;
    }

    public List<Ship> getAllShips(){
        return shipDB.getAllShips();
    }


    public List<Generator> getAllGeneratorFromShip(String shipId){
        List<Generator> allGeneratorFromShip = new ArrayList<>();

        // for(Generator elems : generatorDB.getAllGenerator()){
        //     if(elems.getShipId().equals(shipId)){
        //         allGeneratorFromShip.add(elems);
        //     }
        // }
        return allGeneratorFromShip;
    }


    public Ship findShipByMMSI(String mmsi){
        return shipDB.getShipByMMSI(mmsi);
    }

}