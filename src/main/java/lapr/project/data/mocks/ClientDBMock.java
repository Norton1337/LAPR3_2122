package lapr.project.data.mocks;

import lapr.project.model.client.Client;
import lapr.project.model.client.idb.IClient;

import java.util.LinkedList;
import java.util.List;

public class ClientDBMock implements IClient {

    private final List<Client> clients = new LinkedList<>();

    @Override
    public Client getClient(String id) {
        for (Client elems : getAllClients()) {
            if (elems.getId().equals(id)) {
                return elems;
            }
        }

        return null;
    }

    @Override
    public List<Client> getAllClients() {
        return new LinkedList<>(clients);
    }

    @Override
    public boolean addClient(Client client) {
        return clients.add(client);
    }
}
