package lapr.project.ui;

import lapr.project.controller.ModelControllers.PortsAndWarehousesController;
import lapr.project.model.PortsAndWarehouses.PortsAndWarehouses;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PortsAndWarehousesUI {


    private PortsAndWarehousesController portsAndWarehousesController;
    BufferedReader br;
    public PortsAndWarehousesUI(PortsAndWarehousesController portsAndWarehousesController) {
        this.portsAndWarehousesController = portsAndWarehousesController;
    }

    public void importPorts(String file){

        List<PortsAndWarehouses> listOfPortsAndWarehouses = new ArrayList<>();
        PortsAndWarehouses newPortsAndWarehouses = null;


        try {

            br = new BufferedReader(new FileReader(file));
            String line = "";

            while ((line = br.readLine()) !=null){


                List<String> list = new ArrayList<>(Arrays.asList(line.split(",")));


                if (! listOfPortsAndWarehouses.contains(list.get(2)) && !list.get(2).contains("code")){

                    String coordinates = list.get(4)+ ","+ list.get(5);


                    newPortsAndWarehouses  =new PortsAndWarehouses(list.get(0), list.get(1), Integer.parseInt(list.get(2)), list.get(3), coordinates);
                    portsAndWarehousesController.addPortAndWarehouse(newPortsAndWarehouses);

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
