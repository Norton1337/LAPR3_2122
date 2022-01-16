package lapr.project.ui;

import lapr.project.controller.model_controllers.ContainerController;
import lapr.project.controller.model_controllers.LocalsController;
import lapr.project.model.containers.Container;
import lapr.project.model.operation.Operation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lapr.project.utils.Utils.toInt;

public class ContainerUI {

    private ContainerController containerController;

    public ContainerUI(ContainerController containerController) {
        this.containerController = containerController;
    }

    public void importContainers(String file) {

        Container newContainer = null;


        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line = "";

            while ((line = br.readLine()) != null) {


                List<String> list = new ArrayList<>(Arrays.asList(line.split(",")));


                if (!list.get(0).contains("container_number")) {


                    newContainer = new Container(toInt(list.get(0)),toInt(list.get(1)),list.get(6),
                                                Double.parseDouble(list.get(4)),
                                                Double.parseDouble(list.get(3)),
                                                Double.parseDouble(list.get(2)),
                                                Double.parseDouble(list.get(5)),
                                                list.get(7),list.get(8),list.get(9));

                    containerController.addContainer(newContainer);

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
