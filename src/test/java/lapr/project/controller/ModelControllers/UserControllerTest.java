package lapr.project.controller.ModelControllers;

import lapr.project.controller.model_controllers.ClientController;
import lapr.project.controller.model_controllers.UserController;
import lapr.project.data.mocks.ClientDBMock;
import lapr.project.data.mocks.UsersDBMock;
import lapr.project.model.users.Users;
import lapr.project.ui.ClientUI;
import lapr.project.ui.UsersUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserControllerTest {
    //DB
    UsersDBMock usersDBMock = new UsersDBMock();

    //Controller
    UserController userController = new UserController(usersDBMock);
    //LEITURA DE FICHEIRO
    UsersUI usersUI = new UsersUI(userController);

    Users newUser;

    @BeforeEach
    void setup(){
        usersUI.importUsers("Docs/Input/users.csv");
    }

    @Test
    void userControllerTest(){
        UserController userController = new UserController(usersDBMock);
    }

    @Test
    void addUserTest(){
        newUser = new Users("Client222","123","Client");
        userController.addUser(newUser);
        assertTrue(usersDBMock.getAllUsers().contains(newUser));
    }

    @Test
    void getAllUsersTest(){
        newUser = new Users("Client222","123","Client");
        userController.addUser(newUser);

        assertEquals(usersDBMock.getAllUsers().size(),userController.getAllUsers().size());
    }

    @Test
    void getUserById(){
        newUser = new Users("Client222","123","Client");
        userController.addUser(newUser);

        assertEquals("Client222",userController.getUserById(newUser.getId()).getUsername());
    }

    @Test
    void getUserByUserNameTest(){
        newUser = new Users("Client222","123","Client");
        userController.addUser(newUser);

        assertEquals("Client222",userController.getUserByUsername(newUser.getUsername()).getUsername());
        assertNull(userController.getUserByUsername("jose"));
    }
}
