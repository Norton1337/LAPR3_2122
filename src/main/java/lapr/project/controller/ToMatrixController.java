package lapr.project.controller;

import lapr.project.data.graph_files.AdjacencyMatrixGraph;
import lapr.project.model.locals.Locals;
import lapr.project.model.locals.idb.ILocals;
import lapr.project.model.seadists.idb.ISeadist;


public class ToMatrixController {

    private AdjacencyMatrixGraph<Locals, Double> freightNetworkMatrix;
    private ILocals localsDB;
    private ISeadist seadistDB;

    public ToMatrixController(ILocals localsDB, ISeadist seadistDB) {
        this.freightNetworkMatrix = new AdjacencyMatrixGraph<>();
        this.localsDB = localsDB;
        this.seadistDB = seadistDB;
    }


    public void buildMatrix(int nClosestPorts){
        for(Locals elem: localsDB.getAllPortsAndWarehouses()){
            freightNetworkMatrix.insertVertex(elem);
        }




    }


    public void printMatrix(){
        System.out.println(freightNetworkMatrix.toString());
    }
}
