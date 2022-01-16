package lapr.project.ui;

import lapr.project.controller.model_controllers.ContainerController;
import lapr.project.controller.model_controllers.LocalsController;
import lapr.project.model.locals.Locals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WarehouseUI {

    private LocalsController portsAndWarehousesController;

    public WarehouseUI(LocalsController portsAndWarehousesController) {
        this.portsAndWarehousesController = portsAndWarehousesController;
    }

    public void importWarehouses(String file) {

        Locals newWarehouse = null;


        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line = "";

            while ((line = br.readLine()) != null) {


                List<String> list = new ArrayList<>(Arrays.asList(line.split(",")));


                if (!list.get(0).contains("code")) {

                    String coordinates = list.get(6) + "," + list.get(7);

                    newWarehouse = new Locals(list.get(5), Integer.parseInt(list.get(0)), list.get(3), coordinates);
                    portsAndWarehousesController.addWarehouse(newWarehouse,list.get(1),list.get(2));

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
