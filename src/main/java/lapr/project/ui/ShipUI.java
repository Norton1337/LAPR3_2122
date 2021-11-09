package lapr.project.ui;

import lapr.project.controller.ModelControllers.GeneratorController;
import lapr.project.controller.ModelControllers.ShipController;
import lapr.project.controller.ModelControllers.ShipPositionDataController;
import lapr.project.model.ShipPositionData.ShipPositonData;
import lapr.project.model.Ships.Generator;
import lapr.project.model.Ships.Ship;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static lapr.project.utils.Utils.*;

public class ShipUI {

    private ShipController shipController;
    private ShipPositionDataController shipPositionDataController;
    private GeneratorController generatorController;

    BufferedReader input;

    public ShipUI(ShipController shipController, ShipPositionDataController shipPositionDataController,
                  GeneratorController generatorController) {
        this.shipController = shipController;
        this.shipPositionDataController = shipPositionDataController;
        this.generatorController = generatorController;
    }

    public void importShips(String filePath){
        List<String> alreadyUsed = new ArrayList<>();
        Ship newShip = null;
        ShipPositonData shipPositonData = null;
        Generator shipGenerator = null;


        try {
            input = new BufferedReader(new FileReader(filePath));
            String line = "";

            while ((line = input.readLine()) != null){
                
                List<String> list = new ArrayList<>(Arrays.asList(line.split(",")));

                //GENERATING OBJ
                if(!alreadyUsed.contains(list.get(0)) && !list.get(0).contains("MMSI")){

                    /**
                     * Ship Object Created
                     */
                    newShip = new Ship(list.get(0), list.get(7), list.get(8), list.get(9), ToInt(list.get(10)),
                            ToInt(list.get(11)), ToInt(list.get(12)), ToDouble(list.get(13)), ToDouble(list.get(14)));

                    /**
                     * Generator Object To Be Created
                     */
                    shipGenerator =  new Generator(1,5.0);

                    boolean resultShip = shipController.addShip(newShip);
                    boolean resultGen = generatorController.addGenartor(newShip, shipGenerator);

                    if(resultShip && resultGen){
                        alreadyUsed.add(newShip.getMMSI());
                    }

                }if(!list.get(0).contains("MMSI")){
                    String mmsi = list.get(0);
                    Ship ship = shipController.findShipByMMSI(mmsi);

                    /**
                     * ShipPositonData Object Created
                     */

                    shipPositonData = new ShipPositonData(list.get(1), coordinates(list.get(2), list.get(3)),
                            ToDouble(list.get(4)), ToDouble(list.get(5)), ToInt(list.get(6)), "?", list.get(15));


                    shipPositionDataController.addDataToShip(ship, shipPositonData);


                }


            }


             //printList(shipController.getAllShips());
             //printList(generatorController.getAllGenerator());
             //printList(shipPositionDataController.getShipData());



        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
