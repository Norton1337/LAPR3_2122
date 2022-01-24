package lapr.project.ui;

import static lapr.project.utils.Utils.toInt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lapr.project.cargoship_stories.OperationController;
import lapr.project.model.operation.Operation;

public class OperationsUI {

    private OperationController operationController;

    public OperationsUI(OperationController operationController) {
        this.operationController = operationController;
    }

    public void importOperations(String file) {

        Operation newOperation = null;


        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line = "";

            while ((line = br.readLine()) != null) {


                List<String> list = new ArrayList<>(Arrays.asList(line.split(",")));


                if (!list.get(0).contains("container_number")) {

                    newOperation = new Operation(toInt(list.get(3)),toInt(list.get(4)),toInt(list.get(5)));
                    operationController.addOperation(newOperation,list.get(0),list.get(1),list.get(2));

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
