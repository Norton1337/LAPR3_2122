package lapr.project.data.mocks;

import lapr.project.model.Ships.Generator;
import lapr.project.model.Ships.IDB.IGeneratorDB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static lapr.project.utils.Utils.randomUUID;

public class GeneratorDBMock implements IGeneratorDB {

    List<Generator> generators = new LinkedList<>();


    @Override
    public boolean addGenerator(String shipID, Generator gen) {
        gen.setId(randomUUID());
        gen.setShipId(shipID);

        return generators.add(gen);
    }



    @Override
    public Generator getGeneratorData(String id) {
        for(Generator elems : generators){
            if(elems.getId().equals(id)){
                return elems;
            }
        }
        return null;
    }

    @Override
    public List<Generator> getAllGenerator() {
        return new LinkedList<>(generators);
    }

    @Override
    public List<Generator> getAllGeneratorsFromShip(String shipID) {
        List<Generator> allGen = new ArrayList<>();

        for(Generator elems : generators){
            if(elems.getShipId().equals(shipID)){
                allGen.add(elems);
            }
        }

        if(allGen.isEmpty()){
            return new ArrayList<>();
        }

        return allGen;
    }
}
