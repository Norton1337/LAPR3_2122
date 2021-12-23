package lapr.project.controller.ModelControllers;

import lapr.project.controller.model_controllers.CountryController;
import lapr.project.data.mocks.CountryDBMock;
import lapr.project.ui.CountryUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static lapr.project.utils.Utils.printList;

class CountryControllerTest {

    //DB
    CountryDBMock countryDBMock = new CountryDBMock();

    //Controller
    CountryController countryController = new CountryController(countryDBMock);


    //LEITURA DE FICHEIRO
    CountryUI countryUI = new CountryUI(countryController);

    @BeforeEach
    void setup() {
        countryUI.importCountries("Docs/Input/countries.csv");
    }


    @Test
    void test() {
        printList(countryController.getAllCountries());
    }


}