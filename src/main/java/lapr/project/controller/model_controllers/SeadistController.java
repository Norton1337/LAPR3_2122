package lapr.project.controller.model_controllers;

import lapr.project.model.locals.idb.ILocals;
import lapr.project.model.seadists.Seadist;
import lapr.project.model.seadists.idb.ISeadist;

import java.util.LinkedList;

public class SeadistController {

    private final ILocals localDB;
    private final ISeadist seadistDB;



    public SeadistController(ILocals localDB, ISeadist seadistDB) {
        this.localDB = localDB;
        this.seadistDB = seadistDB;
    }

    public void addElemToSeaDist(Seadist elem) {
        seadistDB.addSeadist(elem);
    }




    public LinkedList<Seadist> getAllSeadist() {
        return new LinkedList<>(seadistDB.getAllSeadist());
    }
}
