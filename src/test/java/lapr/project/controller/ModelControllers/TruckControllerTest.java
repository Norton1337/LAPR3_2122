package lapr.project.controller.ModelControllers;

import lapr.project.controller.model_controllers.TruckController;
import lapr.project.controller.model_controllers.VehiclesController;
import lapr.project.data.mocks.ShipDBMock;
import lapr.project.data.mocks.TrucksDBMock;
import lapr.project.data.mocks.VehiclesDBMock;
import lapr.project.model.truck.Truck;
import lapr.project.model.users.Users;
import lapr.project.ui.TruckUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TruckControllerTest {
    //DB
    VehiclesDBMock vehiclesDBMock = new VehiclesDBMock();
    TrucksDBMock trucksDBMock = new TrucksDBMock();
    ShipDBMock shipDBMock = new ShipDBMock();

    //Controllers
    TruckController truckController = new TruckController(trucksDBMock);
    VehiclesController vehiclesController = new VehiclesController(vehiclesDBMock,shipDBMock,trucksDBMock);
    //Leitura de Ficheiro
    TruckUI truckUI = new TruckUI(vehiclesController);

    Truck newTruck;

    @BeforeEach
    void setup(){
        truckUI.importTrucks("Docs/Input/truck.csv");
    }

    @Test
    void truckControllerTest(){
        TruckController newTruckController = new TruckController(trucksDBMock);
    }

    @Test
    void addTruckTest(){
        Truck newTruck = new Truck("88-28-VR");
        truckController.addTruck(newTruck);

        assertTrue(trucksDBMock.getAllTrucks().contains(newTruck));
    }

    @Test
    void getAllTrucksTest(){
        Truck newTruck = new Truck("88-28-VR");
        truckController.addTruck(newTruck);

        assertEquals(trucksDBMock.getAllTrucks().size(),truckController.getAllTrucks().size());
    }

    @Test
    void findTruckByPlateTest(){
        Truck newTruck = new Truck("88-28-VR");
        truckController.addTruck(newTruck);

        Truck result = truckController.findTruckByPlate("88-28-VR");

        assertEquals(newTruck.getTruckId(),result.getTruckId());
    }

}
