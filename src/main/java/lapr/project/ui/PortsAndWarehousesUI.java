package lapr.project.ui;

import lapr.project.controller.model_controllers.PortsAndWarehousesController;
import lapr.project.model.locals.Locals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PortsAndWarehousesUI {


    private PortsAndWarehousesController portsAndWarehousesController;
    public PortsAndWarehousesUI(PortsAndWarehousesController portsAndWarehousesController) {
        this.portsAndWarehousesController = portsAndWarehousesController;
    }

    public void importPorts(String file){

        Locals newPortsAndWarehouses = null;


        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line = "";

            while ((line = br.readLine()) !=null){


                List<String> list = new ArrayList<>(Arrays.asList(line.split(",")));


                if (!list.get(2).contains("code")){

                    String coordinates = list.get(4)+ ","+ list.get(5);

                    newPortsAndWarehouses = new Locals(list.get(0), list.get(1), Integer.parseInt(list.get(2)), list.get(3), coordinates);
                    portsAndWarehousesController.addPortAndWarehouse(newPortsAndWarehouses);

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
