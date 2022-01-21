package lapr.project.model;

import lapr.project.model.truck.Truck;
import lapr.project.model.users.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersTest {

    Users newUsers;

    @BeforeEach
    void setup() {
        newUsers = new Users("Client222","qwerty","Client");
        newUsers.setId("UserID");
    }

    @Test
    void setAndGetTUserIdTest() {
        assertEquals("UserID",newUsers.getId());
        newUsers.setId("newUserId");
        assertEquals("newUserId",newUsers.getId());
    }

    @Test
    void setAndGetUsernameTest() {
        assertEquals("Client222",newUsers.getUsername());
        newUsers.setUsername("Client223");
        assertEquals("Client223",newUsers.getUsername());
    }
    @Test
    void setAndGetPasswordTest() {
        assertEquals("qwerty",newUsers.getPassword());
        newUsers.setPassword("yew");
        assertEquals("yew",newUsers.getPassword());
    }
    @Test
    void setAndGetRoleTest() {
        assertEquals("Client",newUsers.getRole());
        newUsers.setRole("Ship Captain");
        assertEquals("Ship Captain",newUsers.getRole());
    }
    @Test
    void toStringTest() {
        String string = "Users{" +
                "id='UserID'" +
                ", username='Client222'" +
                ", password='qwerty'" +
                ", role='Client'" +
                '}';
        assertEquals(string, newUsers.toString());
    }
}
