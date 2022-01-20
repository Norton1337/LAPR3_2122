package lapr.project.model.leasing.idb;

import lapr.project.model.leasing.Leasing;

import java.util.List;

public interface ILeasing {

    Leasing getLeasingById(String id);

    List<Leasing> getAllLeasing();

    boolean addLeasing(Leasing leasing);

}
