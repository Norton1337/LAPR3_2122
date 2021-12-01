package lapr.project.data.mocks;

import lapr.project.model.vehicle.Vehicles;
import lapr.project.model.vehicle.idb.IVehicle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static lapr.project.utils.Utils.randomUUID;


public class VehiclesDBMock implements IVehicle {

    private final List<Vehicles> vehicles = new LinkedList<>();


    @Override
    public List<Vehicles> getAllVehicles() {
        return new LinkedList<>(vehicles);
    }

    @Override
    public Vehicles getVehicle(String id) {
        for(Vehicles elems : vehicles){
            if(elems.getId().equals(id)){
                return elems;
            }
        }

        return null;
    }

    @Override
    public Vehicles addVehicle(Vehicles elem) {
        elem.setId(randomUUID());
        vehicles.add(elem);


        return elem;
    }


    @Override
    public boolean removeVehicle(String id) {
        Vehicles vehicleToDelete = getVehicle(id);
        return vehicles.remove(vehicleToDelete);
    }

    @Override
    public List<Vehicles> getAllTrucks() {
        List<Vehicles> allTrucks = new ArrayList<>();

        for(Vehicles elems : vehicles){
            if(elems.getType().contains("truck")){
                allTrucks.add(elems);
            }
        }

        return allTrucks;
    }

    @Override
    public List<Vehicles> getAllShips() {
        List<Vehicles> allShips = new ArrayList<>();

        for(Vehicles elems : vehicles){
            if(elems.getType().contains("ship")){
                allShips.add(elems);
            }
        }

        return allShips;
    }
}
