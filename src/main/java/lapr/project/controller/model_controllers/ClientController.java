package lapr.project.controller.model_controllers;

import lapr.project.model.client.Client;
import lapr.project.model.client.idb.IClient;
import lapr.project.model.users.Users;
import lapr.project.model.users.idb.IUsers;

import java.util.List;

import static lapr.project.utils.Utils.randomUUID;

public class ClientController{

    private final IClient clientDB;
    private final IUsers usersDB;

    public ClientController(IClient clientDB, IUsers usersDB) {
        this.clientDB = clientDB;
        this.usersDB = usersDB;
    }

    public boolean addClient(Client client, String userName){
        Users user = null;

        for(Users elems : usersDB.getAllUsers()){
            if(elems.getUsername().equals(userName)){
                user = elems;
            }
        }

        assert user != null;
        client.setUserId(user.getId());
        client.setId(randomUUID());
        return clientDB.addClient(client);
    }

    public Client getClientWithUserId(String userId){
        for(Client elems: clientDB.getAllClients()){
            if(elems.getUserId().equals(userId)){
                return elems;
            }
        }
        return null;
    }

    public List<Client> getAllClients(){
        return clientDB.getAllClients();
    }

    public Client getClientById(String id){
        return clientDB.getClient(id);
    }
}
