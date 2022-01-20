package lapr.project.ui;

import lapr.project.controller.model_controllers.UserController;
import lapr.project.model.users.Users;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsersUI {

    private final UserController userController;

    public UsersUI(UserController userController) {
        this.userController = userController;
    }


    public void importUsers(String filePath){

        Users newUser = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {

                List<String> list = new ArrayList<>(Arrays.asList(line.split(",")));


                if (!list.get(0).contains("username")) {


                    newUser = new Users(list.get(0).trim(), list.get(1).trim(), list.get(2).trim());
                    userController.addUser(newUser);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
