package lapr.project.data.mocks;

import lapr.project.model.leasing.Leasing;
import lapr.project.model.leasing.idb.ILeasing;

import java.util.LinkedList;
import java.util.List;

public class LeasingDBMock implements ILeasing {

    private final List<Leasing> leasingList = new LinkedList<>();


    @Override
    public Leasing getLeasingById(String id) {
        for (Leasing elems : getAllLeasing()) {
            if (elems.getId().equals(id)) {
                return elems;
            }
        }

        return null;
    }

    @Override
    public List<Leasing> getAllLeasing() {
        return new LinkedList<>(leasingList);
    }

    @Override
    public boolean addLeasing(Leasing leasing) {
        return leasingList.add(leasing);
    }
}
