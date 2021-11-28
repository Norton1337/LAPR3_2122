package lapr.project.controller.ModelControllers;

import lapr.project.model.Ships.Generator;
import lapr.project.model.Ships.IDB.IGeneratorDB;
import lapr.project.model.Ships.IDB.IShipsDB;
import lapr.project.model.Ships.Ship;

import java.util.ArrayList;
import java.util.List;

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
