package lapr.project.model.client.idb;

import lapr.project.model.client.Client;

import java.util.List;

public interface IClient {

    Client getClient(String id);

    List<Client> getAllClients();

    boolean addClient(Client client);

}
