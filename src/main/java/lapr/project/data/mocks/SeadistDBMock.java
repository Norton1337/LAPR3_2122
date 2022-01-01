package lapr.project.data.mocks;

import lapr.project.model.seadists.Seadist;
import lapr.project.model.seadists.idb.ISeadist;

import java.util.LinkedList;
import java.util.List;

import static lapr.project.utils.Utils.randomUUID;

public class SeadistDBMock implements ISeadist {

    private final List<Seadist> allSeadist = new LinkedList<>();


    @Override
    public List<Seadist> getAllSeadist() {
        return new LinkedList<>(allSeadist);
    }

    @Override
    public Seadist getSeadistById(String id) {
        for (Seadist elems : allSeadist) {
            if (elems.getId().equals(id)) {
                return elems;
            }
        }
        return null;
    }

    @Override
    public boolean addSeadist(Seadist elem) {
        elem.setId(randomUUID());
        return allSeadist.add(elem);
    }
}
