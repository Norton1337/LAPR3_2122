package lapr.project.ui;

import lapr.project.controller.model_controllers.ClientController;
import lapr.project.model.client.Client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientUI {

    private final ClientController clientController;

    public ClientUI(ClientController clientController) {
        this.clientController = clientController;
    }

    public void importClients(String filePath){

        Client newClient = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {

                List<String> list = new ArrayList<>(Arrays.asList(line.split(",")));


                if (!list.get(0).contains("userName")) {

                    newClient = new Client(list.get(1).trim(), list.get(2).trim(), list.get(3).trim());
                    clientController.addClient(newClient, list.get(0).trim());
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
