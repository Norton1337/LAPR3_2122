package lapr.project.model.seadists.idb;

import lapr.project.model.seadists.Seadist;

import java.util.List;

public interface ISeadist {

    List<Seadist> getAllSeadist();

    Seadist getSeadistById(String id);

    boolean addSeadist(Seadist elem);
}
