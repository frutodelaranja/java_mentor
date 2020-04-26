package service;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private static Service service;
    private List<User> users;

    private Service(){
        users = new ArrayList<>();
    }

    public static synchronized Service getInstance(){
        if (service == null){
            service = new Service();
        }
        return service;
    }
    public boolean add(User user) {
        return users.add(user);
    }
    public List<User> getAllUsers(){
        return users;
    }
    public void delete(Long id){
        int i = 0;
        for (User user: users){
            if (user.getId() == id){
                users.remove(i);
            }
            i++;
        }
    }
    public boolean update(User user){
        int i = 0;
        for (User userList: users){
            if (user.getId() == userList.getId()){
                users.set(i,user);
                return true;
            }
            i++;

        }
        return false;
    }


}
