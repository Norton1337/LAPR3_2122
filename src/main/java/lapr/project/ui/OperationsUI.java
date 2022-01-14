package lapr.project.ui;

import lapr.project.controller.model_controllers.CargoManifestController;
import lapr.project.controller.model_controllers.ContainerController;
import lapr.project.controller.model_controllers.LocalsController;
import lapr.project.controller.model_controllers.OperationController;
import lapr.project.model.cargoManifest.CargoManifest;
import lapr.project.model.containers.Container;
import lapr.project.model.locals.Locals;
import lapr.project.model.operation.Operation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OperationsUI {

    private OperationController operationController;
    private ContainerController containerController;
    private CargoManifestController cargoManifestController;
    public OperationsUI(OperationController operationController) {
        this.operationController = operationController;
    }

    public void importOperations(String file) {

        Operation newOperation = null;


        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line = "";

            while ((line = br.readLine()) != null) {


                List<String> list = new ArrayList<>(Arrays.asList(line.split(",")));


                if (!list.get(1).contains("container_number")) {

                    Container newContainer = containerController.findContainerByNumber(list.get(1));
                    CargoManifest newCargoManifest = cargoManifestController.findCargoByRecon(list.get(2));


                    newOperation = new Operation(newContainer.getId(),newCargoManifest.getId(),,Integer.parseInt(list.get(4)),Integer.parseInt(list.get(5)),Integer.parseInt(list.get(6)));

                    operationController.addOperation(newOperation);

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
