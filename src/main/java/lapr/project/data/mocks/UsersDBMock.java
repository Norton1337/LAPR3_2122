package lapr.project.data.mocks;

import lapr.project.model.users.Users;
import lapr.project.model.users.idb.IUsers;

import java.util.LinkedList;
import java.util.List;

public class UsersDBMock implements IUsers {

    private final List<Users> users = new LinkedList<>();

    @Override
    public Users getUser(String id) {
        for (Users elems : getAllUsers()) {
            if (elems.getId().equals(id)) {
                return elems;
            }
        }

        return null;
    }

    @Override
    public List<Users> getAllUsers() {
        return new LinkedList<>(users);
    }

    @Override
    public boolean addUser(Users user) {
        return users.add(user);
    }
}
