package lapr.project.controller.ModelControllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lapr.project.controller.model_controllers.LocalsController;
import lapr.project.data.mocks.CountryDBMock;
import lapr.project.data.mocks.LocalsDBMock;
import lapr.project.model.country.idb.ICountryDB;
import lapr.project.model.locals.Locals;
import lapr.project.model.locals.idb.ILocals;

class LocalsControllerTest {
    ICountryDB countryDB = new CountryDBMock();
    ILocals localDB = new LocalsDBMock();

    LocalsController localsController = new LocalsController(countryDB, localDB);

    @Test
    void addPortTest(){
        Locals local = new Locals("country", 1234, "portName", "coordinates");
        localsController.addPort(local);
        localsController.addPort(local);
        localsController.addWarehouse(local, "1234", "12333");

        assertEquals(2,localsController.getAllWarehouses().size());
      
    }
}
