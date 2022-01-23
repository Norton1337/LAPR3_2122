package lapr.project.controller.ModelControllers;

import lapr.project.controller.model_controllers.LocalsController;
import lapr.project.controller.model_controllers.SeadistController;
import lapr.project.data.mocks.CountryDBMock;
import lapr.project.data.mocks.LocalsDBMock;
import lapr.project.data.mocks.SeadistDBMock;
import lapr.project.model.country.idb.ICountryDB;
import lapr.project.model.locals.Locals;
import lapr.project.model.locals.idb.ILocals;
import lapr.project.model.seadists.Seadist;
import lapr.project.model.seadists.idb.ISeadist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static lapr.project.utils.Utils.randomInt;
import static lapr.project.utils.Utils.randomUUID;
import static org.junit.jupiter.api.Assertions.*;

class SeadistControllerTest {

    List<Seadist> sea = new LinkedList<>();
    SeadistController seadistController;
    LocalsController localsController;


    @BeforeEach
    public void setUp() {
        ILocals localsDb = new LocalsDBMock();
        ISeadist seadistDb = new SeadistDBMock();
        ICountryDB countryDb = new CountryDBMock();

        seadistController = new SeadistController(localsDb, seadistDb);
        localsController = new LocalsController(countryDb,localsDb);

        Locals localTest = new Locals(randomUUID(), randomInt(),  randomUUID(), randomUUID());
        localTest.setId("123test123");
        localTest.setPortId("123test123");

        localsController.addPort(localTest);

        String fromPortId = randomUUID();
        String toPortId = "123test123";

        Seadist seadist = new Seadist(fromPortId, toPortId, 32.0f);

        seadistController.addElemToSeaDist(seadist);
    }

    @Test
    void addElemToSeaDist() {
        String fromPortId = randomUUID();
        String toPortId = randomUUID();

        Seadist seadist = new Seadist(fromPortId, toPortId, 36.0f);
        seadistController.addElemToSeaDist(seadist);

        assertTrue(seadistController.getAllSeadist().size() > 0);
        assertEquals(36.0f, seadistController.getAllSeadist().get(1).getDistance());
    }


    @Test
    void getAllSeadist() {
        assertTrue(seadistController.getAllSeadist().size() > 0);
    }
}