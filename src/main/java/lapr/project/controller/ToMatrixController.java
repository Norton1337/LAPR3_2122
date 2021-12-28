package lapr.project.controller;

import lapr.project.controller.model_controllers.CountryController;
import lapr.project.data.graph_files.AdjacencyMatrixGraph;
import lapr.project.model.borders.Borders;
import lapr.project.model.borders.idb.IBordersDB;
import lapr.project.model.country.idb.ICountryDB;
import lapr.project.model.locals.Locals;
import lapr.project.model.locals.idb.ILocals;
import lapr.project.model.seadists.idb.ISeadist;

import java.util.List;

import static lapr.project.utils.Utils.printList;


public class ToMatrixController {

    private AdjacencyMatrixGraph<Locals, Double> freightNetworkMatrix;
    private ILocals localsDB;
    private ISeadist seadistDB;
    private ICountryDB countryDB;
    private IBordersDB bordersDB;

    private CountryController countryController;


    public ToMatrixController(ILocals localsDB, ISeadist seadistDB, ICountryDB countryDB, IBordersDB bordersDB) {
        this.freightNetworkMatrix = new AdjacencyMatrixGraph<>();
        this.localsDB = localsDB;
        this.seadistDB = seadistDB;
        this.countryDB = countryDB;
        this.bordersDB = bordersDB;
        this.countryController = new CountryController(countryDB,bordersDB,localsDB);
    }


    public void buildMatrix(int nClosestPorts){
        for(Locals elem: localsDB.getAllPortsAndWarehouses()){
            freightNetworkMatrix.insertVertex(elem);
        }


        /**
         * Insert Edge relative to borders
         */


        //TODO Buscar pelo nome, e se portId = -1

        for(Locals elems: localsDB.getAllCapitals()){
            List<Borders> bordersOfCountry = countryController.getAllBordersOfCountry(elems.getName());
            for(Borders borders: bordersOfCountry){
                //localsDB.get


                //freightNetworkMatrix.insertEdge(elems, localsDB.ge)
            }
            //System.out.println(elems.getName());
            //printList(countryController.getAllBordersOfCountry(elems.getName()));
        }

        printList(localsDB.getAllPortsAndWarehouses());
        //freightNetworkMatrix.insertEdge()





    }


    public void printMatrix(){
        System.out.println(freightNetworkMatrix.toString());
    }
}
