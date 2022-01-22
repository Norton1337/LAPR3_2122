package lapr.project.model;

import lapr.project.model.client.Client;
import lapr.project.model.users.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {
    Client newClient;

    @BeforeEach
    void setup() {
        newClient = new Client("Joaquim","Avenida","933333333");
        newClient.setId("ClientId");
        newClient.setUserId("Joaquim123");
    }

    @Test
    void setAndGetClientIdTest() {
        assertEquals("ClientId",newClient.getId());
        newClient.setId("newUserId");
        assertEquals("newUserId",newClient.getId());
    }

    @Test
    void setAndGetClientNameTest() {
        assertEquals("Joaquim",newClient.getName());
        newClient.setName("Alberto");
        assertEquals("Alberto",newClient.getName());
    }
    @Test
    void setAndGetClientAddressTest() {
        assertEquals("Avenida",newClient.getAddress());
        newClient.setAddress("Rua");
        assertEquals("Rua",newClient.getAddress());
    }
    @Test
    void setAndGetCellphoneTest() {
        assertEquals("933333333",newClient.getCellphone());
        newClient.setCellphone("933333334");
        assertEquals("933333334",newClient.getCellphone());
    }

    @Test
    void setAndGetUserIdTest(){
        assertEquals("Joaquim123",newClient.getUserId());
        newClient.setUserId("Joaquim124");
        assertEquals("Joaquim124",newClient.getUserId());
    }

    @Test
    void toStringTest() {
        String string = "Client{" +
                "id='ClientId'" +
                ", userId='Joaquim123'" +
                ", name='Joaquim'" +
                ", address='Avenida'" +
                ", cellphone='933333333'" +
                '}';
        assertEquals(string, newClient.toString());
    }
}
