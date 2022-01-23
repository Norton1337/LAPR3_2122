package lapr.project.controller.ModelControllers;

import lapr.project.controller.model_controllers.*;
import lapr.project.data.mocks.*;
import lapr.project.model.containers.Container;
import lapr.project.ui.CargoManifestUI;
import lapr.project.ui.ClientUI;
import lapr.project.ui.ContainerUI;
import lapr.project.ui.UsersUI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static lapr.project.utils.Utils.printList;
import static lapr.project.utils.Utils.readFromProp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContainerControllerTest {
    //DB
    ContainerDBMock containerDBMock = new ContainerDBMock();
    VehiclesDBMock vehiclesDBMock = new VehiclesDBMock();
    CargoManifestDBMock cargoManifestDBMock = new CargoManifestDBMock();
    OperationDBMock operationDBMock = new OperationDBMock();
    CountryDBMock countryDBMock = new CountryDBMock();
    LocalsDBMock localsDBMock = new LocalsDBMock();
    ShipDBMock shipDBMock = new ShipDBMock();
    TrucksDBMock trucksDBMock = new TrucksDBMock();
    GeneratorDBMock generatorDBMock = new GeneratorDBMock();
    LeasingDBMock leasingDBMock = new LeasingDBMock();
    UsersDBMock usersDBMock = new UsersDBMock();
    ClientDBMock clientDBMock = new ClientDBMock();



    ClientController clientController = new ClientController(clientDBMock,usersDBMock);
    UserController userController = new UserController(usersDBMock);

    ClientUI clientUI = new ClientUI(clientController);
    UsersUI usersUI = new UsersUI(userController);


    LocalsController localsController = new LocalsController(countryDBMock,localsDBMock);
    ShipController shipController = new ShipController(shipDBMock,generatorDBMock);
    CargoManifestController cargoManifestController = new CargoManifestController(vehiclesDBMock,
            cargoManifestDBMock,operationDBMock, shipController);
    VehiclesController vehiclesController = new VehiclesController(vehiclesDBMock,shipDBMock,trucksDBMock);
    OperationController operationController = new OperationController(operationDBMock,containerDBMock,
            localsController,cargoManifestController,shipController,vehiclesController);
    ContainerController containerController = new ContainerController(containerDBMock,cargoManifestController
            ,operationController,vehiclesController,localsController, clientDBMock, leasingDBMock, usersDBMock);

    //LEITURA DE FICHEIRO
    ContainerUI containerUI = new ContainerUI(containerController);
    CargoManifestUI cargoManifestUI = new CargoManifestUI(cargoManifestController);
    @BeforeEach
    void setup() {
        cargoManifestUI.importCargoManifest("Docs/Input/cargoManifest.csv");
        containerUI.importContainers("Docs/Input/container.csv");
        usersUI.importUsers("Docs/Input/users.csv");
        clientUI.importClients("Docs/Input/clients.csv");
    }


    @Test
    void addContainerTest(){
        Container newContainer = new Container(2223,5,"ASD",500,
                500,1000,300,"Certificates","Repair",
                "Refrigerated");
        boolean check = containerController.addContainer(newContainer);

        assertTrue(containerDBMock.getAllContainers().contains(newContainer));
    }

    @Test
    void getAllContainersTest(){
        Container newContainer = new Container(2223,5,"ASD",500,
                500,1000,300,"Certificates","Repair",
                "Refrigerated");
        containerController.addContainer(newContainer);
        assertEquals(containerDBMock.getAllContainers().size(),containerController.getAllContainers().size());
    }

    @Test
    void findContainerByNumberTest(){
        Container newContainer = new Container(2223,5,"ASD",500,
                500,1000,300,"Certificates","Repair",
                "Refrigerated");
        containerController.addContainer(newContainer);

        Container result = containerController.findContainerByNumber("2223");
        assertEquals(2223,result.getContainerNumber());
    }

    @Test
    void test() {

        if (Objects.equals(readFromProp("debug", "src/main/resources/application.properties"), "1")){
            printList(containerDBMock.getAllContainers());
        }

    }

    //TODO implement test
    @Test
    void whereIsContainerTest(){
        assertNull(containerController.whereIsContainer("2380"));
    }

    @Test
    void containerRouteTest(){
        List<String> list = new ArrayList<>();
        list.add("Container is not valid");
        assertEquals(list, containerController.containerRoute("client123", "2380"));
    }

}
