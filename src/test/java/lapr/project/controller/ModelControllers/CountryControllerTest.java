package lapr.project.controller.ModelControllers;

import lapr.project.controller.model_controllers.CountryController;
import lapr.project.data.mocks.BordersDBMock;
import lapr.project.data.mocks.CountryDBMock;
import lapr.project.data.mocks.PortsAndWarehousesDBMock;
import lapr.project.ui.CountryUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static lapr.project.utils.Utils.printList;

class CountryControllerTest {

    //DB
    CountryDBMock countryDBMock = new CountryDBMock();
    BordersDBMock bordersDBMock = new BordersDBMock();
    PortsAndWarehousesDBMock portsAndWarehousesDBMock = new PortsAndWarehousesDBMock();

    //Controller
    CountryController countryController = new CountryController(countryDBMock, bordersDBMock, portsAndWarehousesDBMock);


    //LEITURA DE FICHEIRO
    CountryUI countryUI = new CountryUI(countryController);

    @BeforeEach
    void setup() {
        countryUI.importCountriesAndBorders("Docs/Input/countries.csv", "Docs/Input/borders.csv");
    }


    @Test
    void test() {

        //printList(countryController.getAllCountries());
        printList(bordersDBMock.getAllBorders());
    }


}