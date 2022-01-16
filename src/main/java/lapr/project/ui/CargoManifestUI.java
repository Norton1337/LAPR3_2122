package lapr.project.ui;

import lapr.project.controller.model_controllers.CargoManifestController;
import lapr.project.model.cargoManifest.CargoManifest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CargoManifestUI {


    private final CargoManifestController cargoManifestController;

    public CargoManifestUI(CargoManifestController cargoManifestController) {
        this.cargoManifestController = cargoManifestController;
    }

    public void importCargoManifest(String file) {

        CargoManifest newCargoManifest = null;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line = "";

            while ((line = br.readLine()) != null) {


                List<String> list = new ArrayList<>(Arrays.asList(line.split(",")));


                if (!list.get(1).contains("cargo_recon")) {

                    newCargoManifest = new CargoManifest(list.get(3),list.get(2),list.get(4),list.get(5),list.get(1));

                    cargoManifestController.addCargoManifest(newCargoManifest,list.get(0));
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

