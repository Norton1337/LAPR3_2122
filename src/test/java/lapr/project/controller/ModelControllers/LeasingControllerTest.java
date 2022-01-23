package lapr.project.controller.ModelControllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lapr.project.controller.model_controllers.ClientController;
import lapr.project.controller.model_controllers.LeasingController;
import lapr.project.data.mocks.ClientDBMock;
import lapr.project.data.mocks.ContainerDBMock;
import lapr.project.data.mocks.LeasingDBMock;
import lapr.project.data.mocks.UsersDBMock;
import lapr.project.model.client.Client;
import lapr.project.model.client.idb.IClient;
import lapr.project.model.containers.Container;
import lapr.project.model.containers.idb.IContainerDB;
import lapr.project.model.leasing.Leasing;
import lapr.project.model.leasing.idb.ILeasing;
import lapr.project.model.users.Users;
import lapr.project.model.users.idb.IUsers;

class LeasingControllerTest {
    private final ILeasing leasingDB = new LeasingDBMock();
    private final IContainerDB containerDB = new ContainerDBMock();
    private final IClient clientDB = new ClientDBMock();
    private final IUsers usersDB = new UsersDBMock();
    private ClientController clientController = new ClientController(clientDB, usersDB);
    private LeasingController leasingController = new LeasingController(leasingDB, containerDB, clientDB, usersDB, clientController);


    @Test
    void addLeasingTest(){
        Leasing leasing = new Leasing("12/12/2020", "13/12/2020");
        Users user = new Users("username", "password", "role");
        user.setId("id123");
        usersDB.addUser(user);
        Client client = new Client("username", "address", "cellphone");
        client.setId("id");
        client.setUserId("id123");
        clientDB.addClient(client);
        Container container = new Container(2223,5,"ASD",500,
        500,1000,300,"Certificates","Repair",
        "Refrigerated");
        container.setId("id");
        containerDB.addContainer(container);

        assertTrue(leasingController.addLeasing(leasing, "username", "2223"));
        assertEquals(1, leasingController.getAllLeasing().size());
        String string = leasing.getId();
        assertEquals(leasing, leasingController.getLeasingById(string));
    }

  
}
