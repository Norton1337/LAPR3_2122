package lapr.project.controller.ModelControllers;

import lapr.project.controller.model_controllers.ClientController;
import lapr.project.controller.model_controllers.UserController;
import lapr.project.data.mocks.ClientDBMock;
import lapr.project.data.mocks.UsersDBMock;
import lapr.project.model.client.Client;
import lapr.project.model.users.Users;
import lapr.project.ui.ClientUI;
import lapr.project.ui.UsersUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientControllerTest {
    //DB
    ClientDBMock clientDBMock = new ClientDBMock();
    UsersDBMock usersDBMock = new UsersDBMock();

    //Controller
    ClientController clientController = new ClientController(clientDBMock,usersDBMock);
    UserController userController = new UserController(usersDBMock);
    //LEITURA DE FICHEIRO
    ClientUI clientUI = new ClientUI(clientController);
    UsersUI usersUI = new UsersUI(userController);

    @BeforeEach
    void setup(){
        usersUI.importUsers("Docs/Input/users.csv");
        clientUI.importClients("Docs/Input/clients.csv");
    }

    @Test
    void clientControllerTest(){
    }

    @Test
    void addClientTest(){
        Users newUser = new Users("Client222","123","Client");
        usersDBMock.addUser(newUser);
        Client newClient = new Client("Joaquim","Avenida","933333333");
        clientController.addClient(newClient,"Client222");
        assertTrue(clientDBMock.getAllClients().contains(newClient));
    }

    @Test
    void getClientWithUserId(){
        Users newUser = new Users("Client222","123","Client");
        usersDBMock.addUser(newUser);
        Client newClient = new Client("Joaquim","Avenida","933333333");
        clientController.addClient(newClient,"Client222");
        newClient.setUserId("UserId");
        assertEquals("Joaquim",clientController.getClientWithUserId(newClient.getUserId()).getName());
    }

    @Test
    void getAllClients(){
        Users newUser = new Users("Client222","123","Client");
        usersDBMock.addUser(newUser);
        Client newClient = new Client("Joaquim","Avenida","933333333");
        clientController.addClient(newClient,"Client222");

        assertEquals(clientDBMock.getAllClients().size(),clientController.getAllClients().size());
    }

    @Test
    void getClientByIdTest(){
        Users newUser = new Users("Client222","123","Client");
        usersDBMock.addUser(newUser);
        Client newClient = new Client("Joaquim","Avenida","933333333");
        clientController.addClient(newClient,"Client222");

        Client result = clientController.getClientById(newClient.getId());
        assertEquals(newClient.getId(),result.getId());
    }

}
