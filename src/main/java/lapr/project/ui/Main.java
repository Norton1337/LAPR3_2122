package lapr.project.ui;

import lapr.project.controller.DataToBstController;
import lapr.project.controller.DataToKDTreeController;
import lapr.project.controller.ListAllShipsInfoController;
import lapr.project.controller.ToMatrixController;
import lapr.project.controller.model_controllers.*;
import lapr.project.data.mocks.*;
import lapr.project.model.locals.Locals;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;


import static lapr.project.utils.Utils.printList;
import static lapr.project.utils.Utils.printMap;;

class Main {

        public static void main(String[] args) throws IOException, SQLException, ParseException {

                // DB
                VehiclesDBMock vehiclesDBMock = new VehiclesDBMock();
                TrucksDBMock trucksDBMock = new TrucksDBMock();
                ShipDBMock shipDBMock = new ShipDBMock();
                GeneratorDBMock generatorDBMock = new GeneratorDBMock();
                ShipPositionDataDBMock shipPositionDataDBMock = new ShipPositionDataDBMock();
                LocalsDBMock localsDBMock = new LocalsDBMock();
                CountryDBMock countryDBMock = new CountryDBMock();
                BordersDBMock bordersDBMock = new BordersDBMock();
                SeadistDBMock seadistDBMock = new SeadistDBMock();
                OperationDBMock operationDBMock = new OperationDBMock();
                CargoManifestDBMock cargoManifestDBMock = new CargoManifestDBMock();
                ContainerDBMock containerDBMock = new ContainerDBMock();
                UsersDBMock usersDBMock = new UsersDBMock();
                ClientDBMock clientDBMock = new ClientDBMock();
                LeasingDBMock leasingDBMock = new LeasingDBMock();

                // CONTROLLERS DO MODEL
                VehiclesController vehiclesController = new VehiclesController(vehiclesDBMock, shipDBMock,
                                trucksDBMock);
                ShipController shipController = new ShipController(shipDBMock, generatorDBMock);
                ShipPositionDataController shipPositionDataController = new ShipPositionDataController(shipDBMock,
                                shipPositionDataDBMock);
                GeneratorController generatorController = new GeneratorController(shipDBMock, generatorDBMock);
                LocalsController localsController = new LocalsController(countryDBMock, localsDBMock);
                CargoManifestController cargoManifestController = new CargoManifestController(vehiclesDBMock,
                                cargoManifestDBMock, operationDBMock, shipController);
                OperationController operationController = new OperationController(operationDBMock, containerDBMock,
                                localsController, cargoManifestController, shipController, vehiclesController);
                ContainerController containerController = new ContainerController(containerDBMock,
                                cargoManifestController,
                                operationController, vehiclesController, localsController, clientDBMock, leasingDBMock,
                                usersDBMock);
                TruckController truckController = new TruckController(trucksDBMock);
                UserController userController = new UserController(usersDBMock);
                ClientController clientController = new ClientController(clientDBMock, usersDBMock);
                LeasingController leasingController = new LeasingController(leasingDBMock, containerDBMock,
                                clientDBMock,
                                usersDBMock, clientController);

                // CONTROLLERS
                DataToBstController dataToBstController = new DataToBstController();
                ListAllShipsInfoController listAllShipsInfoController = new ListAllShipsInfoController();
                DataToKDTreeController dataToKDTreeController = new DataToKDTreeController();
                CountryController countryController = new CountryController(countryDBMock, bordersDBMock, localsDBMock);
                SeadistController seadistController = new SeadistController(localsDBMock, seadistDBMock);
                ToMatrixController matrixController = new ToMatrixController(localsDBMock, seadistDBMock, countryDBMock,
                                bordersDBMock);

                // LEITURA DE FICHEIRO
                ShipUI shipUI = new ShipUI(shipController, shipPositionDataController, generatorController,
                                vehiclesController);
                PortsAndWarehousesUI portsAndWarehousesUI = new PortsAndWarehousesUI(localsController);
                CountryUI countryUI = new CountryUI(countryController);
                SeadistUI seadistUI = new SeadistUI(seadistController);
                ContainerUI containerUI = new ContainerUI(containerController);
                WarehouseUI warehouseUI = new WarehouseUI(localsController);
                CargoManifestUI cargoManifestUI = new CargoManifestUI(cargoManifestController);
                OperationsUI operationsUI = new OperationsUI(operationController);
                TruckUI truckUI = new TruckUI(vehiclesController);
                UsersUI usersUI = new UsersUI(userController);
                ClientUI clientUI = new ClientUI(clientController);
                LeasingUI leasingUI = new LeasingUI(leasingController);

                // SETUP
                countryUI.importCountriesAndBorders("Docs/Input/countries.csv", "Docs/Input/borders.csv");

                truckUI.importTrucks("Docs/Input/truck.csv");
                shipUI.importShips("Docs/Input/bships.csv");
                dataToBstController.transformBeforeBST(shipController.getAllShips(),
                                shipPositionDataController.getShipData());
                dataToBstController.populateBST();

                portsAndWarehousesUI.importPorts("Docs/Input/bports.csv");

                LinkedList<Locals> portsAndWarehouses = localsController.getAllLocals();
                dataToKDTreeController.populateTree(portsAndWarehouses);

                seadistUI.importSeadist("Docs/Input/seadists.csv");

                containerUI.importContainers("Docs/Input/container.csv");
                warehouseUI.importWarehouses("Docs/Input/warehouses.csv");
                cargoManifestUI.importCargoManifest("Docs/Input/cargoManifest.csv");
                operationsUI.importOperations("Docs/Input/operations.csv");

                usersUI.importUsers("Docs/Input/users.csv");
                clientUI.importClients("Docs/Input/clients.csv");
                leasingUI.importLeasingCon("Docs/Input/leasing.csv");

                // printList(operationDBMock.allOperations());
                System.out.println("\n\n\n\n\n\n");
                // System.out.println(cargoManifestController.capacity_rate("228339600","Buk3h",shipController));

                // printMap(operationController.getOccupancyRate_and_ContainersLeavingNextMonth(246265));
                // printList(cargoManifestController.free_ships(shipController));
                printList(cargoManifestController.containersToLoadAndOffload("212180000","Unload"));
                // printList(containerController.containerRoute("client123","2345"));
                // printMap(operationController.getOccupancyRate_and_ContainersLeavingNextMonth(246265));
                // printList(operationController.port_map(10358, "2022-01-01 00:00:00"));
                //printMap(cargoManifestController.idleTimeShips());
                // printList(localsController.getAllWarehouses());
                // printList(userController.getAllUsers());
                // printList(containerController.getAllContainers());
                // System.out.println("\n\n\n\n\n\n");
                // printList(leasingController.getAllLeasing());

        }
}
