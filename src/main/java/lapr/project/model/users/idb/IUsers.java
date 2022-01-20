package lapr.project.model.users.idb;

import lapr.project.model.users.Users;

import java.util.List;

public interface IUsers {

    Users getUser(String id);

    List<Users> getAllUsers();

    boolean addUser(Users user);

}
