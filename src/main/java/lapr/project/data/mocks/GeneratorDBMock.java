package lapr.project.data.mocks;

import lapr.project.model.Ships.Generator;
import lapr.project.model.Ships.IDB.IGeneratorDB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GeneratorDBMock implements IGeneratorDB {

    List<Generator> generators = new LinkedList<>();


    @Override
    public boolean addGenerator(int shipID, Generator gen) {
        int availableGeneratorId = getAvailableGeneratorId();
        gen.setId(availableGeneratorId);
        gen.setShipId(shipID);

        return generators.add(gen);
    }

    @Override
    public Integer getAvailableGeneratorId() {
        List<Integer> idList = new ArrayList<>();
        List<Generator> allGenList = getAllGenerator();

        if(allGenList.isEmpty()){
            return 0;
        }

        for(Generator elems : allGenList){
            idList.add(elems.getId());
        }

        return Collections.max(idList)+1;
    }

    @Override
    public Generator getGeneratorData(int id) {
        for(Generator elems : generators){
            if(elems.getId() == id){
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
    public List<Generator> getAllGeneratorsFromShip(int shipID) {
        List<Generator> allGen = new ArrayList<>();

        for(Generator elems : generators){
            if(elems.getShipId() == shipID){
                allGen.add(elems);
            }
        }

        if(allGen.isEmpty()){
            return new ArrayList<>();
        }

        return allGen;
    }
}
