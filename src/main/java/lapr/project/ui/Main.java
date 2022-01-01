package lapr.project.ui;

import lapr.project.controller.DataToBstController;
import lapr.project.controller.DataToKDTreeController;
import lapr.project.controller.ListAllShipsInfoController;
import lapr.project.controller.model_controllers.*;
import lapr.project.data.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

class Main {

        public static void main(String[] args) throws IOException, SQLException, ParseException {

                // DBMock
                /*ShipDBMock shipDBMock = new ShipDBMock();
                GeneratorDBMock generatorDBMock = new GeneratorDBMock();
                ShipPositionDataDBMock shipPositionDataDBMock = new ShipPositionDataDBMock();
                PortsAndWarehousesDBMock portsAndWarehousesDBMock = new PortsAndWarehousesDBMock();
                CountryDBMock countryDBMock = new CountryDBMock();*/

                // DB
                DBController dbController = new DBController();
                dbController.createdb();
                VehiclesDB vehiclesDB = new VehiclesDB();
                TruckDB truckDB = new TruckDB();
                ShipDB shipDB = new ShipDB();
                GeneratorDB generatorDB = new GeneratorDB();
                ShipPositionDataDB shipPositionDataDB = new ShipPositionDataDB();
                LocalsDB portsAndWarehousesDB = new LocalsDB();
                CountryDB countryDB = new CountryDB();

                // CONTROLLERS DO MODEL
                ShipController shipController = new ShipController(shipDB, generatorDB);
                VehiclesController vehiclesController = new VehiclesController(vehiclesDB,shipDB,truckDB);
                ShipPositionDataController shipPositionDataController = new ShipPositionDataController(shipDB,
                                shipPositionDataDB);
                GeneratorController generatorController = new GeneratorController(shipDB, generatorDB);
                LocalsController portsAndWarehousesController = new LocalsController(
                                countryDB,
                                portsAndWarehousesDB);

                // CONTROLLERS
                DataToBstController dataToBstController = new DataToBstController();
                ListAllShipsInfoController listAllShipsInfoController = new ListAllShipsInfoController();
                DataToKDTreeController dataToKDTreeController = new DataToKDTreeController();

                 //LEITURA DE FICHEIRO
                 ShipUI shipUI = new ShipUI(shipController, shipPositionDataController,
                 generatorController,vehiclesController);
                 shipUI.importShips("Docs/Input/bships.csv");
                /*
                 *
                 * portsAndWarehousesController.addPortAndWarehouse(new
                 * PortsAndWarehouses("Europe","Cyprus",10136,
                 * "Larnaca","34.91666667,33.65"));
                 * portsAndWarehousesController.addPortAndWarehouse(new
                 * PortsAndWarehouses("Europe","Malta",10138,
                 * "Marsaxlokk","35.84194,14.54306"));
                 * portsAndWarehousesController.addPortAndWarehouse(new
                 * PortsAndWarehouses("Europe","Denmark",10358,
                 * "Aarhus","56.15,10.21666667"));
                 *
                 *
                 * printList(portsAndWarehousesController.getAllPortsAndWharehouse());
                 *
                 *
                 * //dataToBstController.transformBeforeBST(shipController.getAllShips(),
                 * shipPositionDataController.getShipData());
                 * //dataToBstController.populateBST();
                 *
                 * //BST t = dataToBstController.getShipBst();
                 * // System.out.println(t);
                 * /*
                 * System.out.println(shipSummaryController.toString());
                 *
                 *
                 * ShipAndData dataByMMSI = dataToBstController.getShipDetails("636015178");
                 * ShipAndData dataByIMO = dataToBstController.getShipDetails("IMO9601833");
                 * ShipAndData dataByCallSign = dataToBstController.getShipDetails("A8ZC7");
                 * System.out.println(dataByMMSI.toString());
                 * System.out.println(dataByIMO.toString());
                 * System.out.println(dataByCallSign.toString());
                 *
                 */

                /*
                 * List<ShipAndData> andDataList = new ArrayList<>();
                 *
                 * for(Object elems : t.inOrder()){
                 * andDataList.add((ShipAndData) elems);
                 * }
                 *
                 *
                 * long startTime = System.currentTimeMillis();
                 * Utils.printList(listAllShipsInfoController.pairShips(andDataList));
                 * long stopTime = System.currentTimeMillis();
                 * System.out.println(stopTime - startTime);
                 *
                 *
                 *
                 *
                 * //printList(listAllShipsInfoController.shipLog(andDataList));
                 * //System.out.println(dataToBstController.populateBST();
                 *
                 * /*
                 * List<ShipAndData> shipList = new ArrayList<>();
                 *
                 * for (int i = 0; i < shipController.getAllShips().size(); i++) {
                 * shipList.add(dataToBstController.getShipAndDataByMMSI(shipController.
                 * getAllShips().get(i).getMMSI()));
                 * }
                 */
                /*
                 * ShipAndData dataByMMSI = dataToBstController.getShipDetails("636015178");
                 *
                 * ShipSummaryController summaryController = new
                 * ShipSummaryController(dataByMMSI);
                 * ShipSummary shipSummary = summaryController.getShipSummary();
                 * System.out.println(summaryController.toString());
                 */
                // MostTravelledShips mts = new MostTravelledShips();
                // TopShips ts = mts.getTopNShips(shipList, 5);
                //

                /*
                 * PortsAndWarehousesUI portsAndWarehousesUI = new
                 * PortsAndWarehousesUI(portsAndWarehousesController);
                 * portsAndWarehousesUI.importPorts("Docs/Input/sports.csv");
                 * 
                 * LinkedList<Locals> portsAndWarehouses =
                 * portsAndWarehousesController.getAllPortsAndWharehouse();
                 * dataToKDTreeController.populateTree(portsAndWarehouses);
                 * // System.out.println(dataToKDTreeController.getPortsNodes());
                 * //dataToKDTreeController.getPortsTree().print();
                 * //System.out.println("Height: " +
                 * dataToKDTreeController.getPortsTree().height());
                 * //System.out.println("Size: " +
                 * dataToKDTreeController.getPortsNodes().size());
                 * //System.out.println("-------");
                 * //System.out.println(dataToKDTreeController.getPortsTree().balanceFactor());
                 */

                /*
                 * DBController controller = new DBController();
                 * controller.createdb();
                 * List<String> ls1 = controller.a_cm(2021);
                 * ListIterator<String> ls12 = ls1.listIterator();
                 * while (ls12.hasNext()) {
                 * System.out.println(ls12.next());
                 * }
                 */

                // System.out.println(dataToKDTreeController.getPortsTree().toString());

                /*
                 * Point2D coord = new Point2D.Double(-32.06666667,-52.06666667);
                 * Node x = dataToKDTreeController.getPortsTree().find(coord);
                 * System.out.println(x);
                 * System.out.println(dataToKDTreeController.getPortsTree());
                 *
                 */
        }
}
