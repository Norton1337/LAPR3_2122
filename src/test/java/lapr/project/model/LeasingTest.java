package lapr.project.model;

import lapr.project.model.leasing.Leasing;
import lapr.project.model.users.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeasingTest {
    Leasing newLeasing;

    @BeforeEach
    void setup() {
        newLeasing = new Leasing("2021-02-03 12:40:22", "2021-02-04 11:40:22");
        newLeasing.setId("LeasingId");
        newLeasing.setClientId("Client222");
        newLeasing.setContainerId("Container3");
    }

    @Test
    void setAndGetTLeasingIdTest() {
        assertEquals("LeasingId",newLeasing.getId());
        newLeasing.setId("newLeasingId");
        assertEquals("newLeasingId",newLeasing.getId());
    }

    @Test
    void setAndGetLeasingClientIdTest() {
        assertEquals("Client222",newLeasing.getClientId());
        newLeasing.setClientId("Client223");
        assertEquals("Client223",newLeasing.getClientId());
    }
    @Test
    void setAndGetLeasingContainerTest() {
        assertEquals("Container3",newLeasing.getContainerId());
        newLeasing.setContainerId("Container4");
        assertEquals("Container4",newLeasing.getContainerId());
    }
    @Test
    void setAndGetLeasingStartDateTest() {
        assertEquals("2021-02-03 12:40:22",newLeasing.getStartDate());
        newLeasing.setStartDate("2021-02-04 12:40:22");
        assertEquals("2021-02-04 12:40:22",newLeasing.getStartDate());
    }

    @Test
    void setAndGetLeasingEndDateTest() {
        assertEquals("2021-02-04 11:40:22",newLeasing.getEndDate());
        newLeasing.setEndDate("2021-02-05 12:40:22");
        assertEquals("2021-02-05 12:40:22",newLeasing.getEndDate());
    }

    @Test
    void toStringTest() {
        String string = "Leasing{" +
                "id='LeasingId'" +
                ", clientId='Client222'" +
                ", containerId='Container3'" +
                ", startDate='2021-02-03 12:40:22'" +
                ", endDate='2021-02-04 11:40:22'" +
                '}';
        assertEquals(string, newLeasing.toString());
    }
}
