package lapr.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lapr.project.model.seadists.Seadist;

class SeadistTest {
    Seadist newSeadist;

    @BeforeEach
    void setup() {
        newSeadist = new Seadist("fromPortId", "toPortId", (float)420.58);
        newSeadist.setId("id");
    }


    @Test
    void setAndGetIdTest() {
       assertEquals("id", newSeadist.getId());
       newSeadist.setId("id2");
       assertEquals("id2", newSeadist.getId());
    }

    @Test
    void setAndGetFromPortIdTest() {
        assertEquals("fromPortId", newSeadist.getFromPortId());
        newSeadist.setFromPortId("fromPortId2");
        assertEquals("fromPortId2", newSeadist.getFromPortId());
    }

    @Test
    void setAndGetToPortIdTest() {
        assertEquals("toPortId", newSeadist.getToPortId());
        newSeadist.setToPortId("toPortId2");
        assertEquals("toPortId2", newSeadist.getToPortId());
    }

    @Test
    void setAndGetDistance() {
        assertEquals((float)420.58, newSeadist.getDistance());
        newSeadist.setDistance((float)123.4);
        assertEquals((float)123.4, newSeadist.getDistance());
    }

    @Test
    void toStringTest(){
        String string = "Seadist{"+
                        "id='id'"+
                        ", fromPortId='fromPortId'"+
                        ", toPortId='toPortId'"+
                        ", distance=420.58}";
        assertEquals(string, newSeadist.toString());

    }



}
