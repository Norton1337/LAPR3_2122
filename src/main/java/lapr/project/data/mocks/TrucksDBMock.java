package lapr.project.data.mocks;

import lapr.project.model.truck.Truck;
import lapr.project.model.truck.idb.ITruck;

import java.util.LinkedList;
import java.util.List;

import static lapr.project.utils.Utils.randomUUID;

public class TrucksDBMock implements ITruck {

    private final List<Truck> trucks = new LinkedList<>();

    @Override
    public Truck getTruck(String id) {
        for (Truck elems : trucks) {
            if (elems.getTruckId().equals(id)) {
                return elems;
            }
        }

        return null;
    }

    @Override
    public List<Truck> getAllTrucks() {
        return this.trucks;
    }


    @Override
    public boolean addTruck(Truck elem) {
        return trucks.add(elem);
    }

    @Override
    public boolean removeTruck(String id) {
        Truck truckToDelete = getTruck(id);
        return trucks.remove(truckToDelete);
    }

    @Override
    public Truck getTruckByPlate(String plate) {
        return null;
    }
}
