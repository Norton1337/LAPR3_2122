package lapr.project.controller.model_controllers;

import lapr.project.model.users.Users;
import lapr.project.model.users.idb.IUsers;

import java.util.List;

import static lapr.project.utils.Utils.randomUUID;

public class UserController {

    private final IUsers userDB;

    public UserController(IUsers userDB) {
        this.userDB = userDB;
    }

    public boolean addUser(Users user){
        user.setId(randomUUID());
        return userDB.addUser(user);
    }

    public List<Users> getAllUsers(){
        return userDB.getAllUsers();
    }

    public Users getUserById(String id){
        return userDB.getUser(id);
    }

    public Users getUserByUsername(String username){
        for(Users elem : getAllUsers()){
            if(elem.getUsername().equals(username)){
                return elem;
            }
        }
        return null;
    }
}
