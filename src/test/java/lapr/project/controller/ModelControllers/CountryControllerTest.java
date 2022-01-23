package lapr.project.controller.ModelControllers;

import lapr.project.controller.model_controllers.CountryController;
import lapr.project.data.mocks.BordersDBMock;
import lapr.project.data.mocks.CountryDBMock;
import lapr.project.data.mocks.LocalsDBMock;
import lapr.project.ui.CountryUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static lapr.project.utils.Utils.printList;
import static lapr.project.utils.Utils.readFromProp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CountryControllerTest {

    //DB
    CountryDBMock countryDBMock = new CountryDBMock();
    BordersDBMock bordersDBMock = new BordersDBMock();
    LocalsDBMock portsAndWarehousesDBMock = new LocalsDBMock();

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

        if (Objects.equals(readFromProp("debug", "src/main/resources/application.properties"), "1")){
            printList(bordersDBMock.getAllBorders());
        }
        

    }

    @Test
    void getAllBordersOfCountryTest(){
        assertEquals(1, countryController.getAllBordersOfCountry("Portugal").size());
    }

    @Test
    void getCountryWithCapitalTest(){
        assertEquals("Portugal", countryController.getCountryWithCapital("Lisbon").getCountryName());
        assertNull(countryController.getCountryWithCapital("something"));
    }


}