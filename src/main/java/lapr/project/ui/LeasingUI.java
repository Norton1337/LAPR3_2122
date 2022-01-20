package lapr.project.ui;

import lapr.project.controller.model_controllers.LeasingController;
import lapr.project.model.leasing.Leasing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeasingUI {

    private final LeasingController leasingController;

    public LeasingUI(LeasingController leasingController) {
        this.leasingController = leasingController;
    }

    public void importLeasingCon(String filePath){

        Leasing newLeasing = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {

                List<String> list = new ArrayList<>(Arrays.asList(line.split(",")));


                if (!list.get(0).contains("clientUserName")) {

                    newLeasing = new Leasing(list.get(2).trim(), list.get(3).trim());
                    leasingController.addLeasing(newLeasing, list.get(0).trim(), list.get(1).trim());
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
