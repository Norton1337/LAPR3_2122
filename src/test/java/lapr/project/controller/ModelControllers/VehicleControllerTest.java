package lapr.project.controller.ModelControllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lapr.project.controller.model_controllers.VehiclesController;
import lapr.project.data.mocks.ShipDBMock;
import lapr.project.data.mocks.TrucksDBMock;
import lapr.project.data.mocks.VehiclesDBMock;
import lapr.project.model.ships.Ship;
import lapr.project.model.truck.Truck;
import lapr.project.model.vehicle.Vehicles;

class VehicleControllerTest {
    VehiclesDBMock vehiclesDBMock = new VehiclesDBMock();
    ShipDBMock shipDBMock = new ShipDBMock();
    TrucksDBMock trucksDBMock = new TrucksDBMock();


    VehiclesController vehiclesController = new VehiclesController(vehiclesDBMock, shipDBMock, trucksDBMock);

    @Test
    void addGetAllShipsTest(){
        Vehicles vehicle = new Vehicles("vehicle", "vehicle_recon");
        vehicle.setId("id");
        
        Ship ship = new Ship("211331640", "shipName", "IMO", "callSign", 5, 3, 4, 5.0, 6.0);
        assertTrue(vehiclesController.addShip(vehicle, ship)); 
        assertEquals(1, vehiclesController.getAllShips().size());
        Vehicles vehicle2 = new Vehicles("truck", "vehicle_recon2");
        vehicle.setId("id");
        Truck truck = new Truck("plate");
        truck.setTruckId("truckId");

        assertTrue(vehiclesController.addTruck(vehicle2, truck));
        assertEquals(1, vehiclesController.getAllTrucks().size());
    }

}
