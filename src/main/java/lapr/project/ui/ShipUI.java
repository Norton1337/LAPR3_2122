package lapr.project.ui;

import lapr.project.controller.model_controllers.GeneratorController;
import lapr.project.controller.model_controllers.ShipController;
import lapr.project.controller.model_controllers.ShipPositionDataController;
import lapr.project.controller.model_controllers.VehiclesController;
import lapr.project.model.ship_position_data.ShipPositonData;
import lapr.project.model.ships.Generator;
import lapr.project.model.ships.Ship;
import lapr.project.model.vehicle.Vehicles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lapr.project.utils.Utils.*;

public class ShipUI {

    private ShipController shipController;
    private ShipPositionDataController shipPositionDataController;
    private GeneratorController generatorController;
    private VehiclesController vehiclesController;


    public ShipUI(ShipController shipController, ShipPositionDataController shipPositionDataController,
                  GeneratorController generatorController, VehiclesController vehiclesController) {
        this.shipController = shipController;
        this.shipPositionDataController = shipPositionDataController;
        this.generatorController = generatorController;
        this.vehiclesController = vehiclesController;
    }

    public void importShips(String filePath) {
        List<String> alreadyUsed = new ArrayList<>();
        Ship newShip;
        ShipPositonData shipPositonData;
        Generator shipGenerator;


        try (BufferedReader input = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = input.readLine()) != null) {

                List<String> list = new ArrayList<>(Arrays.asList(line.split(",")));

                //GENERATING OBJ
                if (!alreadyUsed.contains(list.get(0)) && !list.get(0).contains("MMSI")) {

                    /**
                     * Ship Object Created
                     */
                    newShip = new Ship(list.get(0), list.get(7), list.get(8), list.get(9), toInt(list.get(10)),
                            toInt(list.get(11)), toInt(list.get(12)), toDouble(list.get(13)), toDouble(list.get(14)));

                    /**
                     * Generator Object To Be Created
                     */
                    shipGenerator = new Generator(1, 5.0);

                    boolean resultShip = vehiclesController.addShip(new Vehicles("Ship"), newShip);
                    boolean resultGen = generatorController.addGenartor(newShip, shipGenerator);

                    if (resultShip && resultGen) {
                        alreadyUsed.add(newShip.getMMSI());
                    }

                }
                if (!list.get(0).contains("MMSI")) {
                    String mmsi = list.get(0);
                    Ship ship = shipController.findShipByMMSI(mmsi);

                    /**
                     * ShipPositonData Object Created
                     */

                    shipPositonData = new ShipPositonData(list.get(1), coordinates(list.get(2), list.get(3)),
                            toDouble(list.get(4)), toDouble(list.get(5)), toInt(list.get(6)), "?", list.get(15));


                    shipPositionDataController.addDataToShip(ship, shipPositonData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
