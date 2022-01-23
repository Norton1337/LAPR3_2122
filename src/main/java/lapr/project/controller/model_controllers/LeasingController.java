package lapr.project.controller.model_controllers;

import lapr.project.model.client.Client;
import lapr.project.model.client.idb.IClient;
import lapr.project.model.containers.Container;
import lapr.project.model.containers.idb.IContainerDB;
import lapr.project.model.leasing.Leasing;
import lapr.project.model.leasing.idb.ILeasing;
import lapr.project.model.users.Users;
import lapr.project.model.users.idb.IUsers;

import java.util.List;

import static lapr.project.utils.Utils.randomUUID;
import static lapr.project.utils.Utils.toInt;

public class LeasingController {

    private final ILeasing leasingDB;
    private final IContainerDB containerDB;
    private final IClient clientDB;
    private final IUsers usersDB;
    private final ClientController clientController;

    public LeasingController(ILeasing leasingDB, IContainerDB containerDB, IClient clientDB, IUsers usersDB, ClientController clientController) {
        this.leasingDB = leasingDB;
        this.containerDB = containerDB;
        this.clientDB = clientDB;
        this.usersDB = usersDB;
        this.clientController = clientController;
    }

    public boolean addLeasing(Leasing leasing, String clientUsername, String containerNumber){
        Users users = null;
        Container container = null;

        for(Container elems : containerDB.getAllContainers()){
            if(elems.getContainerNumber() == toInt(containerNumber)){
                container = elems;
            }
        }

        
        for(Users elems : usersDB.getAllUsers()){
            if(elems.getUsername().equals(clientUsername)){
                users = elems;
            }
        }

        assert users != null;
        assert container != null;

        leasing.setClientId(clientController.getClientWithUserId(users.id).getId());
        leasing.setContainerId(container.getId());
        leasing.setId(randomUUID());

        return leasingDB.addLeasing(leasing);
    }


    public List<Leasing> getAllLeasing(){
        return leasingDB.getAllLeasing();
    }


    public Leasing getLeasingById(String id){
        return leasingDB.getLeasingById(id);
    }
}
