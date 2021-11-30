package lapr.project.data.mocks;

import lapr.project.model.vehicle.Vehicles;
import lapr.project.model.vehicle.idb.IVehicle;

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
            if(elems.getVehiclesId().equals(id)){
                return elems;
            }
        }

        return null;
    }

    @Override
    public boolean addVehicle(Vehicles elem) {
        elem.setVehiclesId(randomUUID());
        vehicles.add(elem);
        return false;
    }


    @Override
    public boolean removeVehicle(String id) {
        Vehicles vehicleToDelete = getVehicle(id);
        return vehicles.remove(vehicleToDelete);
    }
}
